package com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea.search.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scg.R;

/**
 * 清空搜索
 */
public abstract class ClearSearchDialog extends BaseDialog implements View.OnClickListener {

    private int collectionId = 0;
    private String content = "";
    private TextView tv_content;

    public ClearSearchDialog(Context context, String content) {
        super(context, R.style.MyDialog);
        this.content = content;
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
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText(content);
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
                deleteCollectionDo(collectionId);
                break;
        }
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public abstract void deleteCollectionDo(int addressId);

}
