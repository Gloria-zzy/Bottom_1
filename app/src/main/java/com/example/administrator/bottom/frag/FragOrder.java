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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.atys.AtyLogin;
import com.example.administrator.bottom.atys.AtyMainFrame;
import com.example.administrator.bottom.custom.Orderview;
import com.example.administrator.bottom.net.DownloadOrders;
import com.example.administrator.bottom.net.Order;

import java.util.ArrayList;

import static com.example.administrator.bottom.Config.APP_ID;

/**
 * Created by Administrator on 2017/10/29.
 */

public class FragOrder extends Fragment {
    private Orderview order;
    private LinearLayout ll;
    private LinearLayout history;

    public FragOrder() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        if(Config.loginStatus == 0){
            view = inflater.inflate(R.layout.aty_unlog, container, false);
        }else{
            view = inflater.inflate(R.layout.frag_order, container, false);
        }
        ll = (LinearLayout) view.findViewById(R.id.order_ll);
        history = (LinearLayout) view.findViewById(R.id.history_order);


        view.findViewById(R.id.to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AtyLogin.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.transition.switch_slide_in_right, R.transition.switch_still);
            }
        });

        view.findViewById(R.id.back_to_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AtyMainFrame.class);
                i.putExtra("page","home");
                startActivity(i);
            }
        });
        if (Config.loginStatus == 1) {
            // 获得phoneNum
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
            String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
            new DownloadOrders(phone, new DownloadOrders.SuccessCallback() {

                @Override
                public void onSuccess(ArrayList<Order> orders) {
                    for (Order o : orders) {
                        String number = o.getOrderNum();
                        String time = o.getTime();
                        String loc = o.getLocation();
                        String note = o.getNote();
                        String status = o.getStatus();
                        Orderview newov = new Orderview(getActivity());

                        newov.setOrder_intro("大件快递");
                        newov.setOrder_num(number);
                        newov.setOrder_time(time);
                        newov.setOrder_loc(loc);
                        if (note.equals("none")) {
                            note = "无";
                        }
                        newov.setOrder_note(note);
                        if (status.equals("0")) {
                            newov.setOrder_status("已结束");
                            history.addView(newov);
                        } else if (status.equals("1")) {
                            newov.setOrder_status("正在送货");
                            ll.addView(newov);
                        } else if (status.equals("2")) {
                            newov.setOrder_status("订单异常");
                            ll.addView(newov);
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
        }
        return view;
    }

}
