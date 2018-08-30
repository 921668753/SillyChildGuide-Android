package com.yinglan.scg.adapter.mine.mywallet.mybankcard;

import android.content.Context;
import android.view.View;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.mine.mywallet.mybankcard.MyBankCardBean.DataBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 我的银行卡列表适配器
 * Created by Administrator on 2017/12/22.
 */

public class MyBankCardViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MyBankCardViewAdapter(Context context) {
        super(context, R.layout.item_bankcard);
    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, DataBean resultBean) {
        helper.setText(R.id.tv_cardName, resultBean.getAccount_name());
        if (resultBean.getBank() != null && resultBean.getBank().contains(mContext.getString(R.string.chinaBank))) {
            helper.setImageResource(R.id.img_back, R.mipmap.bank_card_bank_china);
        } else if (resultBean.getBank() != null && resultBean.getBank().contains(mContext.getString(R.string.ccdBank))) {
            helper.setImageResource(R.id.img_back, R.mipmap.bank_card_ccd);
        } else if (resultBean.getBank() != null && resultBean.getBank().contains(mContext.getString(R.string.cmbBank))) {
            helper.setImageResource(R.id.img_back, R.mipmap.bank_card_china_merchants_bank);
        } else {
            helper.setImageResource(R.id.img_back, R.mipmap.bank_card_default);
        }
//        if (resultBean.getIs_default() == 1) {
//            helper.setVisibility(R.id.img_check, View.VISIBLE);
//        } else {
        helper.setVisibility(R.id.img_check, View.GONE);
//        }
        helper.setText(R.id.tv_bankCardName, resultBean.getOpen_bank());
        helper.setText(R.id.tv_tail, resultBean.getAccount_no());
    }
}
