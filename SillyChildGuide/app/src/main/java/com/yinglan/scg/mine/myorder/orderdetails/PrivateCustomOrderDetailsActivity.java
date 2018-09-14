package com.yinglan.scg.mine.myorder.orderdetails;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.common.pictureselector.FullyGridLayoutManager;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.FileUtils;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myvehicle.GridImageAdapter;
import com.yinglan.scg.entity.mine.myorder.orderdetails.PrivateCustomOrderDetailsBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.service.dialog.EndTheOrderDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 私人定制订单详情
 */
public class PrivateCustomOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View, GridImageAdapter.OnItemClickListener {

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_orderPrice)
    private TextView tv_orderPrice;

    @BindView(id = R.id.tv_demand)
    private TextView tv_demand;

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.tv_serviceTime)
    private TextView tv_serviceTime;

    @BindView(id = R.id.tv_placeDeparture)
    private TextView tv_placeDeparture;

    @BindView(id = R.id.tv_deliveredAirport)
    private TextView tv_deliveredAirport;

    @BindView(id = R.id.tv_reserveRequirements)
    private TextView tv_reserveRequirements;

    @BindView(id = R.id.tv_orderNumber)
    private TextView tv_orderNumber;

    @BindView(id = R.id.tv_contact)
    private TextView tv_contact;

    @BindView(id = R.id.tv_contactWay)
    private TextView tv_contactWay;

    @BindView(id = R.id.web_detailedItinerary)
    private WebViewLayout web_detailedItinerary;


    @BindView(id = R.id.tv_travelPreferences)
    private TextView tv_travelPreferences;

    @BindView(id = R.id.tv_foodPreferences)
    private TextView tv_foodPreferences;


    @BindView(id = R.id.tv_accommodationPreferences)
    private TextView tv_accommodationPreferences;

    @BindView(id = R.id.web_dueThat)
    private WebViewLayout web_dueThat;

    @BindView(id = R.id.tv_orderIncome)
    private TextView tv_orderIncome;

    @BindView(id = R.id.tv_aggregate)
    private TextView tv_aggregate;

    @BindView(id = R.id.web_descriptionThat)
    private WebViewLayout web_descriptionThat;

    @BindView(id = R.id.tv_userEvaluation)
    private TextView tv_userEvaluation;

    @BindView(id = R.id.ll_userEvaluation)
    private LinearLayout ll_userEvaluation;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_nickName)
    private TextView tv_nickName;

    @BindView(id = R.id.img_satisfactionLevel)
    private ImageView img_satisfactionLevel;

    @BindView(id = R.id.tv_content)
    private TextView tv_content;

    @BindView(id = R.id.gv_imgComments)
    private NoScrollGridView gv_imgComments;

    @BindView(id = R.id.tv_time1)
    private TextView tv_time1;

    @BindView(id = R.id.rl_evaluateGuest)
    private RelativeLayout rl_evaluateGuest;

    @BindView(id = R.id.et_evaluateGuest)
    private EditText et_evaluateGuest;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    @BindView(id = R.id.tv_submitAudit, click = true)
    private TextView tv_submitAudit;

    @BindView(id = R.id.tv_licensePlateNumber)
    private TextView tv_licensePlateNumber;

    @BindView(id = R.id.tv_models)
    private TextView tv_models;

    @BindView(id = R.id.tv_selectVehicle)
    private TextView tv_selectVehicle;

    @BindView(id = R.id.tv_quickOrder)
    private TextView tv_quickOrder;

    @BindView(id = R.id.tv_endTheOrder, click = true)
    private TextView tv_endTheOrder;

    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

    private String order_number;
    private EndTheOrderDialog endTheOrderDialog;

    private List<LocalMedia> selectList = null;
    private GridImageAdapter adapter;
    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();
    private int aspect_ratio_x = 16, aspect_ratio_y = 9;
    private int maxSelectNum = 9;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustomorderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterOrderDetailsPresenter(this);
        order_number = getIntent().getStringExtra("order_number");
        selectList = new ArrayList<LocalMedia>();
        themeId = R.style.picture_default_style;
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterOrderDetailsContract.Presenter) mPresenter).getMyOrderDetails(order_number);
        initDialog();
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
            PictureSelector.create(PrivateCustomOrderDetailsActivity.this)
                    .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .compress(false)// 是否压缩
                    .isCamera(true)// 是否显示拍照按钮
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .isGif(true)// 是否显示gif图片
                    .selectionMedia(selectList)// 是否传入已选图片
                    .minimumCompressSize(StringConstants.COMPRESSION_SIZE)// 小于100kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };

    private void initDialog() {
        endTheOrderDialog = new EndTheOrderDialog(this, order_number);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, "", true, R.id.titlebar);
        web_detailedItinerary.setTitleVisibility(false);
        web_detailedItinerary.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        web_dueThat.setTitleVisibility(false);
        web_dueThat.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        web_descriptionThat.setTitleVisibility(false);
        web_descriptionThat.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(PrivateCustomOrderDetailsActivity.this);
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

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_endTheOrder:
                if (endTheOrderDialog == null) {
                    initDialog();
                }
                if (endTheOrderDialog != null && !endTheOrderDialog.isShowing()) {
                    endTheOrderDialog.show();
                }
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
                    PictureSelector.create(PrivateCustomOrderDetailsActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                    break;
            }
        }
    }

    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            PrivateCustomOrderDetailsBean privateCustomOrderDetailsBean = (PrivateCustomOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, PrivateCustomOrderDetailsBean.class);
            if (privateCustomOrderDetailsBean == null || privateCustomOrderDetailsBean.getData() == null) {
                errorMsg(getString(R.string.serverError), 0);
                return;
            }
            tv_title.setText(privateCustomOrderDetailsBean.getData().getTitle());
            tv_orderPrice.setText(getString(R.string.renminbi) + privateCustomOrderDetailsBean.getData().getOrder_price());
            tv_demand.setText(privateCustomOrderDetailsBean.getData().getSubtitle());
            tv_time.setText(DataUtil.formatData(StringUtils.toLong(privateCustomOrderDetailsBean.getData().getStart_time()), "yyyy-MM-dd") + "—"
                    + DataUtil.formatData(StringUtils.toLong(privateCustomOrderDetailsBean.getData().getEnd_time()), "yyyy-MM-dd"));
            tv_serviceTime.setText(DataUtil.formatData(StringUtils.toLong(privateCustomOrderDetailsBean.getData().getStart_time()), "yyyy-MM-dd") + "—"
                    + DataUtil.formatData(StringUtils.toLong(privateCustomOrderDetailsBean.getData().getEnd_time()), "yyyy-MM-dd"));
            tv_travelPreferences.setText(privateCustomOrderDetailsBean.getData().getTravel_preference());
            tv_foodPreferences.setText(privateCustomOrderDetailsBean.getData().getRepast_preference());
            tv_accommodationPreferences.setText(privateCustomOrderDetailsBean.getData().getStay_preference());

            tv_placeDeparture.setText(privateCustomOrderDetailsBean.getData().getOrigin_name());
            tv_deliveredAirport.setText(privateCustomOrderDetailsBean.getData().getDestination_name());
            tv_reserveRequirements.setText(privateCustomOrderDetailsBean.getData().getBooking_request());
            tv_orderNumber.setText(privateCustomOrderDetailsBean.getData().getOrder_number());
            tv_contact.setText(privateCustomOrderDetailsBean.getData().getContact());
            tv_contactWay.setText(privateCustomOrderDetailsBean.getData().getConnect_number());
            tv_orderIncome.setText(getString(R.string.rmb) + "  " + privateCustomOrderDetailsBean.getData().getOrder_price());
            tv_aggregate.setText(getString(R.string.rmb) + "  " + privateCustomOrderDetailsBean.getData().getOrder_price());

            tv_licensePlateNumber.setText(privateCustomOrderDetailsBean.getData().getLicense_plate());
            tv_models.setText(privateCustomOrderDetailsBean.getData().getModel_name());

            String schedule = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                    privateCustomOrderDetailsBean.getData().getSchedule() + "</body></html>";
            web_detailedItinerary.loadDataWithBaseURL("baseurl", schedule, "text/html", "utf-8", null);
            String book_comment = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                    privateCustomOrderDetailsBean.getData().getBook_comment() + "</body></html>";
            web_dueThat.loadDataWithBaseURL("baseurl", book_comment, "text/html", "utf-8", null);
            String price_description = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                    privateCustomOrderDetailsBean.getData().getPrice_comment() + "</body></html>";
            web_descriptionThat.loadDataWithBaseURL("baseurl", price_description, "text/html", "utf-8", null);

            switch (privateCustomOrderDetailsBean.getData().getOrder_status()) {
                case 1://待服务
                    tv_userEvaluation.setVisibility(View.GONE);
                    ll_userEvaluation.setVisibility(View.GONE);
                    rl_evaluateGuest.setVisibility(View.GONE);
                    tv_submitAudit.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.GONE);
                    break;
                case 2://进行中
                    tv_userEvaluation.setVisibility(View.GONE);
                    ll_userEvaluation.setVisibility(View.GONE);
                    rl_evaluateGuest.setVisibility(View.GONE);
                    tv_submitAudit.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.VISIBLE);
                    tv_selectVehicle.setVisibility(View.GONE);
                    tv_quickOrder.setVisibility(View.GONE);
                    tv_endTheOrder.setVisibility(View.VISIBLE);
                    break;
                case 3://待评价
                    tv_userEvaluation.setVisibility(View.VISIBLE);
                    ll_userEvaluation.setVisibility(View.VISIBLE);
                    rl_evaluateGuest.setVisibility(View.VISIBLE);
                    tv_submitAudit.setVisibility(View.VISIBLE);
                    ll_bottom.setVisibility(View.GONE);
                    break;
                case 4://已完成
                    tv_userEvaluation.setVisibility(View.GONE);
                    ll_userEvaluation.setVisibility(View.GONE);
                    rl_evaluateGuest.setVisibility(View.GONE);
                    tv_submitAudit.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.GONE);
                    break;
                default:
                    tv_userEvaluation.setVisibility(View.GONE);
                    ll_userEvaluation.setVisibility(View.GONE);
                    rl_evaluateGuest.setVisibility(View.GONE);
                    tv_submitAudit.setVisibility(View.GONE);
                    ll_bottom.setVisibility(View.GONE);
                    break;
            }
            dismissLoadingDialog();
        } else if (flag == 1) {


        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
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
    protected void onDestroy() {
        super.onDestroy();
        if (endTheOrderDialog != null) {
            endTheOrderDialog.cancel();
        }
        endTheOrderDialog = null;
        adapter = null;
        selectList.clear();
        selectList = null;
    }
}
