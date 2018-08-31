package com.yinglan.scg.mine.mywallet.withdrawal;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scg.R;
import com.yinglan.scg.mine.mywallet.withdrawal.dialog.SubmitBouncedDialog;
import com.yinglan.scg.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/17.
 */

public class WithdrawalPresenter implements WithdrawalContract.Presenter {


    private WithdrawalContract.View mView;

    public WithdrawalPresenter(WithdrawalContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getMyWallet() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getMyWallet(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postWithdrawal(String withdrawalAmount, int bankId) {
        if (StringUtils.isEmpty(withdrawalAmount)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.notHigherWithdrawalLimit1), 0);
            return;
        }
        if (!(MathUtil.judgeTwoDecimal(withdrawalAmount))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.notHigherWithdrawalLimit1), 0);
            return;
        }
        if (StringUtils.toDouble(withdrawalAmount) <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.notHigherWithdrawalLimit1), 0);
            return;
        }
        if (bankId < 1) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.withdrawalsBank1), 0);
            return;
        }
        SubmitBouncedDialog submitBouncedDialog = new SubmitBouncedDialog(KJActivityStack.create().topActivity()) {
            @Override
            public void confirm() {
                this.cancel();
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.submissionLoad));
                HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                httpParams.put("money", withdrawalAmount);
                httpParams.put("bankId", bankId);
                RequestClient.postWithdrawal(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
        };
        submitBouncedDialog.show();
    }


    @Override
    public void getIsLogin(int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getIsLogin(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });
    }
}
