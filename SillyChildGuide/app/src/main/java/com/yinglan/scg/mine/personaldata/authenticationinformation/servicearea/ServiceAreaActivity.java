package com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.personaldata.ServiceAreaClassificationGridViewAdapter;
import com.yinglan.scg.adapter.mine.personaldata.ServiceAreaClassificationListViewAdapter;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.ServiceAreaByCountryIdBean;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.ServiceAreaCountryListBean;
import com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea.search.AreaSearchActivity;

import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 服务地区
 */
public class ServiceAreaActivity extends BaseActivity implements ServiceAreaContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.lv_countries)
    private ListView lv_countries;

    @BindView(id = R.id.gv_countriesClassification)
    private GridView gv_countriesClassification;

    private ServiceAreaClassificationListViewAdapter mListViewAdapter = null;

    private ServiceAreaClassificationGridViewAdapter mGridViewAdapter = null;

    private List<ServiceAreaCountryListBean.DataBean> serviceAreaCountryList;

    private ServiceAreaCountryListBean.DataBean serviceAreaCountryBean = null;

    private String title = "";
    private int type = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_servicearea);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ServiceAreaPresenter(this);
        mListViewAdapter = new ServiceAreaClassificationListViewAdapter(this);
        mGridViewAdapter = new ServiceAreaClassificationGridViewAdapter(this);
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        //  ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
        titlebar.setTitleText(title);
        titlebar.setRightDrawable(getResources().getDrawable(R.mipmap.img_search));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                //分享
                Intent intent = new Intent(aty, AreaSearchActivity.class);
                intent.putExtra("type", type);
                showActivity(aty, intent);
            }
        };
        titlebar.setDelegate(simpleDelegate);
        lv_countries.setAdapter(mListViewAdapter);
        lv_countries.setOnItemClickListener(this);
        gv_countriesClassification.setAdapter(mGridViewAdapter);
        gv_countriesClassification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ServiceAreaContract.Presenter) mPresenter).getAirportCountryList();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.lv_countries) {
            selectClassification(position);
        } else if (adapterView.getId() == R.id.gv_countriesClassification) {
//            Intent intent = new Intent(aty, SelectProductAirportTransportationActivity.class);
//            intent.putExtra("airport_id", mGridViewAdapter.getItem(position).getAirport_id());
//            intent.putExtra("name", mGridViewAdapter.getItem(position).getCountry_name() + mGridViewAdapter.getItem(position).getRegion_name() + mGridViewAdapter.getItem(position).getAirport_name() + title);
//            intent.putExtra("type", type);
//            showActivity(aty, intent);
        }
    }

    @Override
    public void setPresenter(ServiceAreaContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ServiceAreaCountryListBean serviceAreaCountryListBean = (ServiceAreaCountryListBean) JsonUtil.getInstance().json2Obj(success, ServiceAreaCountryListBean.class);
            serviceAreaCountryList = serviceAreaCountryListBean.getData();
            if (serviceAreaCountryList != null && serviceAreaCountryList.size() > 0) {
                selectClassification(0);
            }
        } else if (flag == 1) {
            ServiceAreaByCountryIdBean airportByCountryIdBean = (ServiceAreaByCountryIdBean) JsonUtil.getInstance().json2Obj(success, ServiceAreaByCountryIdBean.class);
            mGridViewAdapter.clear();
            mGridViewAdapter.addNewData(airportByCountryIdBean.getData());
            dismissLoadingDialog();
        }
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < serviceAreaCountryList.size(); i++) {
            if (position == i || position == i && position == 0) {
                serviceAreaCountryBean = serviceAreaCountryList.get(i);
                serviceAreaCountryBean.setIsSelected(1);
                ((ServiceAreaContract.Presenter) mPresenter).getAirportByCountryId(serviceAreaCountryBean.getCountry_id());
            } else {
                serviceAreaCountryList.get(i).setIsSelected(0);
            }
        }
        mListViewAdapter.clear();
        mListViewAdapter.addNewData(serviceAreaCountryList);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }

}
