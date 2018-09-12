package com.yinglan.scg.mine.myorder.orderdetails;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.myorder.orderdetails.PrivateCustomOrderDetailsBean;
import com.yinglan.scg.loginregister.LoginActivity;

/**
 * 私人定制订单详情
 */
public class PrivateCustomOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

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

    @BindView(id = R.id.tv_selectVehicle, click = true)
    private TextView tv_selectVehicle;

    @BindView(id = R.id.tv_quickOrder, click = true)
    private TextView tv_quickOrder;

    @BindView(id = R.id.tv_endTheOrder)
    private TextView tv_endTheOrder;

    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

    private String order_number;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_privatecustomorderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterOrderDetailsPresenter(this);
        order_number = getIntent().getStringExtra("order_number");
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterOrderDetailsContract.Presenter) mPresenter).getMyOrderDetails(order_number);
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
}
