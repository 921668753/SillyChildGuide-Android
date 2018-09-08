package com.yinglan.scg.mine.myvehicle;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.common.pictureselector.FullyGridLayoutManager;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.FileUtils;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yinglan.scg.R;
import com.yinglan.scg.adapter.mine.myvehicle.GridImageAdapter;
import com.yinglan.scg.entity.mine.myvehicle.PeopleBean;
import com.yinglan.scg.entity.mine.myvehicle.VehicleDetailsBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.myvehicle.model.ModelActivity;
import com.yinglan.scg.mine.personaldata.authenticationinformation.dialog.SubmitAuditDialog;
import com.yinglan.scg.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_PRODUCT;


/**
 * 新增车辆
 */
public class AddVehicleActivity extends BaseActivity implements AddVehicleContract.View, GridImageAdapter.OnItemClickListener {

    @BindView(id = R.id.rv_vehicleImages)
    private RecyclerView rv_vehicleImages;

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

    @BindView(id = R.id.et_licensePlateNumber)
    private EditText et_licensePlateNumber;

    @BindView(id = R.id.img_vehiclePassengerInsurance, click = true)
    private ImageView img_vehiclePassengerInsurance;

    @BindView(id = R.id.tv_submitAudit, click = true)
    private TextView tv_submitAudit;

    private int model_name_id = 0;
    private int model_id = 0;

    private List<LocalMedia> selectList = null;
    private GridImageAdapter adapter;
    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();
    private int aspect_ratio_x = 16, aspect_ratio_y = 9;
    private int maxSelectNum = 9;


