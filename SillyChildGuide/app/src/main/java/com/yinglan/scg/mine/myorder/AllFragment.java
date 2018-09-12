package com.yinglan.scg.mine.myorder;

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
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myorder.OrderViewAdapter;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.entity.mine.myorder.OrderBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.myorder.orderdetails.CharterOrderDetailsActivity;
import com.yinglan.scg.mine.myorder.orderdetails.LineOrderDetailsActivity;
import com.yinglan.scg.mine.myorder.orderdetails.PrivateCustomOrderDetailsActivity;
import com.yinglan.scg.mine.myorder.orderdetails.TransferOrderDetailsActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单---全部
 * Created by Administrator on 2017/9/2.
 */
public class AllFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, OrderContract.View {

    private MyOrderActivity aty;

    private OrderViewAdapter mAdapter;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

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

    private String status = "";

    private boolean isFist = true;
    private int selectedPosition = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MyOrderActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_order, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new OrderPresenter(this);
        mAdapter = new OrderViewAdapter(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        lv_order.setOnItemClickListener(this);
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
        selectedPosition = position;
        ((OrderContract.Presenter) mPresenter).getIsLogin(aty, 1);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderContract.Presenter) mPresenter).getMyOrderPage(aty, status, mMorePageNumber);
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
        ((OrderContract.Presenter) mPresenter).getMyOrderPage(aty, status, mMorePageNumber);
        return true;
    }

    @Override
    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            OrderBean orderBean = (OrderBean) JsonUtil.getInstance().json2Obj(success, OrderBean.class);
            if (orderBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    orderBean.getData().getResultX() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    orderBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noOrder), 0);
                return;
            } else if (orderBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    orderBean.getData().getResultX() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    orderBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            mMorePageNumber = orderBean.getData().getCurrentPageNo();
            totalPageNumber = orderBean.getData().getTotalPageCount();
            for (int i = 0; i < orderBean.getData().getResultX().size(); i++) {
                if (orderBean.getData().getResultX().get(i).getOrder_status() == 1 && !isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(1);
                    isFist = true;
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 1 && isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(0);
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 2 && isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(1);
                    isFist = false;
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 2 && !isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(0);
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 3 && isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(1);
                    isFist = false;
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 3 && !isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(0);
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 4 && !isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(1);
                    isFist = true;
                } else if (orderBean.getData().getResultX().get(i).getOrder_status() == 4 && isFist) {
                    orderBean.getData().getResultX().get(i).setIsFirst(0);
                }
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(orderBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(orderBean.getData().getResultX());
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
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
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
                aty.showActivity(aty, LoginActivity.class);
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
                ((String) msgEvent.getData()).equals("RxBusCharterCommentEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((OrderContract.Presenter) mPresenter).getMyOrderPage(aty, status, mMorePageNumber);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }
}
