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
import com.yinglan.scg.entity.mine.myorder.orderdetails.TransferOrderDetailsBean;
import com.yinglan.scg.entity.orderreceiving.TransferDetailsBean;
import com.yinglan.scg.entity.orderreceiving.TransferDetailsBean.DataBean.ModelListBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.orderreceiving.dialog.SelectVehicleDialog;

import java.util.List;

/**
 * 接送机详情
 */
public class TransferOrderDetailsActivity extends BaseActivity implements CharterOrderDetailsContract.View {

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

    @BindView(id = R.id.tv_orderIncome)
    private TextView tv_orderIncome;


    @BindView(id = R.id.tv_aggregate)
    private TextView tv_aggregate;

    @BindView(id = R.id.web_descriptionThat)
    private WebViewLayout web_descriptionThat;

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

    private SelectVehicleDialog selectVehicleDialog = null;
    private int model_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_transferdetails);
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
        web_descriptionThat.setTitleVisibility(false);
        web_descriptionThat.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv_quickOrder.setVisibility(View.VISIBLE);
        tv_endTheOrder.setVisibility(View.GONE);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_selectVehicle:
                selectVehicleDialog.show();
                break;
            case R.id.tv_quickOrder:

                break;
        }
    }

    @Override
    public void setPresenter(CharterOrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        TransferOrderDetailsBean transferOrderDetailsBean = (TransferOrderDetailsBean) JsonUtil.getInstance().json2Obj(success, TransferOrderDetailsBean.class);
        if (transferOrderDetailsBean == null || transferOrderDetailsBean.getData() == null) {
            errorMsg(getString(R.string.serverError), 0);
            return;
        }
        tv_title.setText(transferOrderDetailsBean.getData().getTitle());
        tv_orderPrice.setText(getString(R.string.renminbi) + transferOrderDetailsBean.getData().getOrder_price());
        tv_demand.setText(transferOrderDetailsBean.getData().getSubtitle());
        tv_time.setText(DataUtil.formatData(StringUtils.toLong(transferOrderDetailsBean.getData().getStart_time()), "yyyy-MM-dd E HH:mm"));
        tv_serviceTime.setText(DataUtil.formatData(StringUtils.toLong(transferOrderDetailsBean.getData().getStart_time()), "yyyy-MM-dd E HH:mm"));
        tv_placeDeparture.setText(transferOrderDetailsBean.getData().getOrigin_name());
        tv_deliveredAirport.setText(transferOrderDetailsBean.getData().getDestination_name());
        tv_reserveRequirements.setText(transferOrderDetailsBean.getData().getBooking_request());
        tv_orderNumber.setText(transferOrderDetailsBean.getData().getOrder_number());
        tv_orderIncome.setText(getString(R.string.rmb) + "  " + transferOrderDetailsBean.getData().getOrder_price());
        tv_aggregate.setText(getString(R.string.rmb) + "  " + transferOrderDetailsBean.getData().getOrder_price());
        String content = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                transferOrderDetailsBean.getData().getPrice_comment() + "</body></html>";
        web_descriptionThat.loadDataWithBaseURL("", content, "text/html", "utf-8", null);

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
    protected void onDestroy() {
        super.onDestroy();
        if (selectVehicleDialog != null) {
            selectVehicleDialog.cancel();
        }
        selectVehicleDialog = null;
    }
}
