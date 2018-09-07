package com.yinglan.scg.adapter.loginregister;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinglan.scg.R;
import com.yinglan.scg.entity.loginregister.SelectCountryCodeBean.DataBean;

import java.util.List;


/**
 * 国家地区码 适配器
 * Created by Admin on 2017/8/15.
 */

public class SelectCountryCodeViewAdapter extends RecyclerView.Adapter<SelectCountryCodeViewAdapter.ViewHolder> {
    protected Context mContext;
    protected List<DataBean> mDatas;
    protected LayoutInflater mInflater;

    private ViewCallBack callBack;//回调

    public SelectCountryCodeViewAdapter(Context mContext, List<DataBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<DataBean> getDatas() {
        return mDatas;
    }

    public SelectCountryCodeViewAdapter setDatas(List<DataBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_selectcountry, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DataBean resultBean = mDatas.get(position);
        holder.tv_country.setText(resultBean.getChina_name());
        holder.tv_areaCode.setText("+" + resultBean.getCountry_code());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClickListener(resultBean.getCountry_code());
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
