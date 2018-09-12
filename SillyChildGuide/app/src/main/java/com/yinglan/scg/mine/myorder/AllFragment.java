package com.yinglan.scg.mine.myorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.kymjs.common.Log;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myorder.OrderViewAdapter;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.entity.mine.myorder.OrderBean;
import com.yinglan.scg.loginregister.LoginActivity;

import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 我的订单---全部
 * Created by Administrator on 2017/9/2.
 */
public class AllFragment extends BaseFragment implements AdapterView.OnItemClickListener, EasyPermissions.PermissionCallbacks, BGARefreshLayout.BGARefreshLayoutDelegate, OrderContract.View, BGAOnItemChildClickListener {

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
        mAdapter.setOnItemChildClickListener(this);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
//        if (mAdapter.getItem(i).getProduct_set_cd() == 1) {
//            intent.setClass(aty, AirportPickupOrderDetailsActivity.class);
//        } else if (mAdapter.getItem(i).getProduct_set_cd() == 2) {
//            intent.setClass(aty, AirportDropOffOrderDetailsActivity.class);
//        } else if (mAdapter.getItem(i).getProduct_set_cd() == 3) {
//            intent.setClass(aty, CharterOrderDetailsActivity.class);
//        } else if (mAdapter.getItem(i).getProduct_set_cd() == 4) {
//            intent.setClass(aty, PrivateCustomOrderDetailsActivity.class);
//        } else if (mAdapter.getItem(i).getProduct_set_cd() == 5) {
//            intent.setClass(aty, BoutiqueLineOrderDetailsActivity.class);
//        }
//        intent.putExtra("order_number", mAdapter.getItem(i).getOrder_number());
        aty.showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
//        bean = mAdapter.getItem(position);
//        switch (childView.getId()) {
//            case R.id.tv_confirmPayment:
//                ((OrderContract.Presenter) mPresenter).getIsLogin(aty, 1);
//                break;
//            case R.id.tv_callUp:
//                choiceLocationWrapper();
//                break;
//            case R.id.tv_sendPrivateChat:
//                ((OrderContract.Presenter) mPresenter).getIsLogin(aty, 2);
//                break;
//            case R.id.tv_appraiseOrder:
//                Intent intent1 = new Intent(aty, CommentActivity.class);
//                intent1.putExtra("order_number", bean.getOrder_number());
//                aty.showActivity(aty, intent1);
//                break;
//            case R.id.tv_additionalComments:
//                Intent intent2 = new Intent(aty, AdditionalCommentsActivity.class);
//                intent2.putExtra("order_id", bean.getOrder_id());
////                intent1.putExtra("order_number", bean.getOrder_number());
////                intent1.putExtra("pay_amount", bean.getPay_amount());
////                intent1.putExtra("type", bean.getProduct_set_cd());
////                intent1.putExtra("start_time", bean.getStart_time());
////                intent1.putExtra("end_time", bean.getEnd_time());
//                aty.showActivity(aty, intent2);
//                break;
//        }
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
        } else if (flag == 1) {//确认结束订单
//            Intent intent = new Intent(aty, PaymentTravelOrderActivity.class);
//            intent.putExtra("order_id", bean.getOrder_id());
//            intent.putExtra("order_number", bean.getOrder_number());
//            intent.putExtra("pay_amount", bean.getPay_amount());
//            intent.putExtra("type", bean.getProduct_set_cd());
//            intent.putExtra("start_time", bean.getStart_time());
//            intent.putExtra("end_time", bean.getEnd_time());
//            aty.showActivity(aty, intent);
//        } else if (flag == 2) {
//            RongIMUtil.connectRongIM(aty);
//            RongIM.getInstance().startConversation(aty, Conversation.ConversationType.PRIVATE, bean.getRong_id(), bean.getService_director());
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
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
        } else if (flag == 1 || flag == 2) {
            dismissLoadingDialog();
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


    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    private void choiceLocationWrapper() {
//        String[] perms = {Manifest.permission.CALL_PHONE};
//        if (EasyPermissions.hasPermissions(aty, perms)) {
//            if (servicePhoneDialog == null) {
//                servicePhoneDialog = new ServicePhoneDialog(aty);
//            }
//            if (servicePhoneDialog != null && !servicePhoneDialog.isShowing()) {
//                servicePhoneDialog.show();
//                servicePhoneDialog.setPhone(bean.getPhone());
//            }
//            return;
//        }
//        EasyPermissions.requestPermissions(this, getString(R.string.callSwitch), NumericConstants.READ_AND_WRITE_CODE, perms);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            //    ViewInject.toast(getString(R.string.callPermission));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (servicePhoneDialog != null && servicePhoneDialog.isShowing()) {
//            servicePhoneDialog.cancel();
//        }
//        servicePhoneDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }
}
