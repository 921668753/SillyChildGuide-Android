package com.yinglan.scg.mine.setup.feedback;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.common.pictureselector.FullyGridLayoutManager;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.FileUtils;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.GridImageAdapter;
import com.yinglan.scg.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 设置中的意见反馈
 * Created by Administrator on 2017/9/2.
 */
public class FeedbackActivity extends BaseActivity implements TextWatcher, FeedbackContract.View, GridImageAdapter.OnItemClickListener {

    @BindView(id = R.id.tv_feedbackType)
    private TextView tv_feedbackType;

    @BindView(id = R.id.ll_dysfunction, click = true)
    private LinearLayout ll_dysfunction;

    @BindView(id = R.id.img_dysfunction)
    private ImageView img_dysfunction;


    @BindView(id = R.id.ll_experienceProblem, click = true)
    private LinearLayout ll_experienceProblem;

    @BindView(id = R.id.img_experienceProblem)
    private ImageView img_experienceProblem;

    @BindView(id = R.id.ll_newFeatureRecommendations, click = true)
    private LinearLayout ll_newFeatureRecommendations;

    @BindView(id = R.id.img_newFeatureRecommendations)
    private ImageView img_newFeatureRecommendations;

    @BindView(id = R.id.ll_other, click = true)
    private LinearLayout ll_other;
    @BindView(id = R.id.img_other)
    private ImageView img_other;

    @BindView(id = R.id.et_feed)
    private EditText et_feed;

    @BindView(id = R.id.tv_currentwords)
    private TextView tv_currentwords;

    @BindView(id = R.id.tv_wordLimit)
    private TextView tv_wordLimit;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    @BindView(id = R.id.tv_submit, click = true)
    private TextView tv_submit;

    private String feedType;

    private int wordLimit;

    private List<LocalMedia> selectList = null;
    private GridImageAdapter adapter;
    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();
    private int aspect_ratio_x = 16, aspect_ratio_y = 9;
    private int maxSelectNum = 9;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new FeedbackPresenter(this);
        wordLimit = StringUtils.toInt(tv_wordLimit.getText().toString(), 500);
        selectList = new ArrayList<LocalMedia>();
        themeId = R.style.picture_default_style;
        adapter = new GridImageAdapter(FeedbackActivity.this, onAddPicClickListener);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        feedType = getString(R.string.dysfunction);
        tv_feedbackType.setFocusable(true);
        tv_feedbackType.setFocusableInTouchMode(true);
        tv_feedbackType.requestFocus();
        tv_feedbackType.requestFocusFromTouch();
        et_feed.addTextChangedListener(this);
        et_feed.setMovementMethod(ScrollingMovementMethod.getInstance());
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
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
                    PictureFileUtils.deleteCacheDirFile(FeedbackActivity.this);
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
        ActivityTitleUtils.initToolbar(aty, getString(R.string.feedback), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_dysfunction:
                img_dysfunction.setImageResource(R.mipmap.feedback_selected);
                img_experienceProblem.setImageResource(R.mipmap.feedback_unselected);
                img_newFeatureRecommendations.setImageResource(R.mipmap.feedback_unselected);
                img_other.setImageResource(R.mipmap.feedback_unselected);
                feedType = getString(R.string.dysfunction);
                break;
            case R.id.ll_experienceProblem:
                img_dysfunction.setImageResource(R.mipmap.feedback_unselected);
                img_experienceProblem.setImageResource(R.mipmap.feedback_selected);
                img_newFeatureRecommendations.setImageResource(R.mipmap.feedback_unselected);
                img_other.setImageResource(R.mipmap.feedback_unselected);
                feedType = getString(R.string.experienceProblem);
                break;
            case R.id.ll_newFeatureRecommendations:
                img_dysfunction.setImageResource(R.mipmap.feedback_unselected);
                img_experienceProblem.setImageResource(R.mipmap.feedback_unselected);
                img_newFeatureRecommendations.setImageResource(R.mipmap.feedback_selected);
                img_other.setImageResource(R.mipmap.feedback_unselected);
                feedType = getString(R.string.newFeatureRecommendations);
                break;
            case R.id.ll_other:
                img_dysfunction.setImageResource(R.mipmap.feedback_unselected);
                img_experienceProblem.setImageResource(R.mipmap.feedback_unselected);
                img_newFeatureRecommendations.setImageResource(R.mipmap.feedback_unselected);
                img_other.setImageResource(R.mipmap.feedback_selected);
                feedType = getString(R.string.other);
                break;
            case R.id.tv_submit:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((FeedbackContract.Presenter) mPresenter).postAdvice(feedType, et_feed.getText().toString(), selectList);
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
                    PictureSelector.create(FeedbackActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        //text  输入框中改变前的字符串信息
        //start 输入框中改变前的字符串的起始位置
        //count 输入框中改变前后的字符串改变数量一般为0
        //after 输入框中改变后的字符串与起始位置的偏移量

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        //text  输入框中改变后的字符串信息
        //start 输入框中改变后的字符串的起始位置
        //before 输入框中改变前的字符串的位置 默认为0
        //count 输入框中改变后的一共输入字符串的数量
        if ((start + count) > wordLimit) {
            tv_currentwords.setText(wordLimit + "/");
        } else {
            tv_currentwords.setText(start + count + "/");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //edit  输入结束呈现在输入框中的信息
        String feedcontent = editable.toString();
        if (feedcontent != null && feedcontent.length() > wordLimit) {
            et_feed.setText(feedcontent.substring(0, wordLimit));
            et_feed.setSelection(wordLimit);
        }
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
            PictureSelector.create(FeedbackActivity.this)
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
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
    public void setPresenter(FeedbackContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.submitSuccess));
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectList.clear();
        onAddPicClickListener = null;
        selectList = null;
        adapter = null;
    }


}