    private TimePickerView pvCustomTime;
    private long model_year = 0;
    private OptionsPickerView peopleOptions = null;
    private int passenger_number = 0;
    private OptionsPickerView luggageOptions = null;
    private int baggage_number = 0;
    private int is_insurance = 1;
    private SubmitAuditDialog submitAuditDialog;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_addvehicle);
    }

    @Override
    public void initData() {
        super.initData();
        model_id = getIntent().getIntExtra("model_id", 0);
        mPresenter = new AddVehiclePresenter(this);
        selectList = new ArrayList<LocalMedia>();
        themeId = R.style.picture_default_style;
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        initDialog();
        initCustomTimePicker();
        initOptions();
    }

    private void initDialog() {
        submitAuditDialog = new SubmitAuditDialog(this) {
            @Override
            public void submitAuditDo() {
                dismiss();
                postEidtCertification1();
            }
        };
    }

    private void postEidtCertification1() {
        showLoadingDialog(getString(R.string.submissionLoad));
        ((AddVehicleContract.Presenter) mPresenter).postEidtModel1(model_id, selectList, model_name_id, passenger_number, baggage_number, model_year,
                et_licensePlateNumber.getText().toString().trim(), is_insurance);
    }

    private void initCustomTimePicker() {
        Calendar birthdaycalendar = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(birthdaycalendar.get(Calendar.YEAR) - 15, birthdaycalendar.get(Calendar.MONTH), birthdaycalendar.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(birthdaycalendar.get(Calendar.YEAR), birthdaycalendar.get(Calendar.MONTH), birthdaycalendar.get(Calendar.DAY_OF_MONTH));
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                model_year = date.getTime() / 1000;
                ((TextView) v).setText(DataUtil.formatData(date.getTime() / 1000, "yyyy"));
            }
        })
                .setRangDate(startDate, endDate)
                .setContentTextSize(18)
                .setDate(Calendar.getInstance())
                .setType(new boolean[]{true, false, false, false, false, false})
                .setLabel(getString(R.string.year), getString(R.string.month), getString(R.string.day), getString(R.string.hour), getString(R.string.minute), getString(R.string.seconds))
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFFd5d5d5)
                .build();
    }

    private void initOptions() {
        List<PeopleBean> list = new ArrayList<PeopleBean>();
        for (int i = 0; i < 8; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.people));
            list.add(peopleBean);
        }
        peopleOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                passenger_number = list.get(options1).getNum();
                ((TextView) v).setText(list.get(options1).getPickerViewText());
            }
        }).build();
        peopleOptions.setPicker(list);
        List<PeopleBean> list1 = new ArrayList<PeopleBean>();
        for (int i = 0; i < 8; i++) {
            PeopleBean peopleBean = new PeopleBean();
            peopleBean.setNum(i + 1);
            peopleBean.setName(i + 1 + getString(R.string.jian));
            list1.add(peopleBean);
        }
        luggageOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                baggage_number = list1.get(options1).getNum();
                ((TextView) v).setText(list1.get(options1).getPickerViewText());
            }
        }).build();
        luggageOptions.setPicker(list1);
    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            if (selectList.size() > 0) {
                String pictureType = selectList.get(selectList.size() - 1).getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                if (mediaType == 2 || mediaType == 3) {
                    ViewInject.toast(getString(R.string.videoOnlyAddOne));
                    return;
                }
            }
            // 进入相册 以下是例子：不需要的api可以不写
            PictureSelector.create(AddVehicleActivity.this)
                    .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                    .previewImage(true)// 是否可预览图片
                    .previewVideo(true)// 是否可预览视频
                    .enablePreviewAudio(true) // 是否可播放音频
                    .compress(false)// 是否压缩
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .isGif(true)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    .minimumCompressSize(StringConstants.COMPRESSION_SIZE)// 小于100kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }
    };

    @Override
    public void initWidget() {
        super.initWidget();
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rv_vehicleImages.setLayoutManager(manager);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        rv_vehicleImages.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(AddVehicleActivity.this);
                } else {
                    ViewInject.toast(getString(R.string.picture_jurisdiction));
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
        if (model_id > 0) {
            ActivityTitleUtils.initToolbar(aty, getString(R.string.vehicleDetails), true, R.id.titlebar);
            showLoadingDialog(getString(R.string.dataLoad));
            ((AddVehicleContract.Presenter) mPresenter).getModelDetail(model_id);
            return;
        }
        ActivityTitleUtils.initToolbar(aty, getString(R.string.addVehicle), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_model:
                Intent intent = new Intent(aty, ModelActivity.class);
                startActivityForResult(intent, RESULT_CODE_PRODUCT);
                break;
            case R.id.ll_canTakeNumber:
                SoftKeyboardUtils.packUpKeyboard(this);
                peopleOptions.show(tv_canTakeNumber);
                break;
            case R.id.ll_canPutBaggage:
                SoftKeyboardUtils.packUpKeyboard(this);
                luggageOptions.show(tv_canPutBaggage);
                break;
            case R.id.ll_vehicleYear:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvCustomTime.show(tv_vehicleYear);
                break;
            case R.id.img_vehiclePassengerInsurance:
                if (is_insurance == 1) {
                    img_vehiclePassengerInsurance.setImageResource(R.mipmap.img_turn_off);
                    is_insurance = 0;
                } else {
                    img_vehiclePassengerInsurance.setImageResource(R.mipmap.img_turn_on);
                    is_insurance = 1;
                }
                break;
            case R.id.tv_submitAudit:
                ((AddVehicleContract.Presenter) mPresenter).postEidtModel(model_id, selectList, model_name_id, passenger_number, baggage_number, model_year,
                        et_licensePlateNumber.getText().toString().trim(), is_insurance);
                break;
        }
    }


    @Override
    public void onItemClick(int position, View v) {
        if (selectList.size() > 0) {
            LocalMedia media = selectList.get(position);
            String pictureType = media.getPictureType();
            int mediaType = PictureMimeType.pictureToVideo(pictureType);
            switch (mediaType) {
                case 1:
                    // 预览图片 可自定长按保存路径
                    //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                    PictureSelector.create(AddVehicleActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                    break;
            }
        }
    }

    @Override
    public void setPresenter(AddVehicleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            VehicleDetailsBean vehicleDetailsBean = (VehicleDetailsBean) JsonUtil.getInstance().json2Obj(success, VehicleDetailsBean.class);
            if (vehicleDetailsBean == null || vehicleDetailsBean.getData() == null || vehicleDetailsBean.getData().getModel() == null || vehicleDetailsBean.getData().getModel().getId() <= 0) {
                errorMsg(getString(R.string.serverReturnsDataNullJsonError), 0);
                return;
            }
            model_id = vehicleDetailsBean.getData().getModel().getId();
            model_name_id = vehicleDetailsBean.getData().getModel().getModel_name_id();
            tv_model.setText(vehicleDetailsBean.getData().getModel().getModel_name());
            passenger_number = vehicleDetailsBean.getData().getModel().getPassenger_number();
            peopleOptions.setSelectOptions(passenger_number - 1);
            tv_canTakeNumber.setText(passenger_number + getString(R.string.people));
            baggage_number = vehicleDetailsBean.getData().getModel().getBaggage_number();
            luggageOptions.setSelectOptions(baggage_number - 1);
            tv_canPutBaggage.setText(baggage_number + getString(R.string.jian));
            model_year = StringUtils.toLong(vehicleDetailsBean.getData().getModel().getModel_year());
            Calendar calendar = Calendar.getInstance();
            Date date = new Date(model_year * 1000);
            calendar.setTime(date);
            pvCustomTime.setDate(calendar);
            tv_vehicleYear.setText(DataUtil.formatData(model_year, "yyyy"));
            et_licensePlateNumber.setText(vehicleDetailsBean.getData().getModel().getLicense_plate());
            if (vehicleDetailsBean.getData().getModel().getIs_insurance() == 1) {
                is_insurance = 1;
                img_vehiclePassengerInsurance.setImageResource(R.mipmap.img_turn_on);
            } else {
                is_insurance = 0;
                img_vehiclePassengerInsurance.setImageResource(R.mipmap.img_turn_off);
            }
            for (int i = 0; i < vehicleDetailsBean.getData().getModel().getModel_pictures().size(); i++) {
                LocalMedia localMedia1 = new LocalMedia();
                localMedia1.setHttpPath(vehicleDetailsBean.getData().getModel().getModel_pictures().get(i));
                localMedia1.setPath(vehicleDetailsBean.getData().getModel().getModel_pictures().get(i));
                localMedia1.setPictureType("image/jpeg");
                selectList.add(localMedia1);
            }
            adapter.setList(selectList);
            adapter.notifyDataSetChanged();
        } else if (flag == 1) {
            if (submitAuditDialog == null) {
                initDialog();
            }
            if (submitAuditDialog != null && !submitAuditDialog.isShowing()) {
                submitAuditDialog.show();
            }
        } else if (flag == 2) {
            ViewInject.toast(getString(R.string.submitSuccess));
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
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
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case RESULT_CODE_PRODUCT:
                    // 车型选择结果回调
                    String model_name = data.getStringExtra("model_name");
                    model_name_id = data.getIntExtra("model_name_id", 0);
                    tv_model.setText(model_name);
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (submitAuditDialog != null) {
            submitAuditDialog.cancel();
        }
        submitAuditDialog = null;
        adapter = null;
        selectList.clear();
        selectList = null;
    }
}
