package com.yinglan.scg.mine.personaldata.setsex;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.retrofit.RequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SetSexPresenter implements SetSexContract.Presenter {
    private SetSexContract.View mView;

    public SetSexPresenter(SetSexContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void setSex(int sex) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sex", sex);
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        RequestClient.postMemberEdit(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
