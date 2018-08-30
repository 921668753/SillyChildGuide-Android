package com.yinglan.scg.main;

import android.content.Intent;
import android.os.Bundle;
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
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.utils.GlideImageLoader;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 首页
 * Created by Admin on 2017/8/10.
 */
public class HomePageFragment extends BaseFragment  {

    private MainActivity aty;

//    @BindView(id = R.id.img_departmentGuide, click = true)
//    private ImageView img_departmentGuide;
//
//    @BindView(id = R.id.ll_guide, click = true)
//    private LinearLayout ll_guide;
//
//    @BindView(id = R.id.img_guideLogo)
//    private ImageView img_guideLogo;
//
//    @BindView(id = R.id.tv_guideName)
//    private TextView tv_guideName;
//
//    @BindView(id = R.id.tv_guideNum)
//    private TextView tv_guideNum;
//
//    @BindView(id = R.id.img_guideCertified)
//    private ImageView img_guideCertified;
//
//    @BindView(id = R.id.img_asmanager, click = true)
//    private ImageView img_asmanager;
//
//    @BindView(id = R.id.ll_store, click = true)
//    private LinearLayout ll_store;
//
//    @BindView(id = R.id.img_storeLogo)
//    private ImageView img_storeLogo;
//
//    @BindView(id = R.id.tv_storeName)
//    private TextView tv_storeName;
//
//    @BindView(id = R.id.tv_shopNum)
//    private TextView tv_shopNum;
//
//    @BindView(id = R.id.img_shopCertified)
//    private ImageView img_shopCertified;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homepage, null);
    }

