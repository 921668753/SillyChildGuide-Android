package com.yinglan.scg.service.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseDialog;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.DensityUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.service.TripViewAdapter;
import com.yinglan.scg.entity.service.dialog.TripBean;
import com.yinglan.scg.loginregister.LoginActivity;

/**
 * 行程弹框
 */
public class TripDialog extends BaseDialog implements TripContract.View, View.OnClickListener {

    private ListView lv_trip;
    private TripViewAdapter mAdapter;

    public TripDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_trip);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        mPresenter = new TripPresenter(this);
        ImageView img_cross = (ImageView) findViewById(R.id.img_cross);
        img_cross.setOnClickListener(this);
        lv_trip = (ListView) findViewById(R.id.lv_trip);
        mAdapter = new TripViewAdapter(mContext);
        lv_trip.setAdapter(mAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cross:
                dismiss();
                break;
        }
    }


    public void setTripTime(long time) {
        showLoadingDialog(mContext.getString(R.string.dataLoad));
        ((TripContract.Presenter) mPresenter).getGuideOrderStroke(mContext, String.valueOf(time));
    }

    @Override
    public void setPresenter(TripContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        TripBean tripBean = (TripBean) JsonUtil.getInstance().json2Obj(success, TripBean.class);
        if (tripBean == null || tripBean.getData() == null || tripBean.getData().size() <= 0) {
            errorMsg(mContext.getString(R.string.serverError), 0);
            return;
        }
        if (tripBean.getData().size() > 1) {
            ViewGroup.LayoutParams layoutParams = lv_trip.getLayoutParams();
            layoutParams.height = DensityUtils.dip2px(300);
        }
        mAdapter.clear();
        mAdapter.addNewData(tripBean.getData());
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
