package com.yinglan.scg.orderreceiving;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.orderreceiving.CharterDetailsBean;
import com.yinglan.scg.entity.orderreceiving.TransferDetailsBean.DataBean.ModelListBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.orderreceiving.dialog.SelectVehicleDialog;

import java.util.List;

/**
 * 包车订单详情
 */
public class CharterDetailsActivity extends BaseActivity implements CharterDetailsContract.View {

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

    @BindView(id = R.id.web_dueThat)
    private WebViewLayout web_dueThat;

//    @BindView(id = R.id.clv_income)
//    private ChildListView clv_income;

    @BindView(id = R.id.tv_orderIncome)
    private TextView tv_orderIncome;

    @BindView(id = R.id.tv_aggregate)
    private TextView tv_aggregate;

    @BindView(id = R.id.web_descriptionThat)
    private WebViewLayout web_descriptionThat;

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


    private String order_number;
    private int model_id = 0;
    private SelectVehicleDialog selectVehicleDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_charterdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new CharterDetailsPresenter(this);
        order_number = getIntent().getStringExtra("order_number");
        showLoadingDialog(getString(R.string.dataLoad));
        ((CharterDetailsContract.Presenter) mPresenter).getTravelOrderDetails(order_number);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, "", true, R.id.titlebar);
        web_dueThat.setTitleVisibility(false);
        web_dueThat.getWebView().setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
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
    public void setPresenter(CharterDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        CharterDetailsBean charterDetailsBean = (CharterDetailsBean) JsonUtil.getInstance().json2Obj(success, CharterDetailsBean.class);
        if (charterDetailsBean == null || charterDetailsBean.getData() == null) {
            errorMsg(getString(R.string.serverError), 0);
            return;
        }
        tv_title.setText(charterDetailsBean.getData().getTitle());
        tv_orderPrice.setText(getString(R.string.renminbi) + charterDetailsBean.getData().getOrder_price());
        tv_demand.setText(charterDetailsBean.getData().getSubtitle());
        tv_time.setText(DataUtil.formatData(StringUtils.toLong(charterDetailsBean.getData().getStart_time()), "yyyy-MM-dd E"));
        tv_serviceTime.setText(DataUtil.formatData(StringUtils.toLong(charterDetailsBean.getData().getStart_time()), "yyyy-MM-dd E"));
        tv_placeDeparture.setText(charterDetailsBean.getData().getOrigin_name());
        tv_deliveredAirport.setText(charterDetailsBean.getData().getDestination_name());
        tv_reserveRequirements.setText(charterDetailsBean.getData().getSubtitle());
        tv_orderNumber.setText(charterDetailsBean.getData().getOrder_number());
        tv_orderIncome.setText(getString(R.string.rmb) + "  " + charterDetailsBean.getData().getOrder_price());
        tv_aggregate.setText(getString(R.string.rmb) + "  " + charterDetailsBean.getData().getOrder_price());
        String book_comment = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                charterDetailsBean.getData().getBook_comment() + "</body></html>";
        web_dueThat.loadDataWithBaseURL("baseurl", book_comment, "text/html", "utf-8", null);
        String price_description = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" /><title></title></head><body>" +
                charterDetailsBean.getData().getPrice_comment() + "</body></html>";
        web_descriptionThat.loadDataWithBaseURL("baseurl", price_description, "text/html", "utf-8", null);
        if (charterDetailsBean.getData() != null && charterDetailsBean.getData().getModel_list() != null && charterDetailsBean.getData().getModel_list().size() == 1) {
            tv_licensePlateNumber.setText(charterDetailsBean.getData().getModel_list().get(0).getLicense_plate());
            tv_models.setText(charterDetailsBean.getData().getModel_list().get(0).getModel_name());
            model_id = charterDetailsBean.getData().getModel_list().get(0).getId();
            tv_selectVehicle.setVisibility(View.GONE);
        }
        if (charterDetailsBean.getData() != null && charterDetailsBean.getData().getModel_list() != null && charterDetailsBean.getData().getModel_list().size() > 1) {
            tv_selectVehicle.setVisibility(View.VISIBLE);
            setDialog(charterDetailsBean.getData().getModel_list());
        }
    }

    private void setDialog(List<ModelListBean> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIs_default() == 1) {
                tv_licensePlateNumber.setText(list.get(i).getLicense_plate());
                tv_models.setText(list.get(i).getModel_name());
                model_id = list.get(i).getId();
                break;
            }
        }
        selectVehicleDialog = new SelectVehicleDialog(this, list) {
            @Override
            public void getModel(ModelListBean bean) {
                model_id = bean.getId();
                tv_licensePlateNumber.setText(bean.getLicense_plate());
                tv_models.setText(bean.getModel_name());
            }
        };
        //  selectVehicleDialog.setList(list);
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
