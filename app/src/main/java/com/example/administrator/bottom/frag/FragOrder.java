package com.example.administrator.bottom.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.bottom.R;
import com.example.administrator.bottom.custom.Orderview;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragOrder extends Fragment {
    private Orderview order;
    private LinearLayout ll;

    public FragOrder() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_order, container, false);
        order = (Orderview) view.findViewById(R.id.order);
        ll=(LinearLayout)view.findViewById(R.id.order_ll);

        order.setOrder_intro("小件快递");
        order.setOrder_time("18:30-19:30");
        order.setOrder_loc("中苑33栋118");
        order.setCancelButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
        order.setContactButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了联系按钮", Toast.LENGTH_SHORT).show();
            }
        });

        Orderview newov=new Orderview(getActivity());
        ll.addView(newov);
        newov.setCancelButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
        newov.setContactButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "点击了联系按钮", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
