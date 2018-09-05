package com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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


    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    private ServiceAreaClassificationListViewAdapter mListViewAdapter = null;

    private ServiceAreaClassificationGridViewAdapter mGridViewAdapter = null;

    private List<ServiceAreaCountryListBean.DataBean> serviceAreaCountryList;

    private ServiceAreaCountryListBean.DataBean serviceAreaCountryBean = null;

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
    }

    @Override
    public void initWidget() {
        super.initWidget();
        titlebar.setTitleText(getString(R.string.serviceArea));
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
                showActivity(aty, intent);
            }
        };
        titlebar.setDelegate(simpleDelegate);
        lv_countries.setAdapter(mListViewAdapter);
        lv_countries.setOnItemClickListener(this);
        gv_countriesClassification.setAdapter(mGridViewAdapter);
        gv_countriesClassification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ServiceAreaContract.Presenter) mPresenter).getCountryList();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    ((ServiceAreaContract.Presenter) mPresenter).getCityList(serviceAreaCountryBean.getCountry_id());
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.lv_countries) {
            selectClassification(position);
        } else if (adapterView.getId() == R.id.gv_countriesClassification) {
            Intent intent = getIntent();
            intent.putExtra("city_id", mGridViewAdapter.getItem(position).getCity_id());
            intent.putExtra("city_name", mGridViewAdapter.getItem(position).getCity_name());
            setResult(RESULT_OK, intent);
            finish();
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
            if (airportByCountryIdBean.getData() == null || airportByCountryIdBean.getData().size() <= 0) {
                errorMsg(getString(R.string.noData), 1);
                return;
            }
            ll_commonError.setVisibility(View.GONE);
            gv_countriesClassification.setVisibility(View.VISIBLE);
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
                ((ServiceAreaContract.Presenter) mPresenter).getCityList(serviceAreaCountryBean.getCountry_id());
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
        if (flag == 1) {
            ll_commonError.setVisibility(View.VISIBLE);
            gv_countriesClassification.setVisibility(View.GONE);
            if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.noData))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
            return;
        }
        ViewInject.toast(msg);
    }

}
