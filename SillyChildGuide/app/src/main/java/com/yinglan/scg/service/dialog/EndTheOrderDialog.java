package com.yinglan.scg.service.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scg.R;

/**
 * 结束订单弹框
 */
public class EndTheOrderDialog extends BaseDialog implements View.OnClickListener {


    public EndTheOrderDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_endtheorder);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        ImageView img_cross = (ImageView) findViewById(R.id.img_cross);
        img_cross.setOnClickListener(this);
        TextView tv_context = (TextView) findViewById(R.id.tv_context);
        tv_context.setText(mContext.getString(R.string.finalizeOrder));
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:

                dismiss();
                break;
            case R.id.tv_determine:
                dismiss();
                break;
            case R.id.img_cross:
                dismiss();
                break;
        }
    }


}
