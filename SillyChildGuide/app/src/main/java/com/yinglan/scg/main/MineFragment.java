package com.yinglan.scg.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.main.UserInfoBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scg.mine.mywallet.MyWalletActivity;
import com.yinglan.scg.mine.personaldata.PersonalDataActivity;
import com.yinglan.scg.mine.setup.HelpCenterActivity;
import com.yinglan.scg.mine.setup.SetUpActivity;
import com.yinglan.scg.mine.sharepolite.SharePoliteActivity;
import com.yinglan.scg.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scg.constant.NumericConstants.REQUEST_CODE;
import static com.yinglan.scg.constant.URLConstants.COLLEGE;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_mineTop, click = true)
    private LinearLayout ll_mineTop;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_storesName)
    private TextView tv_storesName;

    @BindView(id = R.id.tv_nature)
    private TextView tv_nature;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    @BindView(id = R.id.ll_mineBot)
    private LinearLayout ll_mineBot;

    @BindView(id = R.id.tv_ordersTotal)
    private TextView tv_ordersTotal;

    @BindView(id = R.id.tv_storeLevel)
    private TextView tv_storeLevel;

    @BindView(id = R.id.tv_businessLevel)
    private TextView tv_businessLevel;

    @BindView(id = R.id.tv_notLogged)
    private TextView tv_notLogged;

    @BindView(id = R.id.tv_loginImmediately, click = true)
    private TextView tv_loginImmediately;

    @BindView(id = R.id.ll_myStores, click = true)
    private LinearLayout ll_myStores;

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
        mRefreshLayout.beginRefreshing();
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_mineTop:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_loginImmediately:
                errorMsg("", 1);
                break;
            case R.id.ll_myStores:
                int disabled = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "disabled", 3);
                if (disabled == -1) {
                    errorMsg(getString(R.string.recertification1), 2);
                    return;
                } else if (disabled == 0) {
                    errorMsg(getString(R.string.certificationProcess), 2);
                    return;
                } else if (disabled == 2) {
                    errorMsg(getString(R.string.accountNumberDisabled), 2);
                    return;
                } else if (disabled == 3) {
                    errorMsg(getString(R.string.youNotCertified), 2);
                    return;
                }
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.ll_myWallet:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
            case R.id.ll_sillyChildCollege:
                Intent intent = new Intent(aty, HelpCenterActivity.class);
                intent.putExtra("title", getString(R.string.sillyChildCollege));
                intent.putExtra("url", COLLEGE);
                aty.showActivity(aty, intent);
                //  aty.showActivity(aty, SillyChildCollegeActivity.class);
                break;
            case R.id.ll_sharePolite:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 4);
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
//            if (userInfoBean == null || userInfoBean.getData() == null || StringUtils.toInt(userInfoBean.getData().getStore_id(), 0) <= 0) {
//                errorMsg(getString(R.string.serverReturnsDataError), 0);
//                return;
//            }
            tv_notLogged.setVisibility(View.GONE);
            tv_loginImmediately.setVisibility(View.GONE);
            ll_mineTop.setVisibility(View.VISIBLE);
            tv_divider.setVisibility(View.VISIBLE);
            ll_mineBot.setVisibility(View.VISIBLE);
            saveUserInfo(userInfoBean);
            if (StringUtils.isEmpty(userInfoBean.getData().getFace())) {
                img_head.setImageResource(R.mipmap.avatar);
            } else {
                GlideImageLoader.glideLoader(aty, userInfoBean.getData().getFace(), img_head, 0, R.mipmap.avatar);
            }
            String mobile = PreferenceHelper.readString(aty, StringConstants.FILENAME, "mobile");
            if (!StringUtils.isEmpty(userInfoBean.getData().getNickname())) {
                tv_storesName.setText(userInfoBean.getData().getNickname());
            } else {
                tv_storesName.setText(mobile);
            }
            int disabled = StringUtils.toInt(userInfoBean.getData().getDisabled(), 3);
            if (disabled == 3) {
                tv_nature.setVisibility(View.GONE);
            } else if (disabled == -1) {
                tv_nature.setVisibility(View.VISIBLE);
                tv_nature.setText(getString(R.string.unapprove));
                tv_nature.setTextColor(getResources().getColor(R.color.fF0000Colors));
                setCurrentUserInfo(userInfoBean.getData().getFace(), tv_storesName.getText().toString());
            } else if (disabled == 0) {
                tv_nature.setVisibility(View.VISIBLE);
                tv_nature.setText(getString(R.string.audit1));
                tv_nature.setTextColor(getResources().getColor(R.color.textColor));
                setCurrentUserInfo(userInfoBean.getData().getFace(), tv_storesName.getText().toString());
            } else if (disabled == 1) {
                tv_nature.setVisibility(View.VISIBLE);
              //  tv_nature.setText(getString(R.string.merchants));
                tv_nature.setTextColor(getResources().getColor(R.color.greenColors));
                if (!StringUtils.isEmpty(userInfoBean.getData().getStore_name())) {
                    setCurrentUserInfo(userInfoBean.getData().getStore_logo(), userInfoBean.getData().getStore_name());
                } else {
                    setCurrentUserInfo(userInfoBean.getData().getFace(), tv_storesName.getText().toString());
                }
            } else if (disabled == 2) {
                tv_nature.setVisibility(View.VISIBLE);
                tv_nature.setText(getString(R.string.disabled1));
                tv_nature.setTextColor(getResources().getColor(R.color.fF0000Colors));
                if (!StringUtils.isEmpty(userInfoBean.getData().getStore_name())) {
                    setCurrentUserInfo(userInfoBean.getData().getStore_logo(), userInfoBean.getData().getStore_name());
                } else {
                    setCurrentUserInfo(userInfoBean.getData().getFace(), tv_storesName.getText().toString());
                }
            }
            if (!StringUtils.isEmpty(userInfoBean.getData().getOrder_total())) {
                tv_ordersTotal.setText(userInfoBean.getData().getOrder_total());
            }
            if (!StringUtils.isEmpty(userInfoBean.getData().getStore_level())) {
                tv_storeLevel.setText(userInfoBean.getData().getStore_level());
            }
            if (!StringUtils.isEmpty(userInfoBean.getData().getLv_id())) {
                tv_businessLevel.setText(userInfoBean.getData().getLv_id());
            }

        } else if (flag == 1) {
            Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
            // 获取内容
            // 设置结果 结果码，一个数据
            startActivityForResult(personalDataIntent, REQUEST_CODE);
        } else if (flag == 2) {
         //   aty.showActivity(aty, MyStoresActivity.class);
        } else if (flag == 3) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 4) {
            aty.showActivity(aty, SharePoliteActivity.class);
        } else if (flag == 6) {
            Intent intent = new Intent(aty, MainActivity.class);
            intent.putExtra("newChageIcon", 0);
            aty.showActivity(aty, intent);
        }
        dismissLoadingDialog();
    }

    /**
     * 用户信息本地化
     */
    private void saveUserInfo(UserInfoBean userInfoBean) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_name", userInfoBean.getData().getStore_name());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_id", userInfoBean.getData().getStore_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "disabled", StringUtils.toInt(userInfoBean.getData().getDisabled(), 3));
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_logo", userInfoBean.getData().getStore_logo());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "order_total", userInfoBean.getData().getOrder_total());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_level", userInfoBean.getData().getStore_level());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "lv_id", userInfoBean.getData().getLv_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", userInfoBean.getData().getNickname());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "lv_name", userInfoBean.getData().getLv_name());
    }


    private void setCurrentUserInfo(String imgUrl, String name) {
        if (RongIM.getInstance() != null && !StringUtils.isEmpty(name)) {
            String userid = UserUtil.getRcId(aty);
            UserInfo userInfo = new UserInfo(userid, name, Uri.parse(imgUrl));
            RongIM.getInstance().setCurrentUserInfo(userInfo);
            RongIM.getInstance().setMessageAttachedUserInfo(true);
        }
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
        ll_mineTop.setVisibility(View.GONE);
        tv_divider.setVisibility(View.GONE);
        ll_mineBot.setVisibility(View.GONE);
        tv_notLogged.setVisibility(View.VISIBLE);
        tv_loginImmediately.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MineContract.Presenter) mPresenter).getInfo(aty);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            ((MineContract.Presenter) mPresenter).getInfo(aty);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
            ((MineContract.Presenter) mPresenter).getInfo(aty);
        } else if (((String) msgEvent.getData()).equals("RxBusAvatarEvent") && mPresenter != null) {
            String avatar = PreferenceHelper.readString(aty, StringConstants.FILENAME, "avatar", "");
            if (!StringUtils.isEmpty(avatar)) {
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait, 0);
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait1, 0);
            }
        }
    }

}
