package com.yinglan.scg.mine.myvehicle;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.common.cklibrary.utils.httputil.ResponseProgressbarListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.luck.picture.lib.entity.LocalMedia;
import com.yinglan.scg.R;
import com.yinglan.scg.retrofit.RequestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/11.
 */

public class AddVehiclePresenter implements AddVehicleContract.Presenter {

    private AddVehicleContract.View mView;

    public AddVehiclePresenter(AddVehicleContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getModelDetail(int model_id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("model_id", model_id);
        RequestClient.getModelDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postEidtModel(int model_id, List<LocalMedia> selectList, int model_name_id, int passenger_number, int baggage_number, long model_year, String license_plate, int is_insurance) {
        if (selectList.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.vehicleImages1), 1);
            return;
        }
        if (model_name_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectModel), 1);
            return;
        }
        if (passenger_number <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectCanTakeNumber), 1);
            return;
        }

        if (baggage_number <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectCanPutBaggage), 1);
            return;
        }
        if (model_year <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectVehicleYear), 1);
            return;
        }
        if (StringUtils.isEmpty(license_plate)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnterlicensePlateNumber), 1);
            return;
        }
        mView.getSuccess("", 1);
    }

    @Override
    public void postEidtModel1(int model_id, List<LocalMedia> selectList, int model_name_id, int passenger_number, int baggage_number, long model_year, String license_plate, int is_insurance) {
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        List<String> listStr = new ArrayList<String>();
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 1);
                return;
            }
            listStr.add("");
            if (selectList.get(i).getPath().startsWith("http")) {
                listStr.set(i, selectList.get(i).getPath());
                int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                selectListSize = selectListSize + 1;
                PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                if (selectListSize == selectList.size()) {
                    postEidtModel2(model_id, listStr, model_name_id, passenger_number, baggage_number, model_year, license_plate, is_insurance);
                    selectList.clear();
                }
                continue;
            }
            //参数 图片路径,图片名,token,成功的回调
            int finalI = i;
            RequestClient.upLoadImg(KJActivityStack.create().topActivity(), selectList.get(i).getPath(), 1, new ResponseProgressbarListener<String>() {
                @Override
                public void onProgress(String progress) {
                    //  mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.crossLoad) + progress + "%");
                }

                @Override
                public void onSuccess(String response) {
                    int selectListSize = PreferenceHelper.readInt(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
                    selectListSize = selectListSize + 1;
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", selectListSize);
                    listStr.set(finalI, response);
                    if (selectListSize == selectList.size()) {
                        postEidtModel2(model_id, listStr, model_name_id, passenger_number, baggage_number, model_year, license_plate, is_insurance);
                        selectList.clear();
                    }
                }

                @Override
                public void onFailure(String msg) {
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(msg, 1);
                            return;
                        }
                    });
                }
            });
        }
    }


    private void postEidtModel2(int model_id, List<String> selectList, int model_name_id, int passenger_number, int baggage_number, long model_year, String license_plate, int is_insurance) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        if (model_id > 0) {
            httpParams.put("model_id", model_id);
        } else {
            httpParams.put("model_id", "");
        }
        if (selectList.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.vehicleImages1), 1);
            return;
        }
        String imgsStr = "";
        if (selectList.size() > 0) {
            for (int i = 0; i < selectList.size(); i++) {
                imgsStr = imgsStr + "," + selectList.get(i);
            }
            httpParams.put("model_picture", imgsStr.substring(1));
        }
        httpParams.put("model_name_id", model_name_id);
        httpParams.put("passenger_number", passenger_number);
        httpParams.put("baggage_number", baggage_number);
        httpParams.put("model_year", String.valueOf(model_year));
        httpParams.put("license_plate", license_plate);
        httpParams.put("is_insurance", is_insurance);
        RequestClient.postEidtModel(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
