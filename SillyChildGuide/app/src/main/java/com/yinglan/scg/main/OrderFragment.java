package com.yinglan.scg.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.yinglan.scg.R;

/**
 * 订单
 */
public class OrderFragment extends BaseFragment {

    private MainActivity aty;

//    @BindView(id = R.id.ll_charterOrder, click = true)
//    private LinearLayout ll_charterOrder;
//    @BindView(id = R.id.tv_charterOrder)
//    private TextView tv_charterOrder;
//    @BindView(id = R.id.tv_charterOrder1)
//    private TextView tv_charterOrder1;
//
//
//    @BindView(id = R.id.ll_goodOrder, click = true)
//    private LinearLayout ll_goodOrder;
//    @BindView(id = R.id.tv_goodOrder)
//    private TextView tv_goodOrder;
//    @BindView(id = R.id.tv_goodOrder1)
//    private TextView tv_goodOrder1;
//
//
//    private BaseFragment baseFragment;
//    private BaseFragment baseFragment1;
//    private int chageIcon = 0;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_order, null);
    }

//    @Override
//    protected void initData() {
//        super.initData();
//        baseFragment = new CharterOrderFragment();
//        baseFragment1 = new GoodOrderFragment();
//        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
//    }
//
//    @Override
//    protected void initWidget(View parentView) {
//        super.initWidget(parentView);
//        cleanColors(chageIcon);
//    }
//
//    public void changeFragment(BaseFragment targetFragment) {
//        super.changeFragment(R.id.order_content, targetFragment);
//    }
//
//    @Override
//    public void widgetClick(View v) {
//        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.ll_charterOrder:
//                chageIcon = 0;
//                cleanColors(chageIcon);
//                break;
//            case R.id.ll_goodOrder:
//                chageIcon = 1;
//                cleanColors(chageIcon);
//                break;
//        }
//
//    }
//
//
//    /**
//     * 清除颜色，并添加颜色
//     */
//    @SuppressWarnings("deprecation")
//    public void cleanColors(int chageIcon) {
//        tv_charterOrder.setTextColor(getResources().getColor(R.color.tabColors));
//        tv_charterOrder1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
//        tv_goodOrder.setTextColor(getResources().getColor(R.color.tabColors));
//        tv_goodOrder1.setBackgroundColor(getResources().getColor(R.color.whiteColors));
//        if (chageIcon == 0) {
//            tv_charterOrder.setTextColor(getResources().getColor(R.color.greenColors));
//            tv_charterOrder1.setBackgroundColor(getResources().getColor(R.color.greenColors));
//            changeFragment(baseFragment);
//        } else if (chageIcon == 1) {
//            tv_goodOrder.setTextColor(getResources().getColor(R.color.greenColors));
//            tv_goodOrder1.setBackgroundColor(getResources().getColor(R.color.greenColors));
//            changeFragment(baseFragment1);
//        } else {
//            tv_charterOrder.setTextColor(getResources().getColor(R.color.greenColors));
//            tv_charterOrder1.setBackgroundColor(getResources().getColor(R.color.greenColors));
//            changeFragment(baseFragment);
//        }
//    }

}
