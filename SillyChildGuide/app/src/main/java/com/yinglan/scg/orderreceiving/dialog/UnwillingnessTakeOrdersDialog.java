package com.yinglan.scg.orderreceiving.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.DensityUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.orderreceiving.dialog.UnwillingnessTakeOrdersViewAdapter;
import com.yinglan.scg.entity.orderreceiving.dialog.UnwillingnessTakeOrdersBean;
import com.yinglan.scg.loginregister.LoginActivity;

import static android.app.Activity.RESULT_OK;

/**
 * 不愿意接单弹框
 */
public class UnwillingnessTakeOrdersDialog extends BaseDialog implements View.OnClickListener, AdapterView.OnItemClickListener, UnwillingnessTakeOrdersContract.View {

    private String order_number;
    private UnwillingnessTakeOrdersViewAdapter mAdapter;
    private ListView lv_reason;

    public UnwillingnessTakeOrdersDialog(@NonNull Context context, String order_number) {
        super(context, R.style.MyDialog);
        this.order_number = order_number;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_unwillingnesstakeorders);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        mPresenter = new UnwillingnessTakeOrdersPresenter(this);
        ImageView img_cross = (ImageView) findViewById(R.id.img_cross);
        img_cross.setOnClickListener(this);
        mAdapter = new UnwillingnessTakeOrdersViewAdapter(mContext);
        lv_reason = (ListView) findViewById(R.id.lv_reason);
        lv_reason.setOnItemClickListener(this);
        lv_reason.setAdapter(mAdapter);
        ((UnwillingnessTakeOrdersContract.Presenter) mPresenter).getCancelReasonList();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cross:
                dismiss();
                ((Activity) mContext).finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showLoadingDialog(mContext.getString(R.string.submissionLoad));
        ((UnwillingnessTakeOrdersContract.Presenter) mPresenter).postGuideSubmitOrder(order_number, mAdapter.getItem(position).getReason());
    }

    @Override
    public void setPresenter(UnwillingnessTakeOrdersContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            UnwillingnessTakeOrdersBean unwillingnessTakeOrdersBean = (UnwillingnessTakeOrdersBean) JsonUtil.getInstance().json2Obj(success, UnwillingnessTakeOrdersBean.class);
            if (unwillingnessTakeOrdersBean == null || unwillingnessTakeOrdersBean.getData() == null || unwillingnessTakeOrdersBean.getData().size() <= 0) {
                errorMsg(mContext.getString(R.string.serverError), 0);
                return;
            }
            if (unwillingnessTakeOrdersBean.getData().size() > 3) {
                ViewGroup.LayoutParams layoutParams = lv_reason.getLayoutParams();
                layoutParams.height = DensityUtils.dip2px(215);
            }
            mAdapter.clear();
            mAdapter.addNewData(unwillingnessTakeOrdersBean.getData());
        } else if (flag == 1) {
            dismiss();
            Intent intent = new Intent();
            // 获取内容
            ((Activity) mContext).setResult(RESULT_OK, intent);
            ((Activity) mContext).finish();
        }
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
