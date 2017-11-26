package com.example.administrator.bottom.frag;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.custom.Orderview;
import com.example.administrator.bottom.net.DownloadOrders;
import com.example.administrator.bottom.net.Order;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.bottom.Config.APP_ID;

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
//        order = (Orderview) view.findViewById(R.id.order);
        ll = (LinearLayout) view.findViewById(R.id.order_ll);
//
//        order.setOrder_intro("小件快递");
//        order.setOrder_time("18:30-19:30");
//        order.setOrder_loc("中苑33栋118");
//        order.setCancelButtonListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
//            }
//        });
//        order.setContactButtonListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "点击了联系按钮", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Orderview newov = new Orderview(getActivity());
//        ll.addView(newov);
//        newov.setCancelButtonListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "点击了取消按钮", Toast.LENGTH_SHORT).show();
//            }
//        });
//        newov.setContactButtonListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "点击了联系按钮", Toast.LENGTH_SHORT).show();
//            }
//        });

        // 获得phoneNum
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
        new DownloadOrders(phone, new DownloadOrders.SuccessCallback() {

            @Override
            public void onSuccess(ArrayList<Order> orders) {
                for (Order o : orders)
                {
                    String number = o.getOrderNum();
                    String time = o.getTime();
                    String loc = o.getLocation();
                    String note = o.getNote();
                    String status = o.getStatus();
                    Orderview newov = new Orderview(getActivity());
                    ll.addView(newov);
                    newov.setOrder_intro("大件快递");
                    newov.setOrder_num(number);
                    newov.setOrder_time(time);
                    newov.setOrder_loc(loc);
                    newov.setOrder_note(note);
                    if(status.equals("0")){
                        newov.setOrder_status("已结束");
                    }else if(status.equals("1")){
                        newov.setOrder_status("正在送货");
                    }else if(status.equals("2")){
                        newov.setOrder_status("订单异常");
                    }

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
                    newov.setCancelButtonListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getActivity(), "点击了修改按钮", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }, new DownloadOrders.FailCallback() {

            @Override
            public void onFail() {
                Toast.makeText(getActivity(), R.string.fail_to_commit, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
