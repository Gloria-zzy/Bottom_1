package com.example.administrator.bottom.frag;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyAddressMng;
import com.example.administrator.bottom.atys.AtyLogin;
import com.example.administrator.bottom.atys.AtyMainFrame;
import com.example.administrator.bottom.atys.AtyUnlog;

import com.example.administrator.zxinglibrary.android.CaptureActivity;
import com.example.administrator.zxinglibrary.bean.ZxingConfig;
import com.example.administrator.zxinglibrary.common.Constant;
import com.example.administrator.zxinglibrary.encode.CodeCreator;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import static com.example.administrator.bottom.Config.APP_ID;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragMe extends Fragment {
    private String context;
    private TextView mTextView, phone_num;
    private int REQUEST_CODE_SCAN = 111;
//    private Button close_button;

    public FragMe() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_me, container, false);

        //login btn
        mTextView = (TextView) view.findViewById(R.id.func_btn);
        if (Config.loginStatus == 0) {
            mTextView.setText("登录");
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AtyLogin.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                }
            });
        } else {
            mTextView.setText("退出登录");
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Config.loginStatus = 0;
                    Intent intent = new Intent(getActivity(), AtyMainFrame.class);
                    intent.putExtra("page", "me");
                    startActivity(intent);
                    Config.cacheToken(getActivity(), "");
                }
            });
        }


        //address mng
        view.findViewById(R.id.address_mng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Config.loginStatus == 0)
//                {
//                    Intent intent = new Intent(getActivity(), AtyUnlog.class);
//                    startActivity(intent);
//                    getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
//                } else
                {

                    getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                    if (Config.loginStatus == 1) {
                        Intent intent = new Intent(getActivity(), AtyAddressMng.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                    } else {
                        Intent intent = new Intent(getActivity(), AtyUnlog.class);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
                    }

                }
            }
        });

        //show phone number!!!!
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
        phone_num = (TextView) view.findViewById(R.id.textView);
        if (Config.loginStatus == 1) {
            phone_num.setText(phone);
        } else {
            phone_num.setText("未登录");
        }

        //scanner!!!!
        view.findViewById(R.id.scanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndPermission.with(getActivity())
                        .permission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).callback(new PermissionListener(){
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        Intent intent = new Intent(getActivity(), CaptureActivity.class);

                                /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                                * 也可以不传这个参数
                                * 不传的话  默认都为默认不震动  其他都为true
                                * */

                        ZxingConfig config = new ZxingConfig();
                        config.setPlayBeep(true);
                        config.setShake(true);
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);

                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }
                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        Uri packageURI = Uri.parse("package:" + getActivity().getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);

                        Toast.makeText(getActivity(), "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                    }
                }).start();

            }
        });

        return view;
    }
}
