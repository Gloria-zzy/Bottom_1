package com.example.administrator.bottom.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.bottom.R;

/**
 * Created by Administrator on 2017\11\25 0025.
 */

public class Orderview extends RelativeLayout {

    private TextView order_intro;
    private TextView time;
    private TextView order_time;
    private TextView order_loc;
    private Button order_cancel;
    private Button order_contact;

    public Orderview(Context context){
        super(context);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_record, this);

        // 获取控件
        order_intro = (TextView) findViewById(R.id.introduction);
        time = (TextView) findViewById(R.id.time);
        order_time = (TextView) findViewById(R.id.order_time);
        order_loc = (TextView) findViewById(R.id.order_loc);
        order_cancel = (Button) findViewById(R.id.order_cancel);
        order_contact = (Button) findViewById(R.id.order_contact);
    }

    public Orderview(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.mod_record, this);

        // 获取控件
        order_intro = (TextView) findViewById(R.id.introduction);
        time = (TextView) findViewById(R.id.time);
        order_time = (TextView) findViewById(R.id.order_time);
        order_loc = (TextView) findViewById(R.id.order_loc);
        order_cancel = (Button) findViewById(R.id.order_cancel);
        order_contact = (Button) findViewById(R.id.order_contact);
    }

    public void setOrder_intro(String intro) {
        this.order_intro.setText(intro);
    }

    public void setTime(String time) {
        this.time.setText(time);
    }

    public void setOrder_time(String order_time) {
        this.order_time.setText(order_time);
    }

    public void setOrder_loc(String order_loc) {
        this.order_loc.setText(order_loc);
    }

    public void setCancelButtonListener(OnClickListener listener) {
        order_cancel.setOnClickListener(listener);
    }

    public void setContactButtonListener(OnClickListener listener) {
        order_contact.setOnClickListener(listener);
    }
}
