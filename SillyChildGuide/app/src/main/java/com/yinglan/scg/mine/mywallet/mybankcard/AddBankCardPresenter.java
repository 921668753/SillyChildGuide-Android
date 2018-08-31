package com.yinglan.scg.mine.mywallet.mybankcard;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.R;
import com.yinglan.scg.retrofit.RequestClient;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/17.
 */

public class AddBankCardPresenter implements AddBankCardContract.Presenter {

    private AddBankCardContract.View mView;

    public AddBankCardPresenter(AddBankCardContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String type) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputPhone), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("phone", phone);
        RequestClient.postCaptcha(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getBank() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getBank(KJActivityStack.create().topActivity(),httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


    @Override
    public void postAddBankCard(String account_name, String id_number, String open_bank, String account_no, String phone, String verificationCode) {
        if (StringUtils.isEmpty(account_name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.accountHolderName1), 0);
            return;
        }
        String all = "^[A-Za-z\\u4e00-\\u9fa5]{2,10}";//{2,10}表示字符的长度是2-10
        Pattern pattern = Pattern.compile(all);
        boolean tf = Pattern.matches(all, account_name);
        if (!tf) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintName1), 0);
            return;
        }
        if (!(id_number.length() == 15 || id_number.length() == 18)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseidNumber), 0);
            return;
        }

        if (StringUtils.isEmpty(open_bank)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.openingBank1), 0);
            return;
        }
        if (StringUtils.isEmpty(account_no)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.bankCardNumber1), 0);
            return;
        }

//        if (StringUtils.isEmpty(phone)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.accountText), 0);
//            return;
//        }
//        if (phone.length() != 11 || !AccountValidatorUtil.isMobile(phone)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
//            return;
//        }
//        if (StringUtils.isEmpty(verificationCode)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
//            return;
//        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("account_name", account_name);
        httpParams.put("id_number", id_number);
        httpParams.put("open_bank", open_bank);
        httpParams.put("bank", open_bank);
        httpParams.put("account_no", account_no);
        httpParams.put("phone", phone);
        httpParams.put("captcha", verificationCode);
        RequestClient.postAddBankCard(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postPurseDefault(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("id", id);
        RequestClient.postPurseDefault(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }
}
