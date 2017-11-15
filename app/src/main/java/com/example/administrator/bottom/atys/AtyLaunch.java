package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.net.UploadToken;

import static com.example.administrator.bottom.Config.APP_ID;

public class AtyLaunch extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载启动图片
        setContentView(R.layout.aty_launch);
        Integer time = 3000;    //设置等待时间，单位为毫秒

        // 判断该用户是否已经登录
        {
            // 获得token
            SharedPreferences sharedPreferences = getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
            String token = sharedPreferences.getString(Config.KEY_TOKEN, "");

            // 用UploadToken类上传token，并处理返回值
            new UploadToken(token, new UploadToken.SuccessCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(AtyLaunch.this, R.string.login_already, Toast.LENGTH_LONG).show();
                }
            }, new UploadToken.FailCallback() {
                @Override
                public void onFail() {
                    Toast.makeText(AtyLaunch.this, R.string.login_notyet, Toast.LENGTH_LONG).show();
                }
            });
        }


        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AtyLaunch.this, AtyMainFrame.class));
                AtyLaunch.this.finish();
            }
        }, time);
    }
}
