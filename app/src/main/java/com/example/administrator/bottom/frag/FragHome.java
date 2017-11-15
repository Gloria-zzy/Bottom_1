package com.example.administrator.bottom.frag;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyFetch;
import com.example.administrator.bottom.atys.AtyMail;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragHome extends Fragment {
    private String context;
    private TextView mTextView;
    private Button get_btn,mail_btn;

    public FragHome(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home,container,false);
        get_btn=(Button)view.findViewById(R.id.get_btn);
        mail_btn=(Button)view.findViewById(R.id.mail_btn);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


        get_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivityForResult(new Intent(getActivity(),AtyFetch.class), Activity.RESULT_FIRST_USER);
            }
        });
        mail_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivityForResult(new Intent(getActivity(),AtyMail.class), Activity.RESULT_FIRST_USER);
            }
        });
    }
}