//    @Override
//    protected void initData() {
//        super.initData();
//     //   mPresenter = new HomePagePresenter(this);
//        showLoadingDialog(getString(R.string.dataLoad));
//        ((HomePageContract.Presenter) mPresenter).getHomePage(aty);
//    }
//
//    @Override
//    protected void initWidget(View parentView) {
//        super.initWidget(parentView);
//        img_departmentGuide.setVisibility(View.VISIBLE);
//        ll_guide.setVisibility(View.GONE);
//        img_asmanager.setVisibility(View.VISIBLE);
//        ll_store.setVisibility(View.GONE);
//    }
//
//    /**
//     * @param v 控件监听事件
//     */
//    @Override
//    protected void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.img_departmentGuide:
//                ((HomePageContract.Presenter) mPresenter).getIsLogin(aty, 1);
//                break;
//            case R.id.ll_guide:
//
//                ((HomePageContract.Presenter) mPresenter).getIsLogin(aty, 2);
//                break;
//            case R.id.img_asmanager:
//                ((HomePageContract.Presenter) mPresenter).getIsLogin(aty, 3);
//                break;
//            case R.id.ll_store:
//                ((HomePageContract.Presenter) mPresenter).getIsLogin(aty, 4);
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public void setPresenter(HomePageContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public void getSuccess(String success, int flag) {
//        if (flag == 0) {
//            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
//            if (userInfoBean != null && userInfoBean.getData() != null) {
//                saveUserInfo(userInfoBean);
//                int disabled = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "disabled", 3);
//                if (disabled == -1) {
//                    img_departmentGuide.setVisibility(View.VISIBLE);
//                    ll_guide.setVisibility(View.GONE);
//                    img_asmanager.setVisibility(View.GONE);
//                    ll_store.setVisibility(View.VISIBLE);
//                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getStore_logo(), img_storeLogo, 0, R.mipmap.avatar);
//                    tv_storeName.setText(userInfoBean.getData().getStore_name());
//                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
//                    img_shopCertified.setImageResource(R.mipmap.home_not_through);
//                } else if (disabled == 0) {
//                    img_departmentGuide.setVisibility(View.VISIBLE);
//                    ll_guide.setVisibility(View.GONE);
//                    img_asmanager.setVisibility(View.GONE);
//                    ll_store.setVisibility(View.VISIBLE);
//                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getStore_logo(), img_storeLogo, 0, R.mipmap.avatar);
//                    tv_storeName.setText(userInfoBean.getData().getStore_name());
//                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
//                    img_shopCertified.setImageResource(R.mipmap.home_under_review);
//                } else if (disabled == 1) {
//                    img_departmentGuide.setVisibility(View.VISIBLE);
//                    ll_guide.setVisibility(View.GONE);
//                    img_asmanager.setVisibility(View.GONE);
//                    ll_store.setVisibility(View.VISIBLE);
//                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getStore_logo(), img_storeLogo, 0, R.mipmap.avatar);
//                    tv_storeName.setText(userInfoBean.getData().getStore_name());
//                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
//                    img_shopCertified.setImageResource(R.mipmap.home_certified);
//                } else if (disabled == 2) {
//                    img_departmentGuide.setVisibility(View.VISIBLE);
//                    ll_guide.setVisibility(View.GONE);
//                    img_asmanager.setVisibility(View.GONE);
//                    ll_store.setVisibility(View.VISIBLE);
//                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getStore_logo(), img_storeLogo, 0, R.mipmap.avatar);
//                    tv_storeName.setText(userInfoBean.getData().getStore_name());
//                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
//                    img_shopCertified.setImageResource(R.mipmap.home_disabled);
//                } else {
//                    img_departmentGuide.setVisibility(View.VISIBLE);
//                    ll_guide.setVisibility(View.GONE);
//                    img_asmanager.setVisibility(View.VISIBLE);
//                    ll_store.setVisibility(View.GONE);
//                }
//            } else {
//                img_departmentGuide.setVisibility(View.VISIBLE);
//                ll_guide.setVisibility(View.GONE);
//                img_asmanager.setVisibility(View.VISIBLE);
//                ll_store.setVisibility(View.GONE);
//            }
//        } else if (flag == 1) {
//            Intent intent = new Intent(aty, GuideCertificationActivity.class);
//            startActivityForResult(intent, RESULT_CODE_GET);
//        } else if (flag == 2) {
//            int disabled = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "disabled", 3);
//            if (disabled == 1 || disabled == 2) {
//                return;
//            }
//            Intent intent = new Intent(aty, GuideCertificationActivity.class);
//            startActivityForResult(intent, RESULT_CODE_GET);
//        } else if (flag == 3) {
//            Intent intent = new Intent(aty, StoreCertificationActivity.class);
//            startActivityForResult(intent, RESULT_CODE_GET);
//        } else if (flag == 4) {
//            int disabled = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "disabled", 3);
//            if (disabled == 1 || disabled == 2) {
//                return;
//            }
//            Intent intent = new Intent(aty, StoreCertificationActivity.class);
//            startActivityForResult(intent, RESULT_CODE_GET);
//        }
//        dismissLoadingDialog();
//    }
//
//    /**
//     * 用户信息本地化
//     */
//    private void saveUserInfo(UserInfoBean userInfoBean) {
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_name", userInfoBean.getData().getStore_name());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_id", userInfoBean.getData().getStore_id());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "disabled", StringUtils.toInt(userInfoBean.getData().getDisabled(), 3));
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_logo", userInfoBean.getData().getStore_logo());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "order_total", userInfoBean.getData().getOrder_total());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_level", userInfoBean.getData().getStore_level());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "lv_id", userInfoBean.getData().getLv_id());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", userInfoBean.getData().getNickname());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "lv_name", userInfoBean.getData().getLv_name());
//    }
//
//
//    @Override
//    public void errorMsg(String msg, int flag) {
//        dismissLoadingDialog();
//        if (flag == 1 && isLogin(msg) || flag == 2 && isLogin(msg) || flag == 3 && isLogin(msg) || flag == 4 && isLogin(msg)) {
//            aty.showActivity(aty, LoginActivity.class);
//            return;
//        }
//        if (flag == 0 && isLogin(msg)) {
//            img_departmentGuide.setVisibility(View.VISIBLE);
//            ll_guide.setVisibility(View.GONE);
//            img_asmanager.setVisibility(View.VISIBLE);
//            ll_store.setVisibility(View.GONE);
//            return;
//        }
//        ViewInject.toast(msg);
//    }
//
//    @Override
//    public void onChange() {
//        super.onChange();
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case RESULT_CODE_GET:
//                if (resultCode == RESULT_OK && data != null) {
//                    ((HomePageContract.Presenter) mPresenter).getHomePage(aty);
//                }
//                break;
//        }
//    }
//
//    /**
//     * 在接收消息的时候，选择性接收消息：
//     */
//    @Override
//    public void callMsgEvent(MsgEvent msgEvent) {
//        super.callMsgEvent(msgEvent);
//        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
//            //    et_enterNameStore.setText("");
//            ((HomePageContract.Presenter) mPresenter).getHomePage(aty);
//        }
//    }

}
