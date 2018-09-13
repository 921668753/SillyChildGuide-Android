package com.yinglan.scg.service;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataUtil;
import com.kymjs.common.StringUtils;
import com.luck.picture.lib.rxbus2.Subscribe;
import com.luck.picture.lib.rxbus2.ThreadMode;
import com.yinglan.scg.R;
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
public class TravelCalendarActivity extends BaseActivity {

    @BindView(id = R.id.tv_chooseTimeNeedServe)
    private TextView tv_chooseTimeNeedServe;

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
//        stopDay = (DayTimeEntity) getIntent().getSerializableExtra("stopDayBean");
//        if (startDay == null || stopDay == null) {
        int month = StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "MM"));
        DayTimeEntity startDay = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "dd"), 0), StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "MM")), StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "yyyy")), StringUtils.toInt(DataUtil.formatData(System.currentTimeMillis() / 1000, "MM")) - month);
//            stopDay = new DayTimeEntity(-1, -1, -1, -1);
//        }
        dayDatas.add(startDay);
        DayTimeEntity startDay1 = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 2 * 1000) / 1000, "dd"), 0),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 2 * 1000) / 1000, "MM")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 2 * 1000) / 1000, "yyyy")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 2 * 1000) / 1000, "MM")) - month);
        dayDatas.add(startDay1);
        DayTimeEntity startDay2 = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 4 * 1000) / 1000, "dd"), 0),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 4 * 1000) / 1000, "MM")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 4 * 1000) / 1000, "yyyy")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 4 * 1000) / 1000, "MM")) - month);
        dayDatas.add(startDay2);
        DayTimeEntity startDay3 = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 5 * 1000) / 1000, "dd"), 0),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 5 * 1000) / 1000, "MM")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 5 * 1000) / 1000, "yyyy")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 5 * 1000) / 1000, "MM")) - month);

        dayDatas.add(startDay3);
        DayTimeEntity startDay4 = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 7 * 1000) / 1000, "dd"), 0),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 7 * 1000) / 1000, "MM")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 7 * 1000) / 1000, "yyyy")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 7 * 1000) / 1000, "MM")) - month);
        dayDatas.add(startDay4);

        DayTimeEntity startDay5 = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 8 * 1000) / 1000, "dd"), 0),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 8 * 1000) / 1000, "MM")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 8 * 1000) / 1000, "yyyy")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 8 * 1000) / 1000, "MM")) - month);
        dayDatas.add(startDay5);
        DayTimeEntity startDay6 = new DayTimeEntity(StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 9 * 1000) / 1000, "dd"), 0),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 9 * 1000) / 1000, "MM")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 9 * 1000) / 1000, "yyyy")),
                StringUtils.toInt(DataUtil.formatData((System.currentTimeMillis() + 60 * 60 * 24 * 9 * 1000) / 1000, "MM")) - month);
        dayDatas.add(startDay6);


        datas = new ArrayList<>();
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
    }

    private void initDialog() {
        tripDialog = new TripDialog(this);
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
        recyclerView.scrollToPosition(selectDay.getMonthPosition());
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdataCalendar event) {
        //adapter.notifyDataSetChanged();
//        tv_chooseTimeNeedServe.setText(getString(R.string.startTime) + selectDay.getYear() + getString(R.string.year) + selectDay.getMonth() + getString(R.string.month) +
//                selectDay.getDay() + getString(R.string.day));

        if (tripDialog == null) {
            initDialog();
        }
        if (tripDialog != null && !tripDialog.isShowing()) {
            tripDialog.show();
           // tripDialog.setTitle();
        }
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
