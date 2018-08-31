package com.yinglan.scg.mine.setup.feedback;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.DataCleanManager;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/11.
 */

public class FeedbackPresenter implements FeedbackContract.Presenter {

    private FeedbackContract.View mView;

    public FeedbackPresenter(FeedbackContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postAdvice(String feedType, String content, List<LocalMedia> selectList) {
        if (TextUtils.isEmpty(content)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.textDescribe), 0);
            return;
        }
        if (selectList.size() <= 0) {
            List<String> listStr = new ArrayList<String>();
            postAdvice1(feedType, content, listStr);
            return;
        }
        PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "selectListSize", 0);
        List<String> listStr = new ArrayList<String>();
        for (int i = 0; i < selectList.size(); i++) {
            if (StringUtils.isEmpty(selectList.get(i).getPath())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 1);
                return;
            }
            listStr.add("");
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
                        listStr.set(finalI, headpicPath);
                        if (selectListSize == selectList.size()) {
                            postAdvice1(feedType, content, listStr);
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


    private void postAdvice1(String feedType, String content, List<String> selectList) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", feedType);
        if (selectList.size() > 0) {
            String imgsStr = "";
            for (int i = 0; i < selectList.size(); i++) {
                imgsStr = imgsStr + "," + selectList.get(i);
            }
            httpParams.put("imgUrls", imgsStr.substring(1));
        }
        httpParams.put("text", content);
        RequestClient.postAdvice(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
