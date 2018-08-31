package com.yinglan.scg.startpage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.FileUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.okhttp3.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.yinglan.scg.R;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.startpage.dialog.PermissionsDialog;
import com.yinglan.scg.utils.activity.BaseInstrumentedActivity;
import com.yinglan.scg.entity.startpage.QiNiuKeyBean;
import com.yinglan.scg.main.MainActivity;

import java.util.List;

import okhttp3.OkHttpClient;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 用户端
 * 启动页暂定为集成beasactivity
 * 若添加极光推送等需更换极光推送activity   InstrumentedActivity
 * Created by ruitu ck on 2016/9/14.
 */

public class StartPageActivity extends BaseInstrumentedActivity implements StartPageContract.View, EasyPermissions.PermissionCallbacks {
    // private LocationClient mLocationClient;
    //  private MyLocationListener myListener;

    /**
     * 高德定位
     */
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_startpage);
    }

    /**
     * 设置定位
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new StartPagePresenter(this);
//        mLocationClient = new LocationClient(aty.getApplicationContext());
//        myListener = new MyLocationListener();
//        //声明LocationClient类
//        mLocationClient.registerLocationListener(myListener);
//        //注册监听函数
//        ((StartPageContract.Presenter) mPresenter).initLocation(aty, mLocationClient);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }

    public void initView() {
        ImageView image = new ImageView(aty);
        image.setImageResource(R.mipmap.startpage);
        Animation anim = AnimationUtils.loadAnimation(aty, R.anim.splash_start);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                readAndWriteTask();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        image.setAnimation(anim);
        setContentView(image);
    }

    private void jumpTo(boolean isShow) {
//        startService(new Intent(aty, CommonService.class));
        boolean isFirst = PreferenceHelper.readBoolean(this, StringConstants.FILENAME, "firstOpen", false);
        Intent jumpIntent = new Intent();
        if (isFirst) {
            PreferenceHelper.write(this, StringConstants.FILENAME, "firstOpen", false);
            jumpIntent.setClass(this, GuideViewActivity.class);
        } else {
            jumpIntent.setClass(this, MainActivity.class);
            jumpIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            jumpIntent.setAction("android.intent.action.MAIN");
            jumpIntent.addCategory("android.intent.category.LAUNCHER");
            jumpIntent.putExtra("isShow", isShow);
        }
        skipActivity(aty, jumpIntent);
        overridePendingTransition(0, 0);
    }


    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    public void readAndWriteTask() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permissions, do the thing!
            RxVolley.setRequestQueue(RequestQueue.newRequestQueue(FileUtils.getSaveFolder(StringConstants.CACHEPATH), new OkHttpStack(new OkHttpClient())));
            LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            try {
                if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
                    doFinishDialog(getString(R.string.notOpenPositionSwitchClose));
                    return;
                }
            } catch (Exception e) {
                doFinishDialog(getString(R.string.notOpenPositionSwitchClose));
                return;
            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", "");
//            jumpTo(true);
//            ((StartPageContract.Presenter) mPresenter).getSystemMessage();
            ((StartPageContract.Presenter) mPresenter).getQiNiuKey();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite), NumericConstants.READ_AND_WRITE_CODE, perms);
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
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("tag", "onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            doFinishDialog(getString(R.string.sdPermission));
        } else if (requestCode == NumericConstants.LOCATION_CODE) {
            doFinishDialog(getString(R.string.locationRelatedPermission));
        }
    }


    @Override
    public void setPresenter(StartPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        QiNiuKeyBean qiNiuKeyBean = (QiNiuKeyBean) JsonUtil.getInstance().json2Obj(success, QiNiuKeyBean.class);
        if (qiNiuKeyBean != null && !StringUtils.isEmpty(qiNiuKeyBean.getData().getAuthToken())) {
            PreferenceHelper.write(this, StringConstants.FILENAME, "qiNiuToken", qiNiuKeyBean.getData().getAuthToken());
            PreferenceHelper.write(this, StringConstants.FILENAME, "qiNiuImgHost", qiNiuKeyBean.getData().getHost());
            PreferenceHelper.write(this, StringConstants.FILENAME, "qiNiuImgTime", String.valueOf(System.currentTimeMillis()));
        }
        jumpTo(true);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        jumpTo(false);
        //  ((StartPageContract.Presenter) mPresenter).getAppConfig();
        //  ViewInject.toast(msg);
    }


//    public class MyLocationListener extends BDAbstractLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // TODO Auto-generated method stub
//            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                //获取定位结果
//                location.getTime();    //获取定位时间
//                location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
//                location.getLocType();    //获取定位类型
//                location.getLatitude();    //获取纬度信息
//                location.getLongitude();    //获取经度信息
//                location.getRadius();    //获取定位精准度
//                location.getAddrStr();    //获取地址信息
//                location.getCountry();    //获取国家信息
//                location.getCountryCode();    //获取国家码
//                location.getCity();    //获取城市信息
//                location.getCityCode();    //获取城市码
//                location.getDistrict();    //获取区县信息
//                location.getStreet();    //获取街道信息
//                location.getStreetNumber();    //获取街道码
//                location.getLocationDescribe();    //获取当前位置描述信息
//                location.getPoiList();    //获取当前位置周边POI信息
//                location.getBuildingID();    //室内精准定位下，获取楼宇ID
//                location.getBuildingName();    //室内精准定位下，获取楼宇名称
//                location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCountry", location.getCountry());
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "locationCity", location.getCity());
//                PreferenceHelper.write(aty, StringConstants.FILENAME, "location", location.getLongitude() + "," + location.getLatitude());
//                Log.d("tag111", location.getCity());
//                //  jumpTo();
//            }
//        }
//
//        /**
//         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
//         * 自动回调，相同的diagnosticType只会回调一次
//         *
//         * @param locType           当前定位类型
//         * @param diagnosticType    诊断类型（1~9）
//         * @param diagnosticMessage 具体的诊断信息释义
//         */
//        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "location", "");
//            // jumpTo();
//            Log.d("tag111", "000000");
//            if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {
//                //建议打开GPS
//                Log.d("tag111", "1111");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {
//
//                //建议打开wifi，不必连接，这样有助于提高网络定位精度！
//                Log.d("tag111", "2222");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {
//
//                //定位权限受限，建议提示用户授予APP定位权限！
//                Log.d("tag111", "33333");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {
//
//                //网络异常造成定位失败，建议用户确认网络状态是否异常！
//                Log.d("tag111", "444444");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {
//
//                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
//                Log.d("tag111", "455555");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {
//
//                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
//                Log.d("tag111", "666666");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {
//
//                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
//                Log.d("tag111", "77777");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {
//
//                //百度定位服务端定位失败
//                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
//                Log.d("tag111", "8888888");
//            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {
//                //无法获取有效定位依据，但无法确定具体原因
//                //建议检查是否有安全软件屏蔽相关定位权限
//                //或调用LocationClient.restart()重新启动后重试！
//                Log.d("tag111", "9999999");
//            }
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
//        mLocationClient.stop(); //停止定位服务
//        myListener = null;
    }

    private void doFinishDialog(String content) {
        PermissionsDialog dialog = new PermissionsDialog(this) {
            @Override
            public void doAction() {
                finish();
            }
        };
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.setContent(content);
    }
}
