package com.yinglan.scg.utils.custompicker.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yinglan.scg.R;
import com.yinglan.scg.service.TravelCalendarActivity;
import com.yinglan.scg.utils.custompicker.bean.DayTimeEntity;
import com.yinglan.scg.utils.custompicker.bean.UpdataCalendar;
import com.yinglan.scg.utils.custompicker.holder.DayTimeViewHolder;

import java.util.ArrayList;

import io.rong.eventbus.EventBus;

/**
 * Created by xqx on 2017/1/17.
 */
public class DayTimeAdapter extends RecyclerView.Adapter<DayTimeViewHolder> {

    private ArrayList<DayTimeEntity> days;
    private Context context;

    public DayTimeAdapter(ArrayList<DayTimeEntity> days, Context context) {
        this.days = days;
        this.context = context;

    }

    @Override
    public DayTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DayTimeViewHolder ret = null;
        // 不需要检查是否复用，因为只要进入此方法，必然没有复用
        // 因为RecyclerView 通过Holder检查复用
        View v = LayoutInflater.from(context).inflate(R.layout.item_recycler_selectday, parent, false);
        ret = new DayTimeViewHolder(v);
        return ret;
    }

    @Override
    public void onBindViewHolder(final DayTimeViewHolder holder, final int position) {
        final DayTimeEntity dayTimeEntity = days.get(position);
        //显示日期
        if (dayTimeEntity.getDay() != 0) {
            if (dayTimeEntity.getStatus() == 100) {
                holder.select_txt_day.setText(dayTimeEntity.getDay() + "");
                holder.select_ly_day.setEnabled(false);
                holder.select_txt_day.setTextColor(Color.parseColor("#FFFFFF"));
            } else if (dayTimeEntity.getStatus() == 101) {
                holder.select_txt_day.setText(context.getString(R.string.today));
                holder.select_ly_day.setEnabled(true);
            } else {
                holder.select_txt_day.setText(dayTimeEntity.getDay() + "");
                holder.select_ly_day.setEnabled(true);
            }
        } else {
            holder.select_ly_day.setEnabled(false);
        }
        holder.select_ly_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(dayTimeEntity.getStatus() == 102 || dayTimeEntity.getStatus() == 103 || dayTimeEntity.getStatus() == 104 || dayTimeEntity.getStatus() == 105)) {
                    return;
                }
                TravelCalendarActivity.selectDay.setDay(dayTimeEntity.getDay());           // 该item 天数的 年月日等信息  赋给  开始日期
                TravelCalendarActivity.selectDay.setMonth(dayTimeEntity.getMonth());
                TravelCalendarActivity.selectDay.setYear(dayTimeEntity.getYear());
                TravelCalendarActivity.selectDay.setMonthPosition(dayTimeEntity.getMonthPosition());
                TravelCalendarActivity.selectDay.setDayPosition(position);
                TravelCalendarActivity.selectDay.setStatus(dayTimeEntity.getStatus());
                TravelCalendarActivity.selectDay.setTime(dayTimeEntity.getTime());

