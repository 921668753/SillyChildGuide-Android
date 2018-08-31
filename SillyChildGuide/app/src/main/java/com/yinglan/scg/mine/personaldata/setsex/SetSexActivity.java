package com.yinglan.scg.mine.personaldata.setsex;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.yinglan.scg.R;
import com.yinglan.scg.loginregister.LoginActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 设置性别
 */
public class SetSexActivity extends BaseActivity implements SetSexContract.View {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.img_nan, click = true)
    private ImageView img_nan;

    @BindView(id = R.id.img_nv, click = true)
    private ImageView img_nv;

    private int sex = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setsex);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SetSexPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        sex = getIntent().getIntExtra("sex", 0);
        if (sex == 0 || sex == 1) {
            sex = 1;
            img_nan.setImageResource(R.mipmap.set_gender_check_the_number);
            img_nv.setImageDrawable(null);
        } else {
            img_nan.setImageDrawable(null);
            img_nv.setImageResource(R.mipmap.set_gender_check_the_number);
        }
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(R.string.setSex);
        titlebar.setRightText(R.string.complete);
        titlebar.getRightCtv().setTextColor(getResources().getColor(R.color.greenColors));
        titlebar.getRightCtv().setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                showLoadingDialog(getString(R.string.saveLoad));
                ((SetSexContract.Presenter) mPresenter).setSex(sex);
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_nan:
                sex = 1;
                img_nan.setImageResource(R.mipmap.set_gender_check_the_number);
                img_nv.setImageDrawable(null);
                break;
            case R.id.img_nv:
                sex = 2;
                img_nan.setImageDrawable(null);
                img_nv.setImageResource(R.mipmap.set_gender_check_the_number);
                break;
        }
    }

    @Override
    public void setPresenter(SetSexContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        Intent intent = getIntent().putExtra("sex", sex);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


}
