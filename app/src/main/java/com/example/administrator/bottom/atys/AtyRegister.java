package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.bottom.R;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class AtyRegister extends Activity {

    private TextView tvcode;

    //UI组件初始化
    private void bindView() {
        tvcode = (TextView) findViewById(R.id.tvCode);
    }

    //随机六位数字代号
    public String randomCode() {
        int i = (int) (Math.random() * 1000000);
        if (i < 10) {
            return "00000" + i;
        } else if (i < 100) {
            return "0000" + i;
        } else if (i < 1000) {
            return "000" + i;
        } else if (i < 10000) {
            return "00" + i;
        } else if (i < 100000) {
            return "0" + i;
        } else {
            return "" + i;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_register);

        findViewById(R.id.Register_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bindView();

        //随机代号事件
        tvcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = randomCode();
                String code = "您的代号：" + s;
                tvcode.setText(code);
            }
        });
    }
}
