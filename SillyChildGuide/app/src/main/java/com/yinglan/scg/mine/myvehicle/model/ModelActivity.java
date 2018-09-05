package com.yinglan.scg.mine.myvehicle.model;


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
import com.yinglan.scg.adapter.mine.myvehicle.model.ModelClassificationGridViewAdapter;
import com.yinglan.scg.adapter.mine.myvehicle.model.ModelClassificationListViewAdapter;
import com.yinglan.scg.entity.mine.myvehicle.model.ModelBrandListBean;
import com.yinglan.scg.entity.mine.myvehicle.model.ModelNameListBean;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.ServiceAreaByCountryIdBean;
import com.yinglan.scg.entity.mine.personaldata.authenticationinformation.ServiceAreaCountryListBean;
import com.yinglan.scg.mine.myvehicle.model.search.ModelSearchActivity;

import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 车型
 */
public class ModelActivity extends BaseActivity implements ModelContract.View, AdapterView.OnItemClickListener {

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

    private ModelClassificationListViewAdapter mListViewAdapter = null;

    private ModelClassificationGridViewAdapter mGridViewAdapter = null;

    private List<ModelBrandListBean.DataBean> modelBrandList;

    private ModelBrandListBean.DataBean modelBrandBean = null;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_servicearea);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ModelPresenter(this);
        mListViewAdapter = new ModelClassificationListViewAdapter(this);
        mGridViewAdapter = new ModelClassificationGridViewAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        titlebar.setTitleText(getString(R.string.model));
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
                Intent intent = new Intent(aty, ModelSearchActivity.class);
                showActivity(aty, intent);
            }
        };
        titlebar.setDelegate(simpleDelegate);
        lv_countries.setAdapter(mListViewAdapter);
        lv_countries.setOnItemClickListener(this);
        gv_countriesClassification.setAdapter(mGridViewAdapter);
        gv_countriesClassification.setOnItemClickListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ModelContract.Presenter) mPresenter).getModelBrandList();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    ((ModelContract.Presenter) mPresenter).getModelNameList(modelBrandBean.getModel_brand_id());
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
            intent.putExtra("model_name_id", mGridViewAdapter.getItem(position).getModel_name_id());
            intent.putExtra("model_name", mGridViewAdapter.getItem(position).getModel_name());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void setPresenter(ModelContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ModelBrandListBean modelBrandListBean = (ModelBrandListBean) JsonUtil.getInstance().json2Obj(success, ModelBrandListBean.class);
            modelBrandList = modelBrandListBean.getData();
            if (modelBrandList != null && modelBrandList.size() > 0) {
                selectClassification(0);
            }
        } else if (flag == 1) {
            ModelNameListBean modelNameListBean = (ModelNameListBean) JsonUtil.getInstance().json2Obj(success, ModelNameListBean.class);
            if (modelNameListBean.getData() == null || modelNameListBean.getData().size() <= 0) {
                errorMsg(getString(R.string.noData), 1);
                return;
            }
            ll_commonError.setVisibility(View.GONE);
            gv_countriesClassification.setVisibility(View.VISIBLE);
            mGridViewAdapter.clear();
            mGridViewAdapter.addNewData(modelNameListBean.getData());
            dismissLoadingDialog();
        }
    }

    /**
     * 选中分类
     *
     * @param position
     */
    private void selectClassification(int position) {
        for (int i = 0; i < modelBrandList.size(); i++) {
            if (position == i || position == i && position == 0) {
                modelBrandBean = modelBrandList.get(i);
                modelBrandBean.setIsSelected(1);
                ((ModelContract.Presenter) mPresenter).getModelNameList(modelBrandBean.getModel_brand_id());
            } else {
                modelBrandList.get(i).setIsSelected(0);
            }
        }
        mListViewAdapter.clear();
        mListViewAdapter.addNewData(modelBrandList);
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
