package com.yinglan.scg.loginregister;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.IndexNewBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.loginregister.SelectCountryCodeViewAdapter;
import com.yinglan.scg.entity.loginregister.SelectCountryCodeBean;
import com.yinglan.scg.entity.loginregister.SelectCountryCodeBean.DataBean;
import com.yinglan.scg.utils.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择国家区号
 * Created by Admin on 2017/9/6.
 */

public class SelectCountryCodeActivity extends BaseActivity implements SelectCountryCodeContract.View {

    @BindView(id = R.id.rv)
    private RecyclerView mRv;

    @BindView(id = R.id.indexBar)
    private IndexNewBar mIndexBar;
    private SelectCountryCodeViewAdapter mAdapter;

    private LinearLayoutManager mManager;

    private SuspensionDecoration mDecoration;

    private List<DataBean> mDatas = new ArrayList<DataBean>();

    private DividerItemDecoration dividerItemDecoration;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_selectcountry);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new SelectCountryCodePresenter(this);
        mAdapter = new SelectCountryCodeViewAdapter(this, mDatas);
        mDecoration = new SuspensionDecoration(this, mDatas);
        mManager = new LinearLayoutManager(this);
        dividerItemDecoration = new DividerItemDecoration(SelectCountryCodeActivity.this, DividerItemDecoration.VERTICAL_LIST);
    }


    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        mRv.setLayoutManager(mManager);
        mRv.setAdapter(mAdapter);
        mAdapter.setViewCallBack(new SelectCountryCodeViewAdapter.ViewCallBack() {
            @Override
            public void onClickListener(String code) {
                Intent intent = new Intent();
                // 获取内容
                intent.putExtra("areaCode", code);
                // 设置结果 结果码，一个数据
                setResult(RESULT_OK, intent);
                // 结束该activity 结束之后，前面的activity才可以处理结果
               finish();
            }
        });
        mRv.addItemDecoration(mDecoration);
        //如果add两个，那么按照先后顺序，依次渲染。
        dividerItemDecoration.setOrientation(0);
        mRv.addItemDecoration(dividerItemDecoration);
        mIndexBar.setmPressedShowTextView(null)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        showLoadingDialog(getString(R.string.dataLoad));
        ((SelectCountryCodeContract.Presenter) mPresenter).getCountryNumber(this);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.selectCountryCode), true, R.id.titlebar);
    }





    @Override
    public void setPresenter(SelectCountryCodeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        SelectCountryCodeBean selectCountryBean = (SelectCountryCodeBean) JsonUtil.getInstance().json2Obj(success, SelectCountryCodeBean.class);
        if (selectCountryBean != null && selectCountryBean.getData() != null && selectCountryBean.getData().size() > 0) {
            //模拟线上加载数据
            mDatas.clear();
            mDatas.addAll(selectCountryBean.getData());
            initDatas(mDatas);
        }
        dismissLoadingDialog();
    }

    /**
     * 组织数据源
     *
     * @return
     */
    private void initDatas(List<DataBean> list) {
        mAdapter.setDatas(list);
        mAdapter.notifyDataSetChanged();
        mIndexBar.setmSourceDatas(list)//设置数据
                .invalidate();
        mDecoration.setmDatas(list);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter = null;
        mDatas.clear();
        mDatas = null;
    }
}
