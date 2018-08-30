package com.yinglan.scg.mine.setup.feedback;

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
import com.common.cklibrary.common.ImagePreviewNoDelActivity;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.ImagePickerAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置中的意见反馈
 * Created by Administrator on 2017/9/2.
 */

public class FeedbackActivity extends BaseActivity implements TextWatcher, ImagePickerAdapter.OnRecyclerViewItemClickListener, FeedbackContract.View {


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

    private List<ImageItem> selImageList;

    private List<ImageItem> images;

    private List<String> urllist;
    private ImagePickerAdapter adapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new FeedbackPresenter(this);
        wordLimit = StringUtils.toInt(tv_wordLimit.getText().toString(), 500);
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
        initImagePicker();
        selImageList = new ArrayList<>();
        urllist = new ArrayList<String>();
        adapter = new ImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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
                ((FeedbackContract.Presenter) mPresenter).postAdvice(feedType, et_feed.getText().toString(), urllist);
                break;
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


    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
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
//                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                if (view.getId() == R.id.iv_delete) {
                    if (selImageList != null && selImageList.size() > position) {
                        selImageList.remove(position);
                        urllist.remove(position);
                        adapter.setImages(selImageList);
                    }
                } else {
                    //打开预览
                    Intent intentPreview = new Intent(FeedbackActivity.this, ImagePreviewNoDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == ImagePicker.RESULT_CODE_ITEMS && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
            //添加图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            showLoadingDialog(getString(R.string.crossLoad));
            ((FeedbackContract.Presenter) mPresenter).upPictures(images.get(0).path);
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_BACK && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
            //预览图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
            if (images != null) {
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else {
            ViewInject.toast(getString(R.string.noData));
        }
    }

    @Override
    public void setPresenter(FeedbackContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            urllist.add(success);
            selImageList.addAll(images);
            adapter.setImages(selImageList);
            dismissLoadingDialog();
        } else if (flag == 1) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.submitSuccess));
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //   ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }
}
