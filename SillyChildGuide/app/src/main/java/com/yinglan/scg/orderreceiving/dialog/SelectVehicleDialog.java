package com.yinglan.scg.orderreceiving.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.orderreceiving.dialog.SelectVehicleViewAdapter;
import com.yinglan.scg.entity.orderreceiving.TransferDetailsBean.DataBean.ModelListBean;

import java.util.List;

public abstract class SelectVehicleDialog extends BaseDialog implements AdapterView.OnItemClickListener {

    private List<ModelListBean> list;
    private SelectVehicleViewAdapter mAdapter;
    private int position = 0;

    public SelectVehicleDialog(@NonNull Context context, List<ModelListBean> list) {
        super(context, R.style.MyDialog);
        this.list = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectvehicle);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        mAdapter = new SelectVehicleViewAdapter(mContext);
        ImageView img_cancel = (ImageView) findViewById(R.id.img_cancel);
        img_cancel.setOnClickListener(this);
        LinearLayout ll_content = (LinearLayout) findViewById(R.id.ll_content);
        ListView lv_myVehicle = (ListView) findViewById(R.id.lv_myVehicle);
        lv_myVehicle.setAdapter(mAdapter);
        mAdapter.clear();
        mAdapter.addNewData(list);
        lv_myVehicle.setOnItemClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                dismiss();
                getModel(mAdapter.getItem(position));
                break;
        }
    }

    public abstract void getModel(ModelListBean bean);


    public void setList(List<ModelListBean> list) {
        this.list = list;
        if (mAdapter == null) {
            mAdapter = new SelectVehicleViewAdapter(mContext);
        }
        mAdapter.clear();
        mAdapter.addNewData(list);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
        for (int i = 0; i < mAdapter.getData().size(); i++) {
            if (position == i) {
                mAdapter.getData().get(i).setIs_default(1);
            } else {
                mAdapter.getData().get(i).setIs_default(0);
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
