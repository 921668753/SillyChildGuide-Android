package com.yinglan.scg.service.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.yinglan.scg.R;
import com.yinglan.scg.loginregister.LoginActivity;


/**
 * 结束订单弹框
 */
public class EndTheOrderDialog extends BaseDialog implements View.OnClickListener, EndTheOrderContract.View {

    private String order_number;

    public EndTheOrderDialog(@NonNull Context context, String order_number) {
        super(context, R.style.MyDialog);
        this.order_number = order_number;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_endtheorder);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        TextView tv_context = (TextView) findViewById(R.id.tv_context);
        tv_context.setText(mContext.getString(R.string.finalizeOrder));
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
        mPresenter = new EndTheOrderPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                dismiss();
                showLoadingDialog(mContext.getString(R.string.dataLoad));
                ((EndTheOrderContract.Presenter) mPresenter).postEndTheOrder(mContext, order_number);
                break;
        }
    }

    @Override
    public void setPresenter(EndTheOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        /**
         * 发送消息
         */
        RxBus.getInstance().post(new MsgEvent<String>("RxBusEndTheOrderEvent"));
//        Intent intent = new Intent();
//        // 获取内容
//        ((Activity) mContext).setResult(RESULT_OK, intent);
        ((Activity) mContext).finish();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            Intent intent = new Intent();
            intent.setClass(mContext, LoginActivity.class);
            mContext.startActivity(intent);
            return;
        }
        ViewInject.toast(msg);
    }
}
