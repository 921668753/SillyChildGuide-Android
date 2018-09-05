package com.yinglan.scg.mine.personaldata.authenticationinformation.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scg.R;

/**
 * 提交资料弹框
 */
public abstract class SubmitAuditDialog extends BaseDialog implements View.OnClickListener {

    private TextView tv_prompting, tv_content, tv_divider;

    private LinearLayout ll_bottom;
    private Handler handler;

    public SubmitAuditDialog(Context context) {
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
        tv_prompting = (TextView) findViewById(R.id.tv_prompting);
        tv_divider = (TextView) findViewById(R.id.tv_divider);
        ll_bottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(mContext.getString(R.string.submitAuthenticationInformation));
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
                handler = null;
                break;
            case R.id.tv_determine:
                submitAuditDo();
                break;
        }
    }

    public abstract void submitAuditDo();

    public void setContentText() {
        tv_content.setText(mContext.getString(R.string.submitAuthenticationInformation1));
        tv_prompting.setVisibility(View.GONE);
        tv_divider.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.GONE);
        if (handler == null) {
            handler = new Handler();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                handler = null;
            }
        }, 4000);
    }


}
