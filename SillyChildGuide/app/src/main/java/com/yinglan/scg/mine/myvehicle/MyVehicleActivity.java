package com.yinglan.scg.mine.myvehicle;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myvehicle.MyVehicleViewAdapter;
import com.yinglan.scg.entity.mine.myvehicle.MyVehicleBean;
import com.yinglan.scg.loginregister.LoginActivity;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;

import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 我的车辆
 */
public class MyVehicleActivity extends BaseActivity implements MyVehicleContract.View, AdapterView.OnItemClickListener, BGAOnItemChildClickListener {

    @BindView(id = R.id.clv_car)
    private ChildListView clv_car;

    @BindView(id = R.id.img_addCar, click = true)
    private ImageView img_addCar;

    private MyVehicleViewAdapter mAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myvehicle);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyVehiclePresenter(this);
        mAdapter = new MyVehicleViewAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyVehicleContract.Presenter) mPresenter).getMyVehicleList();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myVehicle), true, R.id.titlebar);
        clv_car.setAdapter(mAdapter);
        clv_car.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_addCar:
                Intent intent = new Intent(aty, AddVehicleActivity.class);
                startActivityForResult(intent, RESULT_CODE_GET);
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_delete) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(aty, AddVehicleActivity.class);
        intent.putExtra("model_id", mAdapter.getItem(position).getId());
        startActivityForResult(intent, RESULT_CODE_GET);
    }

    @Override
    public void setPresenter(MyVehicleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            MyVehicleBean myVehicleBean = (MyVehicleBean) JsonUtil.getInstance().json2Obj(success, MyVehicleBean.class);
            if (myVehicleBean == null || myVehicleBean.getData() == null || myVehicleBean.getData().size() <= 0) {
                return;
            }
            mAdapter.clear();
            mAdapter.addNewData(myVehicleBean.getData());
        } else if (flag == 1) {


        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((MyVehicleContract.Presenter) mPresenter).getMyVehicleList();
    }


}
