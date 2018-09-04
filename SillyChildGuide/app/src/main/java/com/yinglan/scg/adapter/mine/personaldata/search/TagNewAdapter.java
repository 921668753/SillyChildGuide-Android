package com.yinglan.scg.adapter.mine.personaldata.search;

import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import cn.bingoogolapple.baseadapter.BGABaseAdapterUtil;


public abstract class TagNewAdapter<T> extends TagAdapter<T> {

    public List<T> mTagDatas;

    public TagNewAdapter(List<T> datas) {
        super(datas);
        mTagDatas = datas;
    }


    /**
     * 设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据表）
     *
     * @param data
     */
    public void setData(List<T> data) {
        if (BGABaseAdapterUtil.isListNotEmpty(data)) {
            mTagDatas.addAll(data);
        } else {
            mTagDatas.clear();
        }
        notifyDataChanged();
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    public List<T> getData() {
        return mTagDatas;
    }

    /**
     * 清空数据列表
     */
    public void clear() {
        mTagDatas.clear();
        notifyDataChanged();
    }
}
