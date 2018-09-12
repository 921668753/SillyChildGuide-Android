package com.yinglan.scg.mine.myorder.orderdetails;

import android.support.v7.widget.RecyclerView;
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
import com.yinglan.scg.entity.mine.myorder.orderdetails.LineOrderDetailsBean;
import com.yinglan.scg.loginregister.LoginActivity;

/**
 * 线路订单详情
 */
public class LineOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

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

    @BindView(id = R.id.web_lineDetails)
    private WebViewLayout web_lineDetails;

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
        setContentView(R.layout.activity_lineorderdetails);
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
        web_lineDetails.setTitleVisibility(false);
        web_lineDetails.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
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
        dismissLoadingDialog();
        LineOrderDetailsBean lineOrderDetailsBean = (LineOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, LineOrderDetailsBean.class);
        if (lineOrderDetailsBean == null || lineOrderDetailsBean.getData() == null) {
            errorMsg(getString(R.string.serverError), 0);
            return;
        }
        tv_title.setText(lineOrderDetailsBean.getData().getTitle());
        tv_orderPrice.setText(getString(R.string.renminbi) + lineOrderDetailsBean.getData().getOrder_price());
        tv_demand.setText(lineOrderDetailsBean.getData().getSubtitle());
        tv_time.setText(DataUtil.formatData(StringUtils.toLong(lineOrderDetailsBean.getData().getStart_time()), "yyyy-MM-dd") + "—"
                + DataUtil.formatData(StringUtils.toLong(lineOrderDetailsBean.getData().getEnd_time()), "yyyy-MM-dd"));
        tv_serviceTime.setText(DataUtil.formatData(StringUtils.toLong(lineOrderDetailsBean.getData().getStart_time()), "yyyy-MM-dd") + "—"
                + DataUtil.formatData(StringUtils.toLong(lineOrderDetailsBean.getData().getEnd_time()), "yyyy-MM-dd"));
        tv_placeDeparture.setText(lineOrderDetailsBean.getData().getOrigin_name());
        tv_deliveredAirport.setText(lineOrderDetailsBean.getData().getDestination_name());
        tv_reserveRequirements.setText(lineOrderDetailsBean.getData().getBooking_request());
        tv_orderNumber.setText(lineOrderDetailsBean.getData().getOrder_number());
        tv_orderIncome.setText(getString(R.string.rmb) + "  " + lineOrderDetailsBean.getData().getOrder_price());
        tv_aggregate.setText(getString(R.string.rmb) + "  " + lineOrderDetailsBean.getData().getOrder_price());

        String product_description = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                lineOrderDetailsBean.getData().getProduct_description() + "</body></html>";
        web_lineDetails.loadDataWithBaseURL("", product_description, "text/html", "utf-8", null);
        String book_comment = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                lineOrderDetailsBean.getData().getBook_comment() + "</body></html>";
        web_dueThat.loadDataWithBaseURL("", book_comment, "text/html", "utf-8", null);
        String price_description = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                lineOrderDetailsBean.getData().getPrice_comment() + "</body></html>";
        web_descriptionThat.loadDataWithBaseURL("", price_description, "text/html", "utf-8", null);

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
