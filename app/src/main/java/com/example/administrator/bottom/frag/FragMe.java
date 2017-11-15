package com.example.administrator.bottom.frag;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyLogin;
import com.example.administrator.bottom.atys.AtyRegister;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragMe extends Fragment {
    private String context;
    private TextView mTextView;
//    private Button close_button;

    public FragMe() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_me, container, false);
//        close_button = (Button) view.findViewById(R.id.close_btn);
//        close_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().onBackPressed();
//            }
//        });

        mTextView = (TextView) view.findViewById(R.id.func_btn);
        if (Config.loginStatus == 0){
            mTextView.setText("登录注册");
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AtyLogin.class);
                    startActivity(intent);
                }
            });
        }else{
            mTextView.setText("退出登录");
        }



            return view;
    }
}
