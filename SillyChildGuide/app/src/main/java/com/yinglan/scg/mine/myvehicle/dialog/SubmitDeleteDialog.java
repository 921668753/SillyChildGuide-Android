package com.yinglan.scg.mine.myvehicle.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scg.R;

import static android.app.Activity.RESULT_OK;

/**
 * 删除弹框
 */
public abstract class SubmitDeleteDialog extends BaseDialog implements View.OnClickListener {

    private int id = 0;

    public SubmitDeleteDialog(Context context) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clearcache);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(mContext.getString(R.string.confirmDeletionVehicleInformation));
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_determine:
                submitAuditDo(id);
                break;
        }
    }

    public abstract void submitAuditDo(int id);

    public void setId(int id) {
        this.id = id;
    }


}
