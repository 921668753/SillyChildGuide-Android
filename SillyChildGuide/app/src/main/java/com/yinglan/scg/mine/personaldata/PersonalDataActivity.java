package com.yinglan.scg.mine.personaldata;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.common.pictureselector.FullyGridLayoutManager;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.FileUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.GridImageAdapter;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.entity.mine.personaldata.PersonalDataBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.personaldata.dialog.PictureSourceDialog;
import com.yinglan.scg.mine.personaldata.setnickname.SetNickNameActivity;
import com.yinglan.scg.mine.personaldata.setselfintroduction.SetSelfIntroductionActivity;
import com.yinglan.scg.mine.personaldata.setsex.SetSexActivity;
import com.yinglan.scg.mine.setup.feedback.FeedbackActivity;
import com.yinglan.scg.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE_PREVIEW;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_ORDER;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_ADD;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MINUS;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MINUSALL;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MOVE;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_GET;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_PAYMENT_SUCCEED;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_PRODUCT;

/**
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View, EasyPermissions.PermissionCallbacks, GridImageAdapter.OnItemClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.ll_headPortrait, click = true)
    private LinearLayout ll_headPortrait;

    @BindView(id = R.id.iv_headPortrait)
    private ImageView iv_headPortrait;

    @BindView(id = R.id.ll_nickname, click = true)
    private LinearLayout ll_nickname;

    @BindView(id = R.id.tv_nickname)
    private TextView tv_nickname;

    @BindView(id = R.id.ll_gender, click = true)
    private LinearLayout ll_gender;

    @BindView(id = R.id.tv_gender)
    private TextView tv_gender;

    @BindView(id = R.id.ll_selfIntroduction, click = true)
    private LinearLayout ll_selfIntroduction;

    @BindView(id = R.id.tv_selfIntroduction)
    private TextView tv_selfIntroduction;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    private boolean isRefresh = false;

    private PictureSourceDialog pictureSourceDialog = null;


    private List<LocalMedia> selectList = null;
    private GridImageAdapter adapter;
    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();
    private int aspect_ratio_x = 16, aspect_ratio_y = 9;
    private int maxSelectNum = 9;

    private String selfIntroduction = "";

    public int type = -1;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personaldata);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PersonalDataPresenter(this);
        selectList = new ArrayList<LocalMedia>();
        themeId = R.style.picture_default_style;
        adapter = new GridImageAdapter(PersonalDataActivity.this, onAddPicClickListener);
        showLoadingDialog(getString(R.string.dataLoad));
        ((PersonalDataContract.Presenter) mPresenter).getInfo();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(PersonalDataActivity.this);
                } else {
                    ViewInject.toast(getString(R.string.picture_jurisdiction));
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(getString(R.string.personalData));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                if (isRefresh) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (selectList.size() > 0) {
                String pictureType = selectList.get(selectList.size() - 1).getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                if (mediaType == 2 || mediaType == 3) {
                    ViewInject.toast(getString(R.string.videoOnlyAddOne));
                    return;
                }
            }
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(PersonalDataActivity.this)
                    .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .previewVideo(true)// 是否可预览视频
                    .enablePreviewAudio(true) // 是否可播放音频
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
//                    .enableCrop(true)// 是否裁剪
//                    .compress(false)// 是否压缩
                    //              .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.compressSavePath(getPath())//压缩图片保存地址
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    //       .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                    .isGif(true)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    //.rotateEnabled(true) // 裁剪是否可旋转图片
                    //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                    //.videoQuality()// 视频录制质量 0 or 1
                    .videoMaxSecond(60)// 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(1)// 显示多少秒以内的视频or音频也可适用 int
                    .recordVideoSecond(60)//视频秒数录制 默认60s int
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_headPortrait:
                PictureDialog();
                break;
            case R.id.ll_nickname:
                Intent setNickNameIntent = new Intent(this, SetNickNameActivity.class);
                setNickNameIntent.putExtra("nickname", tv_nickname.getText().toString());
                showActivityForResult(this, setNickNameIntent, RESULT_CODE_BASKET_ADD);
                break;
            case R.id.ll_gender:
                int sex = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "sex", 0);
                Intent setSexIntent = new Intent(this, SetSexActivity.class);
                setSexIntent.putExtra("sex", sex);
                startActivityForResult(setSexIntent, RESULT_CODE_BASKET_MINUS);
                break;
            case R.id.ll_selfIntroduction:
                Intent setSelfIntroductionIntent = new Intent(this, SetSelfIntroductionActivity.class);
                setSelfIntroductionIntent.putExtra("selfIntroduction", selfIntroduction);
                startActivityForResult(setSelfIntroductionIntent, RESULT_CODE_BASKET_MINUSALL);
                break;
        }
    }

    @Override
    public void onItemClick(int position, View v) {
        if (selectList.size() > 0) {
            LocalMedia media = selectList.get(position);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片 可自定长按保存路径
                    //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                    PictureSelector.create(PersonalDataActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                    break;
            }
        }
    }


    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms) && type == RESULT_CODE_ORDER) {
            PictureSelector.create(PersonalDataActivity.this)
                    .openCamera(PictureMimeType.ofImage())
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
                    .enableCrop(true)// 是否裁剪
                    .compress(true)// 是否压缩
                    .circleDimmedLayer(true)// 是否圆形裁剪
                    .forResult(RESULT_CODE_ORDER);
        } else if (EasyPermissions.hasPermissions(this, perms) && type == RESULT_CODE_PAYMENT_SUCCEED) {
            PictureSelector.create(PersonalDataActivity.this)
                    .openGallery(PictureMimeType.ofImage())
                    .isCamera(false)// 是否显示拍照按钮
                    .enableCrop(true)// 是否裁剪
                    .compress(true)// 是否压缩
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .circleDimmedLayer(true)// 是否圆形裁剪
                    .previewImage(true)// 是否可预览图片
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
                    .forResult(RESULT_CODE_ORDER);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.needPermission), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
            ViewInject.toast(getString(R.string.denyPermission));
        }
    }


    /**
     * 选择更换头像的弹窗
     */
    public void PictureDialog() {
        if (pictureSourceDialog == null) {
            pictureSourceDialog = new PictureSourceDialog(aty) {
                @Override
                public void takePhoto() {
                    type = RESULT_CODE_ORDER;
                    choicePhotoWrapper();
                }

                @Override
                public void chooseFromAlbum() {
                    type = RESULT_CODE_PAYMENT_SUCCEED;
                    choicePhotoWrapper();
                }
            };
        }
        pictureSourceDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case RESULT_CODE_BASKET_ADD:
                    tv_nickname.setText(data.getStringExtra("nickname"));
                    isRefresh = true;
                    break;
                case RESULT_CODE_BASKET_MINUS:
                    int sex = data.getIntExtra("sex", 0);
                    if (sex == 1) {
                        tv_gender.setText(getString(R.string.man));
                    } else if (sex == 2) {
                        tv_gender.setText(getString(R.string.woman));
                    } else {
                        tv_gender.setText(getString(R.string.secret));
                    }
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", sex);
                    break;
                case RESULT_CODE_BASKET_MINUSALL:
                    selfIntroduction = data.getStringExtra("selfIntroduction");
                    tv_selfIntroduction.setText(selfIntroduction);
                    break;
                case RESULT_CODE_ORDER:
                    // 图片选择结果回调
                    List<LocalMedia> selectList1 = PictureSelector.obtainMultipleResult(data);
                    if (selectList1 == null || selectList1.size() == 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    String touxiangpath = selectList1.get(0).getPath();
                    showLoadingDialog(getString(R.string.saveLoad));
                    ((PersonalDataContract.Presenter) mPresenter).upPictures(touxiangpath);
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void setPresenter(PersonalDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        switch (flag) {
            case 0:
                showLoadingDialog(getString(R.string.saveLoad));
                ((PersonalDataContract.Presenter) mPresenter).postMemberEdit(success);
                isRefresh = true;
                break;
            case 1:
                PersonalDataBean personalDataBean = (PersonalDataBean) JsonUtil.getInstance().json2Obj(success, PersonalDataBean.class);
                if (personalDataBean != null && personalDataBean.getData() != null) {
                    if (StringUtils.isEmpty(personalDataBean.getData().getFace())) {
                        iv_headPortrait.setImageResource(R.mipmap.avatar);
                    } else {
                        GlideImageLoader.glideLoader(aty, personalDataBean.getData().getFace(), iv_headPortrait, 0, R.mipmap.avatar);
                    }
                    if (StringUtils.isEmpty(personalDataBean.getData().getNickname())) {
                        String mobile = PreferenceHelper.readString(aty, StringConstants.FILENAME, "mobile");
                        tv_nickname.setText(mobile);
                    } else {
                        tv_nickname.setText(personalDataBean.getData().getNickname());
                    }
                    if (personalDataBean.getData().getSex() == 1) {
                        tv_gender.setText(getString(R.string.man));
                    } else if (personalDataBean.getData().getSex() == 2) {
                        tv_gender.setText(getString(R.string.woman));
                    } else {
                        tv_gender.setText(getString(R.string.secret));
                    }
                    selfIntroduction = personalDataBean.getData().getRemark();
                    tv_selfIntroduction.setText(personalDataBean.getData().getRemark());
                }
                break;
            case 2:
                GlideImageLoader.glideLoader(aty, success, iv_headPortrait, 0, R.mipmap.avatar);
                break;
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
        dismissLoadingDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pictureSourceDialog != null) {
            pictureSourceDialog.cancel();
        }
        selectList.clear();
        onAddPicClickListener = null;
        selectList = null;
        adapter = null;
        pictureSourceDialog = null;
    }

    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isRefresh) {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
        }
        return super.onKeyUp(keyCode, event);
    }


}
