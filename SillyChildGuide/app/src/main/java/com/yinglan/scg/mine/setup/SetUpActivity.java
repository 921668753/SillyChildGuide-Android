package com.yinglan.scg.mine.setup;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.entity.BaseResult;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.SystemTool;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.yinglan.scg.R;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.setup.dialog.ClearCacheDialog;
import com.yinglan.scg.mine.setup.feedback.FeedbackActivity;
import com.yinglan.scg.utils.FileNewUtil;

import java.io.File;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scg.constant.URLConstants.ABOUTUS;
import static com.yinglan.scg.constant.URLConstants.HELP;

/**
 * 设置
 * Created by Administrator on 2017/9/2.
 */

public class SetUpActivity extends BaseActivity implements SetUpContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(id = R.id.tv_logOut, click = true)
    private TextView tv_logOut;

    @BindView(id = R.id.ll_help, click = true)
    private LinearLayout ll_help;

    @BindView(id = R.id.ll_aboutus, click = true)
    private LinearLayout ll_aboutus;


    @BindView(id = R.id.ll_versionNumber, click = true)
    private LinearLayout ll_versionNumber;

    @BindView(id = R.id.tv_versionname)
    private TextView tv_versionname;

    @BindView(id = R.id.ll_clearCache, click = true)
    private LinearLayout ll_clearCache;

    @BindView(id = R.id.tv_cache)
    private TextView tv_cache;

    @BindView(id = R.id.ll_feedback, click = true)
    private LinearLayout ll_feedback;

    private ClearCacheDialog clearCacheDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setup);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SetUpPresenter(this);
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
        if (upgradeInfo != null && upgradeInfo.versionCode > SystemTool.getAppVersionCode(this)) {
            tv_versionname.setText(getString(R.string.newVersion));
        } else {
            tv_versionname.setText("V" + SystemTool.getAppVersionName(this));
        }
        ((SetUpContract.Presenter) mPresenter).getIsLogin(aty, 2);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        queryCache();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.setUp), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_versionNumber:
                readAndWriteTask();
                break;
            case R.id.ll_clearCache:
                showClearCacheDialog();
                break;
            case R.id.ll_help:
                Intent intent = new Intent(aty, HelpCenterActivity.class);
                intent.putExtra("title", getString(R.string.helpCenter));
                intent.putExtra("url", HELP);
                showActivity(aty, intent);
                break;
            case R.id.ll_aboutus:
                Intent aboutUsIntent = new Intent(aty, HelpCenterActivity.class);
                aboutUsIntent.putExtra("title", getString(R.string.aboutUs));
                aboutUsIntent.putExtra("url", ABOUTUS);
                showActivity(aty, aboutUsIntent);
                break;
            case R.id.tv_logOut:
                ((SetUpContract.Presenter) mPresenter).logOutAPP(aty);
                break;
            case R.id.ll_feedback:
                ((SetUpContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
        }
    }

    /**
     * 查询缓存
     */
    public void queryCache() {
        try {
            tv_cache.setText(DataCleanManager.getTotalCacheSize(aty));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除缓存弹框
     */
    public void showClearCacheDialog() {
        if (clearCacheDialog == null) {
            clearCacheDialog = new ClearCacheDialog(this) {
                @Override
                public void clearCacheDo() {
                    DataCleanManager.clearAllCache(aty);
                    tv_cache.setText("0KB");
                    ViewInject.toast(getString(R.string.clearSuccess));
                }
            };
        }
        if (clearCacheDialog != null && !clearCacheDialog.isShowing()) {
            clearCacheDialog.show();
        }
    }


    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    public void readAndWriteTask() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permission, do the thing!
            Beta.checkUpgrade();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite),
                    NumericConstants.READ_AND_WRITE_CODE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //  Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.sdPermission));
        }
    }

    @Override
    public void setPresenter(SetUpContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(success, BaseResult.class);
            if ((String) baseResult.getData() == null) {
                dismissLoadingDialog();
                return;
            }
            File path = new File((String) baseResult.getData());
            FileNewUtil.installApkFile(this, path.getAbsolutePath());
        } else if (flag == 1) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusLogOutEvent"));
            skipActivity(aty, LoginActivity.class);
        } else if (flag == 2) {
            tv_logOut.setVisibility(View.VISIBLE);
        } else if (flag == 3) {
            showActivity(this, FeedbackActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg) && flag == 2) {
            tv_logOut.setVisibility(View.GONE);
            return;
        } else if (isLogin(msg) && flag != 2) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (clearCacheDialog != null) {
            clearCacheDialog.cancel();
        }
        clearCacheDialog = null;
    }


}