//                if (TravelCalendarActivity.startDay.getYear() == 0) {          // 第一次点击开始的位置，因为开始默认参数是 0,0,0,0
//                    TravelCalendarActivity.startDay.setDay(dayTimeEntity.getDay());           // 该item 天数的 年月日等信息  赋给  开始日期
//                    TravelCalendarActivity.startDay.setMonth(dayTimeEntity.getMonth());
//                    TravelCalendarActivity.startDay.setYear(dayTimeEntity.getYear());
//                    TravelCalendarActivity.startDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                    TravelCalendarActivity.startDay.setDayPosition(position);
//                } else if (TravelCalendarActivity.startDay.getYear() > 0 && TravelCalendarActivity.stopDay.getYear() == -1) {      //已经点击了开始 ，点击结束位置，（默认结束位置-1,-1,-1,-1 说明还没有点击结束位置）
//                    if (dayTimeEntity.getYear() > TravelCalendarActivity.startDay.getYear()) {
//                        //如果选中的年份大于开始的年份，说明结束日期肯定大于开始日期 ，合法的 ，将该item的天数的 信息  赋给 结束日期
//                        TravelCalendarActivity.stopDay.setDay(dayTimeEntity.getDay());
//                        TravelCalendarActivity.stopDay.setMonth(dayTimeEntity.getMonth());
//                        TravelCalendarActivity.stopDay.setYear(dayTimeEntity.getYear());
//                        TravelCalendarActivity.stopDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                        TravelCalendarActivity.stopDay.setDayPosition(position);
//                    } else if (dayTimeEntity.getYear() == TravelCalendarActivity.startDay.getYear()) {
//                        //如果选中的年份 等于 选中的年份
//                        if (dayTimeEntity.getMonth() > TravelCalendarActivity.startDay.getMonth()) {
//                            //如果改item的天数的月份大于开始日期的月份，说明结束日期肯定大于开始日期 ，合法的 ，将该item的天数的 信息  赋给 结束日期
//                            TravelCalendarActivity.stopDay.setDay(dayTimeEntity.getDay());
//                            TravelCalendarActivity.stopDay.setMonth(dayTimeEntity.getMonth());
//                            TravelCalendarActivity.stopDay.setYear(dayTimeEntity.getYear());
//                            TravelCalendarActivity.stopDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                            TravelCalendarActivity.stopDay.setDayPosition(position);
//                        } else if (dayTimeEntity.getMonth() == TravelCalendarActivity.startDay.getMonth()) {
//                            //年份月份 都相等
//                            if (dayTimeEntity.getDay() >= TravelCalendarActivity.startDay.getDay()) {
//                                //判断天数 ，如果 该item的天数的 日子大于等于 开始日期的 日子 ，说明结束日期合法的 ，将该item的天数的 信息  赋给 结束日期
//                                TravelCalendarActivity.stopDay.setDay(dayTimeEntity.getDay());
//                                TravelCalendarActivity.stopDay.setMonth(dayTimeEntity.getMonth());
//                                TravelCalendarActivity.stopDay.setYear(dayTimeEntity.getYear());
//                                TravelCalendarActivity.stopDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                                TravelCalendarActivity.stopDay.setDayPosition(position);
//                            } else {
//                                //天数小与初始  从新选择开始  ，结束日期重置，开始日期为当前的位置的天数的信息
//                                TravelCalendarActivity.startDay.setDay(dayTimeEntity.getDay());
//                                TravelCalendarActivity.startDay.setMonth(dayTimeEntity.getMonth());
//                                TravelCalendarActivity.startDay.setYear(dayTimeEntity.getYear());
//                                TravelCalendarActivity.startDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                                TravelCalendarActivity.startDay.setDayPosition(position);
//                                TravelCalendarActivity.stopDay.setDay(-1);
//                                TravelCalendarActivity.stopDay.setMonth(-1);
//                                TravelCalendarActivity.stopDay.setYear(-1);
//                                TravelCalendarActivity.stopDay.setMonthPosition(-1);
//                                TravelCalendarActivity.stopDay.setDayPosition(-1);
//                            }
//                        } else {
//                            //选中的月份 比开始日期的月份还小，说明 结束位置不合法，结束日期重置，开始日期为当前的位置的天数的信息
//                            TravelCalendarActivity.startDay.setDay(dayTimeEntity.getDay());
//                            TravelCalendarActivity.startDay.setMonth(dayTimeEntity.getMonth());
//                            TravelCalendarActivity.startDay.setYear(dayTimeEntity.getYear());
//                            TravelCalendarActivity.startDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                            TravelCalendarActivity.startDay.setDayPosition(position);
//                            TravelCalendarActivity.stopDay.setDay(-1);
//                            TravelCalendarActivity.stopDay.setMonth(-1);
//                            TravelCalendarActivity.stopDay.setYear(-1);
//                            TravelCalendarActivity.stopDay.setMonthPosition(-1);
//                            TravelCalendarActivity.stopDay.setDayPosition(-1);
//                        }
//
//                    } else {
//                        //选中的年份 比开始日期的年份还小，说明 结束位置不合法，结束日期重置，开始日期为当前的位置的天数的信息
//                        TravelCalendarActivity.startDay.setDay(dayTimeEntity.getDay());
//                        TravelCalendarActivity.startDay.setMonth(dayTimeEntity.getMonth());
//                        TravelCalendarActivity.startDay.setYear(dayTimeEntity.getYear());
//                        TravelCalendarActivity.startDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                        TravelCalendarActivity.startDay.setDayPosition(position);
//                        TravelCalendarActivity.stopDay.setDay(-1);
//                        TravelCalendarActivity.stopDay.setMonth(-1);
//                        TravelCalendarActivity.stopDay.setYear(-1);
//                        TravelCalendarActivity.stopDay.setMonthPosition(-1);
//                        TravelCalendarActivity.stopDay.setDayPosition(-1);
//                    }
//                } else if (TravelCalendarActivity.startDay.getYear() > 0 && TravelCalendarActivity.startDay.getYear() > 1) {      //已经点击开始和结束   第三次点击 ，重新点击开始
//                    TravelCalendarActivity.startDay.setDay(dayTimeEntity.getDay());
//                    TravelCalendarActivity.startDay.setMonth(dayTimeEntity.getMonth());
//                    TravelCalendarActivity.startDay.setYear(dayTimeEntity.getYear());
//                    TravelCalendarActivity.startDay.setMonthPosition(dayTimeEntity.getMonthPosition());
//                    TravelCalendarActivity.startDay.setDayPosition(position);
//                    TravelCalendarActivity.stopDay.setDay(-1);
//                    TravelCalendarActivity.stopDay.setMonth(-1);
//                    TravelCalendarActivity.stopDay.setYear(-1);
//                    TravelCalendarActivity.stopDay.setMonthPosition(-1);
//                    TravelCalendarActivity.stopDay.setDayPosition(-1);
//                }
                EventBus.getDefault().post(new UpdataCalendar()); // 发消息刷新适配器，目的为了显示日历上各个日期的背景颜色
            }
        });


        for (int i = 0; i < TravelCalendarActivity.dayDatas.size(); i++) {
            if (TravelCalendarActivity.dayDatas.get(i).getYear() == dayTimeEntity.getYear() &&
                    TravelCalendarActivity.dayDatas.get(i).getMonth() == dayTimeEntity.getMonth()
                    && TravelCalendarActivity.dayDatas.get(i).getDay() == dayTimeEntity.getDay()) {
                switch (TravelCalendarActivity.dayDatas.get(i).getStatus()) {
                    case 102:  //开始和结束同一天
                        dayTimeEntity.setStatus(102);
                        dayTimeEntity.setTime(TravelCalendarActivity.dayDatas.get(i).getTime());
                        holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_startstop);
                        break;
                    case 103:  //开始一天
                        dayTimeEntity.setStatus(103);
                        dayTimeEntity.setTime(TravelCalendarActivity.dayDatas.get(i).getTime());
                        holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_start);
                        break;
                    case 104:  //中间时间
                        dayTimeEntity.setStatus(104);
                        dayTimeEntity.setTime(TravelCalendarActivity.dayDatas.get(i).getTime());
                        holder.select_ly_day.setBackgroundResource(R.color.greenColors);
                        break;
                    case 105:  //结束一天
                        dayTimeEntity.setStatus(105);
                        dayTimeEntity.setTime(TravelCalendarActivity.dayDatas.get(i).getTime());
                        holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_stop);
                        break;
                    default:
                        holder.select_ly_day.setBackgroundResource(R.color.whiteColors);
                        break;
                }
                break;
            }
