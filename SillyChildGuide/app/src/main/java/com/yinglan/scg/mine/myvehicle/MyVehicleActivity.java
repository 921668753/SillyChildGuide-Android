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
import com.yinglan.scg.mine.myvehicle.dialog.SubmitDeleteDialog;

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
    private SubmitDeleteDialog submitDeleteDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myvehicle);
    }


    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyVehiclePresenter(this);
        mAdapter = new MyVehicleViewAdapter(this);
        initDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyVehicleContract.Presenter) mPresenter).getMyVehicleList();
    }

    private void initDialog() {
        submitDeleteDialog = new SubmitDeleteDialog(this) {
            @Override
            public void submitAuditDo(int id) {
                dismiss();
                postsubmitDelete(id);
            }
        };
    }

    private void postsubmitDelete(int id) {
        showLoadingDialog(getString(R.string.deleteLoad));
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
//            if (submitDeleteDialog == null) {
//                initDialog();
//            }
//            if (submitDeleteDialog != null && !submitDeleteDialog.isShowing()) {
//                submitDeleteDialog.show();
//                submitDeleteDialog.setId(mAdapter.getItem(position).getId());
//            }
            if (mAdapter.getItem(position).getIs_default() == 1) {
                ViewInject.toast(getString(R.string.vehicleAlreadyDefaultVehicle));
                return;
            }
            showLoadingDialog(getString(R.string.dataLoad));
            ((MyVehicleContract.Presenter) mPresenter).setModelDefault(mAdapter.getItem(position).getId());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mAdapter.getItem(position).getModel_status() == 1 || mAdapter.getItem(position).getModel_status() == 3) {
            Intent intent = new Intent(aty, VehicleDetailActivity.class);
            intent.putExtra("model_id", mAdapter.getItem(position).getId());
            startActivityForResult(intent, RESULT_CODE_GET);
            return;
        }
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

        if (flag == 0) {
            MyVehicleBean myVehicleBean = (MyVehicleBean) JsonUtil.getInstance().json2Obj(success, MyVehicleBean.class);
            if (myVehicleBean == null || myVehicleBean.getData() == null || myVehicleBean.getData().size() <= 0) {
                return;
            }
            mAdapter.clear();
            mAdapter.addNewData(myVehicleBean.getData());
            dismissLoadingDialog();
        } else if (flag == 1) {
            ((MyVehicleContract.Presenter) mPresenter).getMyVehicleList();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE_GET && resultCode == RESULT_OK) {
            ((MyVehicleContract.Presenter) mPresenter).getMyVehicleList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (submitDeleteDialog != null) {
            submitDeleteDialog.cancel();
        }
        submitDeleteDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }
}
