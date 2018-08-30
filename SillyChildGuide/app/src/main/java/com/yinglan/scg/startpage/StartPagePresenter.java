package com.yinglan.scg.startpage;


import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

/**
 * Created by Administrator on 2016/11/29.
 */

public class StartPagePresenter implements StartPageContract.Presenter {
    private StartPageContract.View mView;

    public StartPagePresenter(StartPageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAppConfig() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getAppConfig(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

//    @Override
//    public void initLocation(Activity activity, LocationClient mLocationClient) {
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");
//        //可选，默认gcj02，设置返回的定位结果坐标系
//        int span = 30000;
//        option.setScanSpan(span);
//        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//
//        option.setIsNeedAddress(true);
//        //可选，设置是否需要地址信息，默认不需要
//
//        option.setOpenGps(true);
//        //可选，默认false,设置是否使用gps
//
//        option.setLocationNotify(true);
//        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//
//        option.setIsNeedLocationDescribe(true);
//        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//
//        option.setIsNeedLocationPoiList(true);
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//
//        option.setIgnoreKillProcess(false);
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//
////        option.setIgnoreCacheException(false);
////        //可选，默认false，设置是否收集CRASH信息，默认收集
//
//        option.setEnableSimulateGps(false);
//        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//
//        // option.setWifiValidTime(5*60*1000);
//        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位
//        mLocationClient.setLocOption(option);
//        choiceLocationWrapper(activity, mLocationClient);
//    }


//    @AfterPermissionGranted(NumericConstants.LOCATION_CODE)
//    private void choiceLocationWrapper(Activity activity, LocationClient mLocationClient) {
//        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
//        if (EasyPermissions.hasPermissions(activity, perms)) {
//
//            mLocationClient.start();
//        } else {
//            EasyPermissions.requestPermissions(activity, "定位选择需要以下权限:\n\n1.访问设备上的gps\n\n2.读写权限", NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
//        }
//    }

    @Override
    public void getSystemMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getSystemMessage(httpParams, 1, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(response, SystemMessageBean.class);
//                if (systemMessageBean.getResult().getList() == null || systemMessageBean.getResult().getList().size() == 0) {
//                    mView.errorMsg(response, 0);
//                    return;
//                }
//                if (systemMessageBean.getResult().getUnread() > 0) {
//                    mView.getSuccess("", 0);
//                } else {
//                    getGuideMessage();
//                }
    }

//            @Override
//            public void onFailure(String msg) {
//                getGuideMessage();
//            }
//        });
    //   }

    @Override
    public void getGuideMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", "driver");
        httpParams.put("page", 1);
        httpParams.put("pageSize", 10000);
//        RequestClient.getHxUserList(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
////                if (loadConversationList(response)) {
////                    mView.getSuccess(response, 0);
////                } else {
////                    mView.errorMsg(response, 0);
////                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void getChatManagerListener() {
        // EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    public void getQiNiuKey() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getQiNiuKey(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response,0);

            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }




}