package com.example.administrator.bottom.frag;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyAddressMng;
import com.example.administrator.bottom.atys.AtyLogin;
import com.example.administrator.bottom.atys.AtyMainFrame;

import static com.example.administrator.bottom.Config.APP_ID;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragMe extends Fragment {
    private String context;
    private TextView mTextView, phone_num;
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
                Intent intent = new Intent(getActivity(), AtyAddressMng.class);
                startActivity(intent);
            }
        });

        //show phone number!!!!
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
        phone_num = (TextView) view.findViewById(R.id.textView);
        if(Config.loginStatus == 1){
            phone_num.setText(phone);
        }else{
            phone_num.setText("未登录");
        }
        return view;
    }
}
