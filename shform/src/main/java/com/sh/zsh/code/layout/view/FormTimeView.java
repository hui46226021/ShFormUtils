package com.sh.zsh.code.layout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.sh.shform.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhush on 2017/3/14.
 * E-mail 405086805@qq.com
 * PS 时间输入控件
 */
public class FormTimeView extends TextView{

    TimePickerView pvTime;
    int isTime;
    Date date;
    String ALL_FORMAT ="yyyy-MM-dd HH:mm";
    String YEAR_MONTH_DAY_FORMAT ="yyyy-MM-dd";
    String HOURS_MINS_FORMAT ="HH:mm";
    String MONTH_DAY_HOUR_MIN_FORMAT ="MM-dd HH:mm";
    String YEAR_MONTH_FORMAT ="yyyy-MM";

    public FormTimeView(Context context) {
        super(context);
    }

    public FormTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.less_from_view, 0, 0);
        isTime = ta.getInt(R.styleable.less_from_view_less_time_is_time,1);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showPvTime();
            }
        });

        initPvTime();
    }


    public void initPvTime(){
        //时间选择器
        switch (isTime){
            case 1:
                pvTime = new TimePickerView(getContext(), TimePickerView.Type.ALL);
                break;
            case 2:
                pvTime = new TimePickerView(getContext(), TimePickerView.Type.YEAR_MONTH_DAY);
                break;
            case 3:
                pvTime = new TimePickerView(getContext(), TimePickerView.Type.HOURS_MINS);
                break;
            case 4:
                pvTime = new TimePickerView(getContext(), TimePickerView.Type.MONTH_DAY_HOUR_MIN);
                break;
            case 5:
                pvTime = new TimePickerView(getContext(), TimePickerView.Type.YEAR_MONTH);
                break;
        }

        //控制时间范围
//        Calendar calendar = Calendar.getInstance();
//        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(true);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {

                FormTimeView.this.date = date;
                //时间选择器
                FormTimeView.super.setText(getTimeStringByDate(date));
            }
        });
    }

    /**
     * 显示时间控件
     */
    public void showPvTime(){

        pvTime.show();
    }


    private String  getTimeStringByDate(Date date){


        switch (isTime){
            case 1:
                return new SimpleDateFormat(ALL_FORMAT).format(date);
            case 2:
                return new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT).format(date);
            case 3:
                return new SimpleDateFormat(HOURS_MINS_FORMAT).format(date);
            case 4:
                return new SimpleDateFormat(MONTH_DAY_HOUR_MIN_FORMAT).format(date);
            case 5:
                return new SimpleDateFormat(YEAR_MONTH_FORMAT).format(date);

        }



        return new SimpleDateFormat(ALL_FORMAT).format(date);
    }


    public String  getTimeString(){
        return getText()+"";
    }

    public Date getDate(){
        return  date;
    }

    public void setDate(Date date){
        pvTime.setTime(date);
        FormTimeView.super.setText(getTimeStringByDate(date));
    }
}
