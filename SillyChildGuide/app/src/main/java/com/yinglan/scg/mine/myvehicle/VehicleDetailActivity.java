package com.yinglan.scg.mine.myvehicle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myvehicle.ModelImgGridViewAdapter;
import com.yinglan.scg.entity.mine.myvehicle.VehicleDetailsBean;
import com.yinglan.scg.loginregister.LoginActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆详情
 */
public class VehicleDetailActivity extends BaseActivity implements VehicleDetailContract.View, AdapterView.OnItemClickListener {

    @BindView(id = R.id.ngv_vehicleImages)
    private NoScrollGridView ngv_vehicleImages;

    @BindView(id = R.id.ll_model, click = true)
    private LinearLayout ll_model;

    @BindView(id = R.id.tv_model)
    private TextView tv_model;

    @BindView(id = R.id.ll_canTakeNumber, click = true)
    private LinearLayout ll_canTakeNumber;

    @BindView(id = R.id.tv_canTakeNumber)
    private TextView tv_canTakeNumber;

    @BindView(id = R.id.ll_canPutBaggage, click = true)
    private LinearLayout ll_canPutBaggage;

    @BindView(id = R.id.tv_canPutBaggage)
    private TextView tv_canPutBaggage;

    @BindView(id = R.id.ll_vehicleYear, click = true)
    private LinearLayout ll_vehicleYear;

    @BindView(id = R.id.tv_vehicleYear)
    private TextView tv_vehicleYear;

    @BindView(id = R.id.tv_licensePlateNumber)
    private TextView tv_licensePlateNumber;

    @BindView(id = R.id.img_vehiclePassengerInsurance, click = true)
    private ImageView img_vehiclePassengerInsurance;

    private List<LocalMedia> selectList = null;
    private ModelImgGridViewAdapter mAdapter;
    private int model_id = 0;
    private int themeId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehicledetails);
    }

    @Override
    public void initData() {
        super.initData();
        model_id = getIntent().getIntExtra("model_id", 0);
        mPresenter = new VehicleDetailPresenter(this);
        selectList = new ArrayList<LocalMedia>();
        themeId = R.style.picture_default_style;
        mAdapter = new ModelImgGridViewAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((VehicleDetailContract.Presenter) mPresenter).getModelDetail(model_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.vehicleDetails), true, R.id.titlebar);
        ngv_vehicleImages.setAdapter(mAdapter);
        ngv_vehicleImages.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mAdapter.getData().size() > 0) {
            LocalMedia media = mAdapter.getItem(position);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片 可自定长按保存路径
                    //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                    PictureSelector.create(VehicleDetailActivity.this).themeStyle(themeId).openExternalPreview(position, mAdapter.getData());
                    break;
            }
        }

    }

    @Override
    public void setPresenter(VehicleDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        VehicleDetailsBean vehicleDetailsBean = (VehicleDetailsBean) JsonUtil.getInstance().json2Obj(success, VehicleDetailsBean.class);
        if (vehicleDetailsBean.getData() == null || vehicleDetailsBean.getData().getModel() == null || vehicleDetailsBean.getData().getModel().getId() <= 0) {
            errorMsg(getString(R.string.serverReturnsDataNullJsonError), 0);
            return;
        }
        model_id = vehicleDetailsBean.getData().getModel().getId();
        tv_model.setText(vehicleDetailsBean.getData().getModel().getModel_name());

        tv_canTakeNumber.setText(vehicleDetailsBean.getData().getModel().getPassenger_number() + getString(R.string.people));
        tv_canPutBaggage.setText(vehicleDetailsBean.getData().getModel().getBaggage_number() + getString(R.string.jian));
        tv_vehicleYear.setText(DataUtil.formatData(StringUtils.toLong(vehicleDetailsBean.getData().getModel().getModel_year()), "yyyy"));
        tv_licensePlateNumber.setText(vehicleDetailsBean.getData().getModel().getLicense_plate());
        if (vehicleDetailsBean.getData().getModel().getIs_insurance() == 1) {
            img_vehiclePassengerInsurance.setImageResource(R.mipmap.img_turn_on);
        } else {
            img_vehiclePassengerInsurance.setImageResource(R.mipmap.img_turn_off);
        }
        for (int i = 0; i < vehicleDetailsBean.getData().getModel().getModel_picture().size(); i++) {
            LocalMedia localMedia1 = new LocalMedia();
            localMedia1.setHttpPath(vehicleDetailsBean.getData().getModel().getModel_picture().get(i));
            localMedia1.setPath(vehicleDetailsBean.getData().getModel().getModel_picture().get(i));
            localMedia1.setPictureType("image/jpeg");
            selectList.add(localMedia1);
        }
        mAdapter.clear();
        mAdapter.addNewData(selectList);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            skipActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectList.clear();
        selectList = null;
        mAdapter.clear();
        mAdapter = null;
    }
}
