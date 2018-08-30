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
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.personaldata.ImagePickerAdapter;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.entity.mine.personaldata.PersonalDataBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.personaldata.dialog.PictureSourceDialog;
import com.yinglan.scg.mine.personaldata.setnickname.SetNickNameActivity;
import com.yinglan.scg.mine.personaldata.setselfintroduction.SetSelfIntroductionActivity;
import com.yinglan.scg.mine.personaldata.setsex.SetSexActivity;
import com.yinglan.scg.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE_PREVIEW;
import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE_SELECT;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_ADD;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MINUS;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MINUSALL;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MOVE;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_GET;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_PRODUCT;

/**
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View, EasyPermissions.PermissionCallbacks, ImagePickerAdapter.OnRecyclerViewItemClickListener {

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


    private ImagePicker imagePicker;

    private List<ImageItem> selImageList;

    private List<ImageItem> images;
    // private List<String> urllist;
    private ImagePickerAdapter adapter;

    private String selfIntroduction = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personaldata);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PersonalDataPresenter(this);
        initImagePicker();
        selImageList = new ArrayList<>();
        // urllist = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        showLoadingDialog(getString(R.string.dataLoad));
        ((PersonalDataContract.Presenter) mPresenter).getInfo();
    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);                  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
        imagePicker.setShowCamera(false);                      //显示拍照按钮
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
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

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper(int code) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms) && code == RESULT_CODE_GET) {
            Intent intent = new Intent(PersonalDataActivity.this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else if (EasyPermissions.hasPermissions(this, perms) && code == RESULT_CODE_BASKET_MOVE) {
            Intent intent = new Intent(PersonalDataActivity.this, ImageGridActivity.class);
            /* 如果需要进入选择的时候显示已经选中的图片，
             * 详情请查看ImagePickerActivity
             */
            // intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else if (EasyPermissions.hasPermissions(this, perms) && code == RESULT_CODE_PRODUCT) {
            ImagePicker.getInstance().setSelectLimit(8);
            ImagePicker.getInstance().setShowCamera(true);//显示拍照按钮
            ImagePicker.getInstance().setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
            ImagePicker.getInstance().setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            ImagePicker.getInstance().setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
            ImagePicker.getInstance().setOutPutX(1000);                         //保存文件的宽度。单位像素
            ImagePicker.getInstance().setOutPutY(1000);                         //保存文件的高度。单位像素
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
        ImagePicker.getInstance().setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        ImagePicker.getInstance().setShowCamera(false);//显示拍照按钮
        ImagePicker.getInstance().setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        ImagePicker.getInstance().setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        ImagePicker.getInstance().setOutPutX(800);                         //保存文件的宽度。单位像素
        ImagePicker.getInstance().setOutPutY(800);                         //保存文件的高度。单位像素
        ImagePicker.getInstance().setSelectLimit(1);
        if (pictureSourceDialog == null) {
            pictureSourceDialog = new PictureSourceDialog(aty) {
                @Override
                public void takePhoto() {
                    choicePhotoWrapper(RESULT_CODE_GET);
                }

                @Override
                public void chooseFromAlbum() {
                    choicePhotoWrapper(RESULT_CODE_BASKET_MOVE);
                }
            };
        }
        pictureSourceDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
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
                case REQUEST_CODE_SELECT:
                    if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        if (images == null || images.size() == 0) {
                            ViewInject.toast(getString(R.string.noData));
                            return;
                        }
                        String touxiangpath = images.get(0).path;
                        showLoadingDialog(getString(R.string.saveLoad));
                        ((PersonalDataContract.Presenter) mPresenter).upPictures(touxiangpath);
                    } else {
                        ViewInject.toast(getString(R.string.noData));
                    }
                    break;
                case REQUEST_CODE_PREVIEW:
                    if (resultCode == ImagePicker.RESULT_CODE_BACK && data != null) {
                        images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                        if (images != null) {
                            selImageList.clear();
                            selImageList.addAll(images);
                            adapter.setImages(selImageList);
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                Intent intent1 = new Intent(this, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
                //  intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
//                    imagePopupWindow = new ImagePopupWindow(this, getWindow(), urllist.get(position));
//                    imagePopupWindow.showAtLocation(ll_allactivity, Gravity.CENTER, 0, 0);
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
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
                ((PersonalDataContract.Presenter) mPresenter).postMemberEdit(success);
                showLoadingDialog(getString(R.string.saveLoad));
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
//                    photo
//                    adapter.setImages(selImageList);
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
