package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.bottom.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/11/25 0025.
 */

public class AtyLocateSchool extends Activity {

    private ListView listSchool;  // ListView控件
    private TextView txtSchool;

    private void bindView() {
        txtSchool = (TextView) findViewById(R.id.aty_locate_address_school);
    }

    private void addItem() {
    }

    private void deleteItem() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_locate_address);

        bindView();
    }
}