//            if (TravelCalendarActivity.dayDatas.get(i).getYear() == dayTimeEntity.getYear() &&
//                    TravelCalendarActivity.dayDatas.get(i).getMonth() == dayTimeEntity.getMonth()
//                    && TravelCalendarActivity.dayDatas.get(i).getDay() == dayTimeEntity.getDay()) {
//                dayTimeEntity.setStatus(102);
//                //开始和结束同一天
//                holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_startstop);
//                break;
//            }
//            long time = DataUtil.getStringToDate(TravelCalendarActivity.dayDatas.get(i).getYear() + "-" + TravelCalendarActivity.dayDatas.get(i).getMonth() + "-" + TravelCalendarActivity.dayDatas.get(i).getDay() + " 00:00", "yyyy-MM-dd HH:mm");
//            long time1 = 0;
//            if (i + 1 < TravelCalendarActivity.dayDatas.size()) {
//                time1 = DataUtil.getStringToDate(TravelCalendarActivity.dayDatas.get(i + 1).getYear() + "-" + TravelCalendarActivity.dayDatas.get(i + 1).getMonth() + "-" + TravelCalendarActivity.dayDatas.get(i + 1).getDay() + " 00:00", "yyyy-MM-dd HH:mm");
//            }
//            if (TravelCalendarActivity.dayDatas.get(i).getYear() == dayTimeEntity.getYear() &&
//                    TravelCalendarActivity.dayDatas.get(i).getMonth() == dayTimeEntity.getMonth()
//                    && TravelCalendarActivity.dayDatas.get(i).getDay() == dayTimeEntity.getDay() && time1 - time > 24 * 3600 * 1000) {
//                dayTimeEntity.setStatus(102);
//                //开始和结束同一天
//                holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_startstop);
//                break;
//            }
//            if (TravelCalendarActivity.dayDatas.get(i).getYear() == dayTimeEntity.getYear() &&
//                    TravelCalendarActivity.dayDatas.get(i).getMonth() == dayTimeEntity.getMonth()
//                    && TravelCalendarActivity.dayDatas.get(i).getDay() == dayTimeEntity.getDay() && i == TravelCalendarActivity.dayDatas.size() - 1) {
//                dayTimeEntity.setStatus(102);
//                //开始和结束同一天
//                holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_startstop);
//                break;
//            }

        }


