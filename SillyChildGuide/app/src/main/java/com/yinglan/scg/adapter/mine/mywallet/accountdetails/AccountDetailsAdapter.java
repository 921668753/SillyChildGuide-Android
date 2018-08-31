package com.yinglan.scg.adapter.mine.mywallet.accountdetails;

import android.content.Context;

import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.mywallet.accountdetails.AccountDetailsBean.DataBean.ResultBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 我的  我的钱包 账户明细  适配器
 * Created by Admin on 2017/9/8.
 */

public class AccountDetailsAdapter extends BGAAdapterViewAdapter<ResultBean> {


    public AccountDetailsAdapter(Context context) {
        super(context, R.layout.item_accountdetails);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ResultBean model) {

        /**
         * 明细类型
         */

        if (model.getType() == 1 && model.getStatus() == 0) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.recharge) + " (" + mContext.getString(R.string.ongoing) + ")");
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "+" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 1 && model.getStatus() == 1) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.recharge));
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "+" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 2 && model.getStatus() == 0) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.withdrawal) + " (" + mContext.getString(R.string.ongoing) + ")");
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "-" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 2 && model.getStatus() == 1) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.withdrawal));
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "-" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 3 && model.getStatus() == 0) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.consumption));
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "-" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 3 && model.getStatus() == 1) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.consumption));
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "-" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 4 && model.getStatus() == 0) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.refund) + " (" + mContext.getString(R.string.ongoing) + ")");
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "+" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        } else if (model.getType() == 4 && model.getStatus() == 1) {
            helper.setText(R.id.tv_detailsType, mContext.getString(R.string.refund));
            /**
             * 交易金额
             */
            helper.setText(R.id.tv_balance, "+" + MathUtil.keepTwo(StringUtils.toDouble(model.getAmount())));
        }

        if (model.getStatus() == 0) {
            helper.setTextColorRes(R.id.tv_balance, R.color.dividercolors1);
        } else {
            helper.setTextColorRes(R.id.tv_balance, R.color.greenColors);
        }


        /**
         * 时间
         */
        helper.setText(R.id.tv_time, DataUtil.formatData(StringUtils.toLong(model.getCreate_time()) / 1000, "yyyy-MM-dd HH:mm:ss"));
    }
}
