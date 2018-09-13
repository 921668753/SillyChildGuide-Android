package com.yinglan.scg.orderreceiving.dialog;

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
import com.yinglan.scg.R;
import com.yinglan.scg.mine.personaldata.PersonalDataActivity;

/**
 * 认证弹窗
 */
public class CertificationDialog extends BaseDialog implements View.OnClickListener {

    private int type = 0;
    private TextView tv_content;

    public CertificationDialog(@NonNull Context context) {
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
        tv_content = (TextView) findViewById(R.id.tv_content);
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
                dismiss();
                if (type == 0 || type == 1) {
                    Intent intent = new Intent(mContext, PersonalDataActivity.class);
                    ((Activity) mContext).startActivity(intent);
                }
                break;
        }
    }

    public void setOrdersBouncedDialog(int type) {
        this.type = type;
        if (type == 0) {
            tv_content.setText(mContext.getString(R.string.notCertified));
            return;
        }
        if (type == 1) {
            tv_content.setText(mContext.getString(R.string.notCertified1));
            return;
        }
    }


}