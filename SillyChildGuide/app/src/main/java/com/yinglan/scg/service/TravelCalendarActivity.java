package com.yinglan.scg.service;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.rxbus2.Subscribe;
import com.luck.picture.lib.rxbus2.ThreadMode;
import com.yinglan.scg.R;
import com.yinglan.scg.entity.service.TravelCalendarBean;
import com.yinglan.scg.loginregister.LoginActivity;
import com.yinglan.scg.service.dialog.TripDialog;
import com.yinglan.scg.utils.custompicker.adapter.MonthTimeAdapter;
import com.yinglan.scg.utils.custompicker.bean.DayTimeEntity;
import com.yinglan.scg.utils.custompicker.bean.MonthTimeEntity;
import com.yinglan.scg.utils.custompicker.bean.UpdataCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import io.rong.eventbus.EventBus;

/**
 * 行程日历
 */
public class TravelCalendarActivity extends BaseActivity implements TravelCalendarContract.View {

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    private MonthTimeAdapter adapter;
    private ArrayList<MonthTimeEntity> datas;
    public static ArrayList<DayTimeEntity> dayDatas;
    public static DayTimeEntity selectDay;
    private TripDialog tripDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_travelcalendar);
    }

    @Override
    public void initData() {
        super.initData();
        selectDay = new DayTimeEntity(0, 0, 0, 0);
        dayDatas = new ArrayList<DayTimeEntity>();
        datas = new ArrayList<>();
        mPresenter = new TravelCalendarPresenter(this);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        int nextYear = c.get(Calendar.YEAR);
        int nextMonth = c.get(Calendar.MONTH);
        for (int i = 0; i < 12; i++) {
            datas.add(new MonthTimeEntity(nextYear, nextMonth, nextYear + "--" + nextMonth));
            if (nextMonth == 12) {
                nextMonth = 0;
                nextYear = nextYear + 1;
            }
            nextMonth = nextMonth + 1;
        }
        adapter = new MonthTimeAdapter(datas, this);
        EventBus.getDefault().register(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((TravelCalendarContract.Presenter) mPresenter).getDateList(aty);
    }

    private void initDialog() {
        tripDialog = new TripDialog(aty);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.travelCalendar), true, R.id.titlebar);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this,   // 上下文
                        LinearLayout.VERTICAL,  //垂直布局,
                        false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        // recyclerView.scrollToPosition(selectDay.getMonthPosition());
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdataCalendar event) {
        //  adapter.notifyDataSetChanged();
//        tv_chooseTimeNeedServe.setText(getString(R.string.startTime) + selectDay.getYear() + getString(R.string.year) + selectDay.getMonth() + getString(R.string.month) +
//                selectDay.getDay() + getString(R.string.day));
        if (selectDay.getStatus() != 102) {
            return;
        }
        if (tripDialog == null) {
            initDialog();
        }
        if (tripDialog != null && !tripDialog.isShowing()) {
            tripDialog.show();
            long time = DataUtil.getStringToDate(selectDay.getYear() + "-" + selectDay.getMonth() + "-" + selectDay.getDay() + " 00:00", "yyyy-MM-dd HH:mm") / 1000;
            tripDialog.setTripTime(time);
        }
    }


    @Override
    public void setPresenter(TravelCalendarContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        TravelCalendarBean travelCalendarBean = (TravelCalendarBean) JsonUtil.getInstance().json2Obj(success, TravelCalendarBean.class);
        if (travelCalendarBean == null || travelCalendarBean.getData() == null || travelCalendarBean.getData().size() <= 0) {
            return;
        }
        int year = StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "yyyy"));
        int month = StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "MM"));
        for (int i = 0; i < travelCalendarBean.getData().size(); i++) {
            int newYear = StringUtils.toInt(DataUtil.formatData(travelCalendarBean.getData().get(i), "yyyy"));
            int newMonth = StringUtils.toInt(DataUtil.formatData(travelCalendarBean.getData().get(i), "MM"));
            int day = StringUtils.toInt(DataUtil.formatData(travelCalendarBean.getData().get(i), "dd"));
            DayTimeEntity dayTimeEntity = null;
            if (newYear == year) {
                dayTimeEntity = new DayTimeEntity(day, newMonth, newYear, newMonth - month, 102);
            } else {
                dayTimeEntity = new DayTimeEntity(day, newMonth, newYear, 12 - month + (newYear - year - 1) * 12 + newMonth, 102);
            }
            dayTimeEntity.setTime(travelCalendarBean.getData().get(i));
            dayDatas.add(dayTimeEntity);
        }
        adapter.notifyDataSetChanged();
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
        if (tripDialog != null) {
            tripDialog.cancel();
        }
        tripDialog = null;
        EventBus.getDefault().unregister(this);
    }

}
