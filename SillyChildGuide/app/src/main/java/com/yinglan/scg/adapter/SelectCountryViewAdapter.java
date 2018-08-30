package com.yinglan.scg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.SelectCountryBean.ResultBean;

import java.util.List;


/**
 * 国家地区码 适配器
 * Created by Admin on 2017/8/15.
 */

public class SelectCountryViewAdapter extends RecyclerView.Adapter<SelectCountryViewAdapter.ViewHolder> {
    protected Context mContext;
    protected List<ResultBean> mDatas;
    protected LayoutInflater mInflater;

    private ViewCallBack callBack;//回调

    public SelectCountryViewAdapter(Context mContext, List<ResultBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<ResultBean> getDatas() {
        return mDatas;
    }

    public SelectCountryViewAdapter setDatas(List<ResultBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_selectcountry, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ResultBean resultBean = mDatas.get(position);
        holder.tv_country.setText(resultBean.getCountry());
        holder.tv_areaCode.setText(resultBean.getMobile_prefix());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClickListener(resultBean.getMobile_prefix());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_country;
        TextView tv_areaCode;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_country = (TextView) itemView.findViewById(R.id.tv_country);
            tv_areaCode = (TextView) itemView.findViewById(R.id.tv_areaCode);
            content = itemView.findViewById(R.id.content);
        }
    }

    public void setViewCallBack(ViewCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ViewCallBack {
        void onClickListener(String code);
    }

}
