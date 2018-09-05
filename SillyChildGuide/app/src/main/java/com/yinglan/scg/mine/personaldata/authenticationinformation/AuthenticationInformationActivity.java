package com.yinglan.scg.mine.personaldata.authenticationinformation;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.FileUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.yinglan.scg.R;
import com.yinglan.scg.constant.NumericConstants;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.mine.personaldata.authenticationinformation.dialog.SubmitAuditDialog;
import com.yinglan.scg.mine.personaldata.authenticationinformation.servicearea.ServiceAreaActivity;
import com.yinglan.scg.utils.GlideImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_GET;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_ADD;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MINUS;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MINUSALL;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_BASKET_MOVE;
import static com.yinglan.scg.constant.NumericConstants.RESULT_CODE_PRODUCT;

/**
 * 认证信息
 */
public class AuthenticationInformationActivity extends BaseActivity implements AuthenticationInformationContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(id = R.id.ll_serviceArea, click = true)
    private LinearLayout ll_serviceArea;

    @BindView(id = R.id.tv_serviceArea)
    private TextView tv_serviceArea;

    @BindView(id = R.id.img_carCompany, click = true)
    private ImageView img_carCompany;

    @BindView(id = R.id.img_drivingLicence, click = true)
    private ImageView img_drivingLicence;

    @BindView(id = R.id.img_holdCardFace, click = true)
    private ImageView img_holdCardFace;

    @BindView(id = R.id.img_legally, click = true)
    private ImageView img_legally;

    @BindView(id = R.id.img_yachtDrivingLicense, click = true)
    private ImageView img_yachtDrivingLicense;

    @BindView(id = R.id.tv_submitAudit, click = true)
    private TextView tv_submitAudit;


    private int themeId;
    private int chooseMode = PictureMimeType.ofImage();
    private int aspect_ratio_x = 16, aspect_ratio_y = 9;

    private int type = 0;

    private boolean isImgCarCompany = true;
    private List<LocalMedia> selectCarCompanyList = null;

    private boolean isImgDrivingLicence = true;
    private List<LocalMedia> selectDrivingLicenceList = null;

    private boolean isImgHoldCardFace = true;
    private List<LocalMedia> selectHoldCardFaceList = null;

    private boolean isImgLegally = true;
    private List<LocalMedia> selectLegallyList = null;

    private boolean isImgYachtDrivingLicense = true;
    private List<LocalMedia> selectYachtDrivingLicenseList = null;

    private int city_id = 0;
    private SubmitAuditDialog submitAuditDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_authenticationinformation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AuthenticationInformationPresenter(this);
        selectCarCompanyList = new ArrayList<LocalMedia>();
        selectDrivingLicenceList = new ArrayList<LocalMedia>();
        selectHoldCardFaceList = new ArrayList<LocalMedia>();
        selectLegallyList = new ArrayList<LocalMedia>();
        selectYachtDrivingLicenseList = new ArrayList<LocalMedia>();
        initDialog();
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
        ((AuthenticationInformationContract.Presenter) mPresenter).postEidtCertification1(city_id, selectCarCompanyList, selectDrivingLicenceList, selectHoldCardFaceList,
                selectLegallyList, selectYachtDrivingLicenseList);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.authenticationInformation), true, R.id.titlebar);
        themeId = R.style.picture_default_style;

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_serviceArea:
                Intent intent = new Intent(aty, ServiceAreaActivity.class);
                startActivityForResult(intent, RESULT_CODE_PRODUCT);
                break;
            case R.id.img_carCompany:
                type = RESULT_CODE_GET;
                if (isImgCarCompany) {
                    choicePhotoWrapper();
                } else {
                    PictureSelector.create(this).themeStyle(themeId).openExternalPreview(0, selectCarCompanyList, true);
                }
                break;
            case R.id.img_drivingLicence:
                type = RESULT_CODE_BASKET_ADD;
                if (isImgDrivingLicence) {
                    choicePhotoWrapper();
                } else {
                    PictureSelector.create(this).themeStyle(themeId).openExternalPreview(0, selectDrivingLicenceList, true);
                }
                break;
            case R.id.img_holdCardFace:
                type = RESULT_CODE_BASKET_MINUS;
                if (isImgHoldCardFace) {
                    choicePhotoWrapper();
                } else {
                    PictureSelector.create(this).themeStyle(themeId).openExternalPreview(0, selectHoldCardFaceList, true);
                }
                break;
            case R.id.img_legally:
                type = RESULT_CODE_BASKET_MINUSALL;
                if (isImgLegally) {
                    choicePhotoWrapper();
                } else {
                    PictureSelector.create(this).themeStyle(themeId).openExternalPreview(0, selectLegallyList, true);
                }
                break;
            case R.id.img_yachtDrivingLicense:
                type = RESULT_CODE_BASKET_MOVE;
                if (isImgYachtDrivingLicense) {
                    choicePhotoWrapper();
                } else {
                    PictureSelector.create(this).themeStyle(themeId).openExternalPreview(0, selectYachtDrivingLicenseList, true);
                }
                break;
            case R.id.tv_submitAudit:
                ((AuthenticationInformationContract.Presenter) mPresenter).postEidtCertification(city_id, selectCarCompanyList, selectDrivingLicenceList, selectHoldCardFaceList,
                        selectLegallyList, selectYachtDrivingLicenseList);
                break;
        }
    }


    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            PictureSelector.create(this)
                    .openGallery(chooseMode)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .selectionMode(PictureConfig.SINGLE)
                    .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(4)// 每行显示个数
                    .isCamera(true)// 是否显示拍照按钮
                    .previewImage(true)// 是否可预览图片 true or false
                    .enableCrop(false)// 是否裁剪
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .minimumCompressSize(StringConstants.COMPRESSION_SIZE)// 小于100kb的图片不压缩
                    .setOutputCameraPath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())// 自定义拍照保存路径
                    .compressSavePath(FileUtils.getSaveFolder(StringConstants.PHOTOPATH).getAbsolutePath())//压缩图片保存地址
                    .forResult(type);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.needPermission), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
            ViewInject.toast(getString(R.string.denyPermission));
        }
    }


    @Override
    public void setPresenter(AuthenticationInformationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            if (submitAuditDialog == null) {
                initDialog();
            }
            if (submitAuditDialog != null && !submitAuditDialog.isShowing()) {
                submitAuditDialog.show();
            }
        } else if (flag == 1) {
            if (submitAuditDialog == null) {
                initDialog();
            }
            if (submitAuditDialog != null && !submitAuditDialog.isShowing()) {
                submitAuditDialog.show();
            }
            submitAuditDialog.setContentText();
        } else if (flag == 2) {


        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0 && submitAuditDialog != null && submitAuditDialog.isShowing()) {
            submitAuditDialog.dismiss();
        }
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 2) {
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
                case RESULT_CODE_GET:
                    // 图片选择结果回调
                    selectCarCompanyList = PictureSelector.obtainMultipleResult(data);
                    if (selectCarCompanyList == null || selectCarCompanyList.size() <= 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    isImgCarCompany = false;
                    GlideImageLoader.glideOrdinaryLoader(this, selectCarCompanyList.get(0).getPath(), img_carCompany, R.mipmap.placeholderfigure);
                    break;
                case RESULT_CODE_BASKET_ADD:
                    // 图片选择结果回调
                    selectDrivingLicenceList = PictureSelector.obtainMultipleResult(data);
                    if (selectDrivingLicenceList == null || selectDrivingLicenceList.size() <= 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    isImgDrivingLicence = false;
                    GlideImageLoader.glideOrdinaryLoader(this, selectDrivingLicenceList.get(0).getPath(), img_drivingLicence, R.mipmap.placeholderfigure);
                    break;
                case RESULT_CODE_BASKET_MINUS:
                    // 图片选择结果回调
                    selectHoldCardFaceList = PictureSelector.obtainMultipleResult(data);
                    if (selectHoldCardFaceList == null || selectHoldCardFaceList.size() <= 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    isImgHoldCardFace = false;
                    GlideImageLoader.glideOrdinaryLoader(this, selectHoldCardFaceList.get(0).getPath(), img_holdCardFace, R.mipmap.placeholderfigure);
                    break;
                case RESULT_CODE_BASKET_MINUSALL:
                    // 图片选择结果回调
                    selectLegallyList = PictureSelector.obtainMultipleResult(data);
                    if (selectLegallyList == null || selectLegallyList.size() <= 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    isImgLegally = false;
                    GlideImageLoader.glideOrdinaryLoader(this, selectLegallyList.get(0).getPath(), img_legally, R.mipmap.placeholderfigure);
                    break;
                case RESULT_CODE_BASKET_MOVE:
                    // 图片选择结果回调
                    selectYachtDrivingLicenseList = PictureSelector.obtainMultipleResult(data);
                    if (selectYachtDrivingLicenseList == null || selectYachtDrivingLicenseList.size() <= 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    isImgYachtDrivingLicense = false;
                    GlideImageLoader.glideOrdinaryLoader(this, selectYachtDrivingLicenseList.get(0).getPath(), img_yachtDrivingLicense, R.mipmap.placeholderfigure);
                    break;
                case RESULT_CODE_PRODUCT:
                    // 服务地区选择结果回调
                    String cityName = data.getStringExtra("city_name");
                    city_id = data.getIntExtra("city_id", 0);
                    tv_serviceArea.setText(cityName);
                    break;
                case PictureConfig.DEL_FLAG:
                    List<LocalMedia> selectList = (List<LocalMedia>) data.getSerializableExtra(PictureConfig.EXTRA_PREVIEW_SELECT_LIST);
                    if (selectList == null || selectList.size() <= 0) {
                        selectList = null;
                    }
                    // 服务地区选择结果回调
                    if (type == RESULT_CODE_GET && selectList == null) {
                        isImgCarCompany = true;
                        selectCarCompanyList.clear();
                        img_carCompany.setImageResource(R.mipmap.img_car_company);
                    } else if (type == RESULT_CODE_GET && selectList != null) {
                        selectCarCompanyList = selectList;
                    } else if (type == RESULT_CODE_BASKET_ADD && selectList == null) {
                        isImgDrivingLicence = true;
                        selectDrivingLicenceList.clear();
                        img_drivingLicence.setImageResource(R.mipmap.img_driving_licence);
                    } else if (type == RESULT_CODE_BASKET_ADD && selectList != null) {
                        selectDrivingLicenceList = selectList;
                    } else if (type == RESULT_CODE_BASKET_MINUS && selectList == null) {
                        isImgHoldCardFace = true;
                        selectHoldCardFaceList.clear();
                        img_holdCardFace.setImageResource(R.mipmap.img_hold_card_face);
                    } else if (type == RESULT_CODE_BASKET_MINUS && selectList != null) {
                        selectHoldCardFaceList = selectList;
                    } else if (type == RESULT_CODE_BASKET_MINUSALL && selectList == null) {
                        isImgLegally = true;
                        selectLegallyList.clear();
                        img_legally.setImageResource(R.mipmap.img_legally);
                    } else if (type == RESULT_CODE_BASKET_MINUSALL && selectList != null) {
                        selectLegallyList = selectList;
                    } else if (type == RESULT_CODE_BASKET_MOVE && selectList == null) {
                        isImgYachtDrivingLicense = true;
                        selectYachtDrivingLicenseList.clear();
                        img_yachtDrivingLicense.setImageResource(R.mipmap.img_yacht_driving_license);
                    } else if (type == RESULT_CODE_BASKET_MOVE && selectList != null) {
                        selectYachtDrivingLicenseList = selectList;
                    }
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
        selectCarCompanyList.clear();
        selectCarCompanyList = null;
        selectDrivingLicenceList.clear();
        selectDrivingLicenceList = null;
        selectHoldCardFaceList.clear();
        selectHoldCardFaceList = null;
        selectLegallyList.clear();
        selectLegallyList = null;
        selectYachtDrivingLicenseList.clear();
        selectYachtDrivingLicenseList = null;
    }


}
