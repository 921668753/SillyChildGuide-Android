package com.yinglan.scg.mine.personaldata.authenticationinformation;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.Log;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.yinglan.scg.R;
import com.yinglan.scg.retrofit.RequestClient;
import com.yinglan.scg.retrofit.uploadimg.UploadManagerUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public class AuthenticationInformationPresenter implements AuthenticationInformationContract.Presenter {

    private AuthenticationInformationContract.View mView;

    public AuthenticationInformationPresenter(AuthenticationInformationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void postEidtCertification(int city_id, List<LocalMedia> selectCarCompanyList, List<LocalMedia> selectDrivingLicenceList, List<LocalMedia> selectHoldCardFaceList,
                                      List<LocalMedia> selectLegallyList, List<LocalMedia> selectYachtDrivingLicenseList) {
        if (city_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectServiceArea), 0);
            return;
        }
        if (selectCarCompanyList == null || selectCarCompanyList.size() <= 0 || StringUtils.isEmpty(selectCarCompanyList.get(0).getPath())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.uploadVehicleInspection), 0);
            return;
        }
        if (selectDrivingLicenceList == null || selectDrivingLicenceList.size() <= 0 || StringUtils.isEmpty(selectDrivingLicenceList.get(0).getPath())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.uploadDriverLicense), 0);
            return;
        }
        if (selectHoldCardFaceList == null || selectHoldCardFaceList.size() <= 0 || StringUtils.isEmpty(selectHoldCardFaceList.get(0).getPath())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.uploadHoldingIdCard), 0);
            return;
        }
        mView.getSuccess("", 0);
    }

    @Override
    public void postEidtCertification1(int city_id, List<LocalMedia> selectCarCompanyList, List<LocalMedia> selectDrivingLicenceList, List<LocalMedia> selectHoldCardFaceList,
                                       List<LocalMedia> selectLegallyList, List<LocalMedia> selectYachtDrivingLicenseList) {
        if (city_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectServiceArea), 0);
            return;
        }
        if (selectCarCompanyList == null || selectCarCompanyList.size() <= 0 || StringUtils.isEmpty(selectCarCompanyList.get(0).getPath())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.uploadVehicleInspection), 0);
            return;
        }
        if (selectDrivingLicenceList == null || selectDrivingLicenceList.size() <= 0 || StringUtils.isEmpty(selectDrivingLicenceList.get(0).getPath())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.uploadDriverLicense), 0);
            return;
        }
        if (selectHoldCardFaceList == null || selectHoldCardFaceList.size() <= 0 || StringUtils.isEmpty(selectHoldCardFaceList.get(0).getPath())) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.uploadHoldingIdCard), 0);
            return;
        }

        List<LocalMedia> selectList = new ArrayList<LocalMedia>();
        selectList.clear();
        selectList.addAll(selectCarCompanyList);
        selectList.addAll(selectDrivingLicenceList);
        selectList.addAll(selectHoldCardFaceList);
        if (selectLegallyList == null || selectLegallyList.size() <= 0 || StringUtils.isEmpty(selectLegallyList.get(0).getPath())) {
            selectLegallyList.clear();
            LocalMedia localMedia = new LocalMedia();
            localMedia.setHttpPath("");
            selectLegallyList.add(localMedia);
        }
        selectList.addAll(selectLegallyList);
        if (selectYachtDrivingLicenseList == null || selectYachtDrivingLicenseList.size() <= 0 || StringUtils.isEmpty(selectYachtDrivingLicenseList.get(0).getPath())) {
            selectYachtDrivingLicenseList.clear();
            LocalMedia localMedia = new LocalMedia();
            localMedia.setHttpPath("");
            selectYachtDrivingLicenseList.add(localMedia);
        }
        selectList.addAll(selectYachtDrivingLicenseList);
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath()) || selectList.get(i).getPath().startsWith("http")) {
                int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                selectListSize = selectListSize + 1;
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                continue;
            }
            String token = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuToken");
            //参数 图片路径,图片名,token,成功的回调
            int finalI = i;
            UploadManagerUtil.getInstance().getUploadManager().put(selectList.get(i).getPath(), null, token, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo responseInfo, JSONObject jsonObject) {
                    Log.d("ReadFragment", "key" + key + "responseInfo" + JsonUtil.obj2JsonString(responseInfo) + "jsObj:" + String.valueOf(jsonObject));
                    if (responseInfo.isOK()) {
                        String host = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "qiNiuImgHost");
                        String headpicPath = null;
                        try {
                            headpicPath = host + jsonObject.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 1);
                                    return;
                                }
                            });
                            return;
                        }
                        int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                        selectListSize = selectListSize + 1;
                        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                        Log.i("ReadFragment", "complete: " + headpicPath);
                        selectList.get(finalI).setHttpPath(headpicPath);
                        if (selectListSize == selectList.size()) {
                            postEidtCertification2(city_id, selectList);
                            selectList.clear();
                        }
                        return;
                    }
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedUploadPicture), 1);
                            return;
                        }
                    });
                    return;
                }
            }, null);
        }
    }


    private void postEidtCertification2(int city_id, List<LocalMedia> selectList) {
        if (city_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectServiceArea), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("city_id", city_id);
        httpParams.put("car_inspection_certificate", selectList.get(0).getHttpPath());
        httpParams.put("driver_license", selectList.get(1).getHttpPath());
        httpParams.put("id_card", selectList.get(2).getHttpPath());
        if (selectList != null && selectList.size() > 0 && !StringUtils.isEmpty(selectList.get(3).getHttpPath())) {
            httpParams.put("guide_card", selectList.get(3).getHttpPath());
        }
        if (selectList != null && selectList.size() > 0 && !StringUtils.isEmpty(selectList.get(4).getHttpPath())) {
            httpParams.put("yacht_driving_license", selectList.get(4).getHttpPath());
        }
        RequestClient.postAddCertification(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }

    @Override
    public void getCertificationDetail() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getCertificationDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }

}
