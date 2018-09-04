package com.yinglan.scg.adapter.mine.myvehicle.model.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.personaldata.search.TagNewAdapter;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.RecentSearchBean.DataBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * 最近搜索
 */
public class RecentSearchTagAdapter extends TagNewAdapter<DataBean> {

    private final LayoutInflater mInflater;
    private final Context context;

    public RecentSearchTagAdapter(Context context, List<DataBean> datas) {
        super(datas);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(FlowLayout parent, int position, DataBean s) {
        TextView tv = (TextView) mInflater.inflate(R.layout.item_arearecentsearch, parent, false);
        tv.setText(s.getName());
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(TagFlowLayout.dip2px(context, 7), TagFlowLayout.dip2px(context, 10), TagFlowLayout.dip2px(context, 7), TagFlowLayout.dip2px(context, 0));
        tv.setLayoutParams(lp);
        return tv;
    }
}
