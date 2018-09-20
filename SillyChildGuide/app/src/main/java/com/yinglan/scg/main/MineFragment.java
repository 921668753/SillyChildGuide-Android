package com.yinglan.scg.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.myview.ObservableScrollView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.main.UserInfoBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scg.mine.myorder.MyOrderActivity;
import com.yinglan.scg.mine.myvehicle.MyVehicleActivity;
import com.yinglan.scg.mine.mywallet.MyWalletActivity;
import com.yinglan.scg.mine.personaldata.PersonalDataActivity;
import com.yinglan.scg.mine.setup.HelpCenterActivity;
import com.yinglan.scg.mine.setup.SetUpActivity;
import com.yinglan.scg.mine.sharepolite.SharePoliteActivity;
import com.yinglan.scg.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE;
import static com.yinglan.scg.constant.URLConstants.COLLEGE;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */

public class MineFragment extends BaseFragment implements MineContract.View, ObservableScrollView.ScrollViewListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.sv_mine)
    private ObservableScrollView sv_mine;

    @BindView(id = R.id.rl_title)
    private RelativeLayout rl_title;

    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.ll_notLogin, click = true)
    private LinearLayout ll_notLogin;

    @BindView(id = R.id.tv_editData1, click = true)
    private TextView tv_editData1;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    @BindView(id = R.id.tv_editData, click = true)
    private TextView tv_editData;

    @BindView(id = R.id.tv_minetouxiang)
    private TextView tv_minetouxiang;

    @BindView(id = R.id.iv_minetouxiang, click = true)
    private ImageView iv_minetouxiang;

    @BindView(id = R.id.tv_nickname, click = true)
    private TextView tv_nickname;

    @BindView(id = R.id.tv_serialNumber)
    private TextView tv_serialNumber;

    @BindView(id = R.id.tv_ordersTotal)
    private TextView tv_ordersTotal;

    @BindView(id = R.id.tv_guideLevel)
    private TextView tv_guideLevel;

    @BindView(id = R.id.tv_serviceLevel)
    private TextView tv_serviceLevel;


    @BindView(id = R.id.ll_myVehicle, click = true)
    private LinearLayout ll_myVehicle;

    @BindView(id = R.id.ll_myOrder, click = true)
    private LinearLayout ll_myOrder;

    @BindView(id = R.id.ll_myWallet, click = true)
    private LinearLayout ll_myWallet;

    @BindView(id = R.id.ll_sillyChildCollege, click = true)
    private LinearLayout ll_sillyChildCollege;

    @BindView(id = R.id.ll_sharePolite, click = true)
    private LinearLayout ll_sharePolite;


    @BindView(id = R.id.ll_setUp, click = true)
    private LinearLayout ll_setUp;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MinePresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        sv_mine.setScrollViewListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_editData:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_editData1:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.iv_minetouxiang:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.ll_notLogin:
                aty.showActivity(aty, LoginActivity.class);
                break;
            case R.id.ll_myVehicle:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.ll_myOrder:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
            case R.id.ll_myWallet:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 4);
                break;
            case R.id.ll_sillyChildCollege:
                Intent intent = new Intent(aty, HelpCenterActivity.class);
                intent.putExtra("title", getString(R.string.sillyChildCollege));
                intent.putExtra("url", COLLEGE);
                aty.showActivity(aty, intent);
                break;
            case R.id.ll_sharePolite:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 5);
                break;
            case R.id.ll_setUp:
                aty.showActivity(aty, SetUpActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        if (flag == 0) {
            Log.e("用户信息", "结果：" + success);
            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
            if (userInfoBean != null && userInfoBean.getData() != null) {
                ll_notLogin.setVisibility(View.GONE);
                tv_editData.setVisibility(View.VISIBLE);
                tv_editData1.setVisibility(View.VISIBLE);
                tv_minetouxiang.setVisibility(View.VISIBLE);
                iv_minetouxiang.setVisibility(View.VISIBLE);
                tv_nickname.setVisibility(View.VISIBLE);
                tv_serialNumber.setVisibility(View.VISIBLE);
                saveUserInfo(userInfoBean);
                tv_nickname.setText(userInfoBean.getData().getNickname());
                if (StringUtils.isEmpty(userInfoBean.getData().getFace())) {
                    iv_minetouxiang.setImageResource(R.mipmap.avatar);
                } else {
                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getFace(), iv_minetouxiang, 0, R.mipmap.avatar);
                }
                String shz = PreferenceHelper.readString(aty, StringConstants.FILENAME, "shz", "");
                tv_serialNumber.setText(shz);
                tv_ordersTotal.setText(userInfoBean.getData().getOrder_number() + "");
                tv_guideLevel.setText(userInfoBean.getData().getGuide_level_name());
                tv_guideLevel.setTextColor(Color.parseColor("#" + userInfoBean.getData().getGuide_level_color()));
                tv_serviceLevel.setText(userInfoBean.getData().getService_level());
            }
        } else if (flag == 1) {
            Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
            // 获取内容
            // 设置结果 结果码，一个数据
            startActivityForResult(personalDataIntent, REQUEST_CODE);
        } else if (flag == 2) {
            aty.showActivity(aty, MyVehicleActivity.class);
        } else if (flag == 3) {
            aty.showActivity(aty, MyOrderActivity.class);
        } else if (flag == 4) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 5) {
            aty.showActivity(aty, SharePoliteActivity.class);
        }
        dismissLoadingDialog();
    }


    /**
     * 用户信息本地化
     */
    private void saveUserInfo(UserInfoBean userInfoBean) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "model_status", userInfoBean.getData().isModel_status());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "approve_status", userInfoBean.getData().getApprove_status());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "guide_level_name", userInfoBean.getData().getGuide_level_name());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "guide_level_color", userInfoBean.getData().getGuide_level_color());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", userInfoBean.getData().getNickname());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "order_number", userInfoBean.getData().getOrder_number());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", userInfoBean.getData().getSex());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "remark", userInfoBean.getData().getRemark());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "service_level", userInfoBean.getData().getService_level());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "invite_code", userInfoBean.getData().getInvite_code());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(false);
        dismissLoadingDialog();
        if (isLogin(msg) && flag == 0) {
            initDefaultInfo();
            return;
        } else if (isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 将显示的个人信息设置到默认状态
     */
    private void initDefaultInfo() {
        UserUtil.clearUserInfo(aty);
        tv_editData.setVisibility(View.GONE);
        tv_editData1.setVisibility(View.GONE);
        tv_minetouxiang.setVisibility(View.GONE);
        iv_minetouxiang.setVisibility(View.GONE);
        tv_nickname.setVisibility(View.GONE);
        tv_serialNumber.setVisibility(View.GONE);
        ll_notLogin.setVisibility(View.VISIBLE);
        tv_ordersTotal.setText("0");
        tv_guideLevel.setText("");
        tv_serviceLevel.setText("0");
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MinePresenter) mPresenter).getInfo(aty);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 0) {
            rl_title.setBackgroundColor(Color.TRANSPARENT);
            //                          设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.TRANSPARENT);
            tv_editData1.setTextColor(Color.TRANSPARENT);
            tv_divider.setBackgroundColor(Color.TRANSPARENT);
            Log.e("111", "y <= 0");
        } else if (scrollY > 0 && scrollY <= 200) {
            float scale = (float) scrollY / 200;
            float alpha = (255 * scale);
            // 只是layout背景透明(仿知乎滑动效果)白色透明
            rl_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            //    设置文字颜色，黑色，加透明度
            tv_title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            tv_editData1.setTextColor(Color.argb((int) alpha, 0, 0, 0));
            tv_divider.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
            Log.e("111", "y > 0 && y <= imageHeight");
        } else {
//                          白色不透明
            rl_title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
            //                          设置文字颜色
            //黑色
            tv_title.setTextColor(Color.argb((int) 255, 0, 0, 0));
            tv_editData1.setTextColor(Color.argb((int) 255, 0, 0, 0));
            tv_divider.setBackgroundColor(getResources().getColor(R.color.dividercolors2));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            ((MinePresenter) mPresenter).getInfo(aty);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusMineFragmentEvent") && mPresenter != null) {
            ((MinePresenter) mPresenter).getInfo(aty);
        }
    }

}
