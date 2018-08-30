package com.yinglan.scg.mine.personaldata.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yinglan.scg.R;

/**
 * 选择图片的来源
 * Created by Administrator on 2018/5/8.
 */

public abstract class PictureSourceDialog extends Dialog implements View.OnClickListener {

    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_picturesource);
        initView();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

    }

    private void initView() {
        TextView tv_takephoto = (TextView) findViewById(R.id.tv_takephoto);
        tv_takephoto.setOnClickListener(this);
        TextView tv_choosefromalbum = (TextView) findViewById(R.id.tv_choosefromalbum);
        tv_choosefromalbum.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    public PictureSourceDialog(Activity context) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_takephoto:
                dismiss();
                takePhoto();
                break;
            case R.id.tv_choosefromalbum:
                dismiss();
                chooseFromAlbum();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    public abstract void takePhoto();

    public abstract void chooseFromAlbum();

}
