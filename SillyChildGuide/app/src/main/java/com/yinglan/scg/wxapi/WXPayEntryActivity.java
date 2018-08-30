/*
 * Copyright (C) 2015 tony<wuhaiyang1213@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yinglan.scg.wxapi;

import android.content.Intent;
import android.util.Log;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.PreferenceHelper;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yinglan.scg.BuildConfig;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void setRootView() {
        api = WXAPIFactory.createWXAPI(this, BuildConfig.WEIXIN_APPKEY);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("pay", "errStr=" + resp.errStr);
        Log.d("pay", "errCode=" + resp.errCode);
        Log.d("pay", "getType=" + resp.getType());
        Log.d("pay", "toString=" + resp.toString());
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                PreferenceHelper.write(this, StringConstants.FILENAME, "isRefreshMineFragment", true);
//                if (KJActivityStack.create().findActivity(RechargeActivity.class)!=null){
//                    ViewInject.toast(getString(R.string.alipay_succeed));
//                }else if (KJActivityStack.create().findActivity(CheckstandActivity.class)!=null){
//                    Intent jumpintent=new Intent(this, PaySuccessActivity.class);
//                    Activity context= KJActivityStack.create().findActivity(CheckstandActivity.class);
//                    jumpintent.putExtra("orderid",((CheckstandActivity)context).getOrderid());
//                    jumpintent.putExtra("paytype",((CheckstandActivity)context).getPaytype());
//                    jumpintent.putExtra("paymoney",((CheckstandActivity)context).getPaymoney_fmt());
//                    showActivity(this,jumpintent);
//                    KJActivityStack.create().finishActivity(CheckstandActivity.class);
//                }
                finish();
                //   skipActivity(aty, PaySuccessActivity.class);
            } else if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
//                PreferenceHelper.write(this, StringConstants.FILENAME, "payClass", "");
//                ViewInject.toast(getString(R.string.alipay_order_cancel));
            } else {
//                PreferenceHelper.write(this, StringConstants.FILENAME, "payClass", "");
//                ViewInject.toast(getString(R.string.alipay_order_error));
            }
            finish();
        }
    }


}