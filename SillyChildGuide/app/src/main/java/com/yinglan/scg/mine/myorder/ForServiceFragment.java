package com.yinglan.scg.mine.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myorder.ForServiceProcessingViewAdapter;
import com.yinglan.scg.adapter.mine.myorder.ForServiceWaitingViewAdapter;
import com.yinglan.scg.entity.mine.myorder.ForServiceBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.myorder.orderdetails.CharterOrderDetailsActivity;
import com.yinglan.scg.mine.myorder.orderdetails.LineOrderDetailsActivity;
import com.yinglan.scg.mine.myorder.orderdetails.PrivateCustomOrderDetailsActivity;
import com.yinglan.scg.mine.myorder.orderdetails.TransferOrderDetailsActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单---待服务
 * Created by Administrator on 2017/9/2.
 */
public class ForServiceFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, OrderContract.View {

    private MyOrderActivity aty;

    private ForServiceProcessingViewAdapter mAdapter;
    private ForServiceWaitingViewAdapter mAdapter1;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_processing)
    private ChildListView lv_processing;

    @BindView(id = R.id.lv_waiting)
    private ChildListView lv_waiting;
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

    private int selectedPosition = 0;
    private int selectedPosition1 = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_forservice, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new OrderPresenter(this);
        mAdapter = new ForServiceProcessingViewAdapter(aty);
        mAdapter1 = new ForServiceWaitingViewAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_processing.setAdapter(mAdapter);
        lv_processing.setOnItemClickListener(this);
        lv_waiting.setAdapter(mAdapter1);
        lv_waiting.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (adapterView.getId() == R.id.lv_processing) {
            selectedPosition = position;
            ((OrderContract.Presenter) mPresenter).getIsLogin(aty, 1);
        } else if (adapterView.getId() == R.id.lv_waiting) {
            selectedPosition1 = position;
            ((OrderContract.Presenter) mPresenter).getIsLogin(aty, 2);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderContract.Presenter) mPresenter).getProcessingGuideOrder(aty);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            ForServiceBean forServiceBean = (ForServiceBean) JsonUtil.getInstance().json2Obj(success, ForServiceBean.class);
            if (forServiceBean.getData() == null || forServiceBean.getData().getWaiting_list() == null && forServiceBean.getData().getProcessing_list() == null ||
                    forServiceBean.getData().getWaiting_list().size() <= 0 && forServiceBean.getData().getProcessing_list().size() <= 0 ||
                    forServiceBean.getData().getWaiting_list() == null && forServiceBean.getData().getProcessing_list().size() <= 0 ||
                    forServiceBean.getData().getWaiting_list().size() <= 0 && forServiceBean.getData().getProcessing_list() == null) {
                errorMsg(getString(R.string.noOrder), 0);
                return;
            }
            mRefreshLayout.endRefreshing();
            if (forServiceBean.getData().getProcessing_list() != null && forServiceBean.getData().getProcessing_list().size() > 0) {
                lv_processing.setVisibility(View.VISIBLE);
                mAdapter.clear();
                mAdapter.addNewData(forServiceBean.getData().getProcessing_list());
            } else {
                lv_processing.setVisibility(View.GONE);
            }
            if (forServiceBean.getData().getWaiting_list() != null && forServiceBean.getData().getWaiting_list().size() > 0) {
                lv_waiting.setVisibility(View.VISIBLE);
                mAdapter1.clear();
                mAdapter1.addNewData(forServiceBean.getData().getWaiting_list());
            } else {
                lv_waiting.setVisibility(View.GONE);
            }
            dismissLoadingDialog();
        } else if (flag == 1) {//订单详情
            Intent intent = new Intent();
            if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 1 || mAdapter.getItem(selectedPosition).getProduct_set_cd() == 2) {
                intent.setClass(aty, TransferOrderDetailsActivity.class);
            } else if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 3) {
                intent.setClass(aty, CharterOrderDetailsActivity.class);
            } else if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 4) {
                intent.setClass(aty, PrivateCustomOrderDetailsActivity.class);
            } else if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 5) {
                intent.setClass(aty, LineOrderDetailsActivity.class);
            }
            intent.putExtra("order_number", mAdapter.getItem(selectedPosition).getOrder_number());
            aty.showActivity(aty, intent);
        } else if (flag == 2) {//订单详情
            Intent intent = new Intent();
            if (mAdapter1.getItem(selectedPosition1).getProduct_set_cd() == 1 || mAdapter1.getItem(selectedPosition1).getProduct_set_cd() == 2) {
                intent.setClass(aty, TransferOrderDetailsActivity.class);
            } else if (mAdapter1.getItem(selectedPosition1).getProduct_set_cd() == 3) {
                intent.setClass(aty, CharterOrderDetailsActivity.class);
            } else if (mAdapter1.getItem(selectedPosition1).getProduct_set_cd() == 4) {
                intent.setClass(aty, PrivateCustomOrderDetailsActivity.class);
            } else if (mAdapter1.getItem(selectedPosition1).getProduct_set_cd() == 5) {
                intent.setClass(aty, LineOrderDetailsActivity.class);
            }
            intent.putExtra("order_number", mAdapter1.getItem(selectedPosition1).getOrder_number());
            aty.showActivity(aty, intent);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            mRefreshLayout.endRefreshing();
            mRefreshLayout.setPullDownRefreshEnable(false);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                // ViewInject.toast(getString(R.string.reloginPrompting));
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.noOrder))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else if (flag == 1) {
            if (isLogin(msg)) {
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null ||
                ((String) msgEvent.getData()).equals("RxBusEndTheOrderEvent") && mPresenter != null) {
            ((OrderContract.Presenter) mPresenter).getProcessingGuideOrder(aty);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }
}
