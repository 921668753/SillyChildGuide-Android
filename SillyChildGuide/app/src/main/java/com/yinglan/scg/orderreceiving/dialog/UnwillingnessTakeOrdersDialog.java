package com.yinglan.scg.orderreceiving.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseDialog;
import com.yinglan.scg.R;

/**
 * 不愿意接单弹框
 */
public class UnwillingnessTakeOrdersDialog extends BaseDialog implements View.OnClickListener ,AdapterView.OnItemClickListener{

    private ListView lv_reason;

    public UnwillingnessTakeOrdersDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_unwillingnesstakeorders);
        initView();
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
    }

    private void initView() {
        ImageView img_cross = (ImageView) findViewById(R.id.img_cross);
        img_cross.setOnClickListener(this);
        lv_reason = (ListView) findViewById(R.id.lv_reason);
        lv_reason.setOnItemClickListener( this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_cross:
                dismiss();
                break;
        }
    }


    @Override
    public void show() {
        super.show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
