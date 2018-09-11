package com.yinglan.scg.orderreceiving;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.orderreceiving.CharterDetailsBean;
import com.yinglan.scg.loginregister.LoginActivity;

/**
 * 接送机详情
 */
public class TransferDetailsActivity extends BaseActivity implements CharterDetailsContract.View {





    private String order_number;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_transferdetails);
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
    public void setPresenter(CharterDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        CharterDetailsBean charterDetailsBean = (CharterDetailsBean) JsonUtil.getInstance().json2Obj(success, CharterDetailsBean.class);


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
