package com.yinglan.scg.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.main.OrderReceivingViewAdapter;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.entity.main.OrderReceivingBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.orderreceiving.CharterDetailsActivity;
import com.yinglan.scg.orderreceiving.LineDetailsActivity;
import com.yinglan.scg.orderreceiving.MissedOrdersActivity;
import com.yinglan.scg.orderreceiving.PrivateCustomDetailsActivity;
import com.yinglan.scg.orderreceiving.TransferDetailsActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 接单
 * Created by Admin on 2018/8/10.
 */
public class OrderReceivingFragment extends BaseFragment implements OrderReceivingContract.View, BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {

    private MainActivity aty;

    @BindView(id = R.id.tv_missedOrders, click = true)
    private TextView tv_missedOrders;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

    private OrderReceivingViewAdapter mAdapter;

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

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 总页码
     */
    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;
    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;
    private int selectedPosition = 0;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_orderreceiving, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mAdapter = new OrderReceivingViewAdapter(aty);
        mPresenter = new OrderReceivingPresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_missedOrders:
                ((OrderReceivingContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedPosition = position;
        ((OrderReceivingContract.Presenter) mPresenter).getIsLogin(aty, 2);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderReceivingContract.Presenter) mPresenter).getGuideOrderPage(aty, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            return false;
        }
        mMorePageNumber++;
        if (mMorePageNumber > totalPageNumber) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderReceivingContract.Presenter) mPresenter).getGuideOrderPage(aty, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(OrderReceivingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            OrderReceivingBean orderReceivingBean = (OrderReceivingBean) JsonUtil.getInstance().json2Obj(success, OrderReceivingBean.class);
            if (orderReceivingBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    orderReceivingBean.getData().getResultX() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    orderReceivingBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noOrder), 0);
                return;
            } else if (orderReceivingBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    orderReceivingBean.getData().getResultX() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    orderReceivingBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = orderReceivingBean.getData().getCurrentPageNo();
            totalPageNumber = orderReceivingBean.getData().getTotalPageCount();
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(orderReceivingBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(orderReceivingBean.getData().getResultX());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            aty.showActivity(aty, MissedOrdersActivity.class);
        } else if (flag == 2) {
            boolean model_status = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "model_status", false);
            if (!model_status) {
                errorMsg(getString(R.string.finishVehicleAuditFirst), 1);
                return;
            }
            Intent intent = new Intent();
            if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 1 || mAdapter.getItem(selectedPosition).getProduct_set_cd() == 2) {
                intent.setClass(aty, TransferDetailsActivity.class);
            } else if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 3) {
                intent.setClass(aty, CharterDetailsActivity.class);
            } else if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 4) {
                intent.setClass(aty, PrivateCustomDetailsActivity.class);
            } else if (mAdapter.getItem(selectedPosition).getProduct_set_cd() == 5) {
                intent.setClass(aty, LineDetailsActivity.class);
            }
            intent.putExtra("order_number", mAdapter.getItem(selectedPosition).getOrder_number());
            startActivityForResult(intent, RESULT_CODE_GET);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            dismissLoadingDialog();
            isShowLoadingMore = false;
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
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
        } else if (flag == 1 || flag == 2) {
            dismissLoadingDialog();
            if (isLogin(msg)) {
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        }
    }

    @Override
    public void onChange() {
        super.onChange();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_CODE_GET:
                if (resultCode == RESULT_OK && data != null) {
                    /**
                     * 发送消息
                     */
                    RxBus.getInstance().post(new MsgEvent<String>("RxBusServiceFragmentEvent"));
                    mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
                    ((OrderReceivingContract.Presenter) mPresenter).getGuideOrderPage(aty, mMorePageNumber);
                }
                break;
        }
    }


    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((OrderReceivingContract.Presenter) mPresenter).getGuideOrderPage(aty, mMorePageNumber);
        }
    }


}