//        if (TravelCalendarActivity.startDay.getYear() == dayTimeEntity.getYear() && TravelCalendarActivity.startDay.getMonth() == dayTimeEntity.getMonth() && TravelCalendarActivity.startDay.getDay() == dayTimeEntity.getDay()
//                && TravelCalendarActivity.stopDay.getYear() == dayTimeEntity.getYear() && TravelCalendarActivity.stopDay.getMonth() == dayTimeEntity.getMonth() && TravelCalendarActivity.stopDay.getDay() == dayTimeEntity.getDay()) {
//            //开始和结束同一天
//            holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_startstop);
//
//        } else if (TravelCalendarActivity.startDay.getYear() == dayTimeEntity.getYear() && TravelCalendarActivity.startDay.getMonth() == dayTimeEntity.getMonth() && TravelCalendarActivity.startDay.getDay() == dayTimeEntity.getDay()) {
//            //该item是 开始日期
//            holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_start);
//        } else if (TravelCalendarActivity.stopDay.getYear() == dayTimeEntity.getYear() && TravelCalendarActivity.stopDay.getMonth() == dayTimeEntity.getMonth() && TravelCalendarActivity.stopDay.getDay() == dayTimeEntity.getDay()) {
//            //该item是 结束日期
//            holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_stop);
//        } else if (dayTimeEntity.getMonthPosition() >= TravelCalendarActivity.startDay.getMonthPosition() && dayTimeEntity.getMonthPosition() <= TravelCalendarActivity.stopDay.getMonthPosition()) {
//            //处于开始和结束之间的点
//            if (dayTimeEntity.getMonthPosition() == TravelCalendarActivity.startDay.getMonthPosition() && dayTimeEntity.getMonthPosition() == TravelCalendarActivity.stopDay.getMonthPosition()) {
//                //开始和结束是一个月份
//                if (dayTimeEntity.getDay() > TravelCalendarActivity.startDay.getDay() && dayTimeEntity.getDay() < TravelCalendarActivity.stopDay.getDay()) {
//                    holder.select_ly_day.setBackgroundResource(R.color.greenColors);
//                } else {
//                    if (dayTimeEntity.getStatus() == 100) {
//                        holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_gray);
//                    } else {
//                        holder.select_ly_day.setBackgroundResource(R.color.white);
//                    }
//                }
//            } else if (TravelCalendarActivity.startDay.getMonthPosition() != TravelCalendarActivity.stopDay.getMonthPosition()) {
//                // 日期和 开始 不是一个月份
//                if (dayTimeEntity.getMonthPosition() == TravelCalendarActivity.startDay.getMonthPosition() && dayTimeEntity.getDay() > TravelCalendarActivity.startDay.getDay()) {
//                    //和初始相同月  天数往后
//                    holder.select_ly_day.setBackgroundResource(R.color.greenColors);
//                } else if (dayTimeEntity.getMonthPosition() == TravelCalendarActivity.stopDay.getMonthPosition() && dayTimeEntity.getDay() < TravelCalendarActivity.stopDay.getDay()) {
//                    //和结束相同月   天数往前
//                    holder.select_ly_day.setBackgroundResource(R.color.greenColors);
//                } else if (dayTimeEntity.getMonthPosition() != TravelCalendarActivity.startDay.getMonthPosition() && dayTimeEntity.getMonthPosition() != TravelCalendarActivity.stopDay.getMonthPosition()) {
//                    //和 开始结束都不是同一个月
//                    holder.select_ly_day.setBackgroundResource(R.color.greenColors);
//                } else {
//                    if (dayTimeEntity.getStatus() == 100) {
//                        holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_gray);
//                    } else {
//                        holder.select_ly_day.setBackgroundResource(R.color.white);
//                    }
//                }
//            }

//        } else {
        if (dayTimeEntity.getStatus() == 100) {
            holder.select_ly_day.setBackgroundResource(R.drawable.bg_time_gray);
        } else if (dayTimeEntity.getStatus() != 102 || dayTimeEntity.getStatus() != 103 || dayTimeEntity.getStatus() != 104 || dayTimeEntity.getStatus() != 105) {
            //   holder.select_ly_day.setBackgroundResource(R.color.whiteColors);
        }
        //   }

    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (days != null) {
            ret = days.size();
        }
        return ret;
    }
}
