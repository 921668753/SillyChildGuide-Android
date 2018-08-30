package com.yinglan.scg.homepage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.luck.picture.lib.rxbus2.Subscribe;
import com.luck.picture.lib.rxbus2.ThreadMode;
import com.yinglan.scg.R;
import com.yinglan.scg.utils.custompicker.adapter.MonthTimeAdapter;
import com.yinglan.scg.utils.custompicker.bean.DayTimeEntity;
import com.yinglan.scg.utils.custompicker.bean.MonthTimeEntity;
import com.yinglan.scg.utils.custompicker.bean.UpdataCalendar;

import java.util.ArrayList;
import java.util.Calendar;

import io.rong.eventbus.EventBus;

/**
 * 选择日期
 */
public class SelectDateActivity extends BaseActivity {

    @BindView(id = R.id.tv_chooseTimeNeedServe)
    private TextView tv_chooseTimeNeedServe;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    private MonthTimeAdapter adapter;
    private ArrayList<MonthTimeEntity> datas;
    public static DayTimeEntity startDay;
    public static DayTimeEntity stopDay;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_selectdate);
    }

    @Override
    public void initData() {
        super.initData();
        startDay = (DayTimeEntity) getIntent().getSerializableExtra("startDayBean");
        stopDay = (DayTimeEntity) getIntent().getSerializableExtra("stopDayBean");
        if (startDay == null || stopDay == null) {
            startDay = new DayTimeEntity(0, 0, 0, 0);
            stopDay = new DayTimeEntity(-1, -1, -1, -1);
        }
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

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.selectDate), true, R.id.titlebar);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this,   // 上下文
                        LinearLayout.VERTICAL,  //垂直布局,
                        false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(startDay.getMonthPosition());
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UpdataCalendar event) {
        adapter.notifyDataSetChanged();
        tv_chooseTimeNeedServe.setText(getString(R.string.startTime) + startDay.getYear() + getString(R.string.year) + startDay.getMonth() + getString(R.string.month) +
                startDay.getDay() + getString(R.string.day));
        if (stopDay.getDay() != -1) {
            tv_chooseTimeNeedServe.setText(tv_chooseTimeNeedServe.getText().toString() + "   " +
                    getString(R.string.endTime) + stopDay.getYear() + getString(R.string.year) + stopDay.getMonth() + getString(R.string.month) + stopDay.getDay() + getString(R.string.day));
            Intent intent = new Intent();
            // 获取内容
            intent.putExtra("startDay", startDay.getYear() + getString(R.string.year) + startDay.getMonth() + getString(R.string.month) + startDay.getDay() + getString(R.string.day));
            intent.putExtra("stopDay", stopDay.getYear() + getString(R.string.year) + stopDay.getMonth() + getString(R.string.month) + stopDay.getDay() + getString(R.string.day));
            intent.putExtra("startDayBean", startDay);
            intent.putExtra("stopDayBean", stopDay);
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
