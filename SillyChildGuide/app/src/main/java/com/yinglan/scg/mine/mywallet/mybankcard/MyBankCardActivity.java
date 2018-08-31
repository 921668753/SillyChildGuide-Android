package com.yinglan.scg.mine.mywallet.mybankcard;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.mywallet.mybankcard.MyBankCardViewAdapter;
import com.yinglan.scg.entity.mine.mywallet.mybankcard.MyBankCardBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.mywallet.mybankcard.dialog.SubmitBouncedDialog;

import cn.bingoogolapple.titlebar.BGATitleBar;

import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE_CHOOSE_PHOTO;


/**
 * 我的银行卡
 * Created by Administrator on 2017/11/30.
 */

public class MyBankCardActivity extends BaseActivity implements MyBankCardContract.View, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    /**
     * 银行卡列表
     */
    @BindView(id = R.id.lv_bankCard)
    private ListView lv_bankCard;

    private MyBankCardViewAdapter myBankCardViewAdapter;

    private int type = 0;

    private SubmitBouncedDialog submitBouncedDialog = null;

    private int removePosition = 0;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mybankcard);
    }

    @Override
    public void initData() {
        super.initData();
        myBankCardViewAdapter = new MyBankCardViewAdapter(this);
        mPresenter = new MyBankCardPresenter(this);
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        lv_bankCard.setAdapter(myBankCardViewAdapter);
        lv_bankCard.setOnItemClickListener(this);
        lv_bankCard.setOnItemLongClickListener(this);
        initDialog();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    showLoadingDialog(getString(R.string.dataLoad));
                    ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
                    return;
                }
                break;
        }
    }

    private void initDialog() {
        submitBouncedDialog = new SubmitBouncedDialog(aty, getString(R.string.deleteCard)) {
            @Override
            public void confirm(int id) {
                showLoadingDialog(getString(R.string.deleteLoad));
                ((MyBankCardContract.Presenter) mPresenter).postRemoveBank(myBankCardViewAdapter.getItem(removePosition).getId());
            }
        };
    }

    private void initTitle() {
        if (type == 1) {
            titlebar.setTitleText(R.string.selectBankCard);
        } else {
            titlebar.setTitleText(R.string.myBankCard);
        }
        titlebar.setRightDrawable(R.mipmap.bank_card_plus_sign);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                Intent intent = new Intent(aty, AddBankCardActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type == 1) {
            removePosition = position;
            ((MyBankCardContract.Presenter) mPresenter).postPurseDefault(myBankCardViewAdapter.getItem(removePosition).getId());
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (submitBouncedDialog == null) {
            initDialog();
        }
        submitBouncedDialog.show();
        removePosition = position;
        submitBouncedDialog.setId(removePosition);
        return true;
    }


    @Override
    public void setPresenter(MyBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ll_commonError.setVisibility(View.GONE);
            lv_bankCard.setVisibility(View.VISIBLE);
            MyBankCardBean myBankCardBean = (MyBankCardBean) JsonUtil.getInstance().json2Obj(success, MyBankCardBean.class);
            if (myBankCardBean.getData() == null || myBankCardBean.getData().size() <= 0) {
                errorMsg(getString(R.string.notAddedBankCard), 0);
                return;
            }
            myBankCardViewAdapter.clear();
            myBankCardViewAdapter.addNewData(myBankCardBean.getData());
        } else if (flag == 1) {
            if (submitBouncedDialog != null && submitBouncedDialog.isShowing()) {
                submitBouncedDialog.dismiss();
            }
            myBankCardViewAdapter.removeItem(removePosition);
        } else if (flag == 2) {
            MyBankCardBean.DataBean dataBean = myBankCardViewAdapter.getItem(removePosition);
            Intent intent = getIntent();
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
            return;
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            lv_bankCard.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                skipActivity(this, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
                return;
            } else if (msg.contains(getString(R.string.notAddedBankCard))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
                return;
            }
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
            return;
        }
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myBankCardViewAdapter.clear();
        myBankCardViewAdapter = null;
        if (submitBouncedDialog != null) {
            submitBouncedDialog.cancel();
        }
        submitBouncedDialog = null;
    }

}
