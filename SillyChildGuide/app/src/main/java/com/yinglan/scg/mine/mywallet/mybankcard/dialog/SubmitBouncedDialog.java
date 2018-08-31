package com.yinglan.scg.mine.mywallet.mybankcard.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yinglan.scg.R;


/**
 * 提现---提交弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class SubmitBouncedDialog extends Dialog implements View.OnClickListener {

    private String content = null;
    private Context context;
    private int id = 0;

    public SubmitBouncedDialog(Context context, String content) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clearcache);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(content);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                confirm(id);
                break;
        }
    }

    public void setId(int id) {
        this.id = id;
    }


    public abstract void confirm(int id);
}
