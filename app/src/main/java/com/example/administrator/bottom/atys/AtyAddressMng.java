package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.net.DownloadAddress;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.bottom.Config.APP_ID;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class AtyAddressMng extends Activity {
    //    private EditText etUserName;
//    private EditText etPassword_1;
//    private EditText etPassword_2;
//    private EditText etPhoneNumber;
//    private EditText etEmail;
//    private EditText etAddress;
//
//    private TextView tvcode;
    private Spinner area_spinner;
    private Spinner building_spinner;
    private Spinner room_spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private TextView address;

    //UI组件初始化
    private void bindView() {
//        tvcode = (TextView) findViewById(R.id.tvCode);
//        etUserName = (EditText) findViewById(R.id.etUserName);
//        etPassword_1 = (EditText) findViewById(R.id.etPassword_1);
//        etPassword_2 = (EditText) findViewById(R.id.etPassword_2);
//        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
//        etEmail = (EditText) findViewById(R.id.etEmail);
//        etAddress = (EditText) findViewById(R.id.etAddress);

        area_spinner = (Spinner) findViewById(R.id.area_spinner_mng);
        building_spinner = (Spinner) findViewById(R.id.building_spinner_mng);
        room_spinner = (Spinner) findViewById(R.id.room_spinner_mng);
    }

    //随机六位数字代号
//    public String randomCode() {
//        int i = (int) (Math.random() * 1000000);
//        if (i < 10) {
//            return "00000" + i;
//        } else if (i < 100) {
//            return "0000" + i;
//        } else if (i < 1000) {
//            return "000" + i;
//        } else if (i < 10000) {
//            return "00" + i;
//        } else if (i < 100000) {
//            return "0" + i;
//        } else {
//            return "" + i;
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_address_mng);

        bindView();
        findViewById(R.id.Address_mng_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.transition.switch_still,R.transition.switch_slide_out_right);
            }
        });

        //数据
        data_list = new ArrayList<String>();
        data_list.add("东苑");
        data_list.add("中苑");
        data_list.add("西苑");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        //加载适配器
        area_spinner.setAdapter(arr_adapter);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("晖园11栋");
        data_list.add("晖园12栋");
        data_list.add("晖园13栋");
        data_list.add("晖园14栋");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        //加载适配器
        building_spinner.setAdapter(arr_adapter);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("117");
        data_list.add("118");
        data_list.add("119");
        data_list.add("120");

        //适配器
        arr_adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        //加载适配器
        room_spinner.setAdapter(arr_adapter);

        //show current address!!!!
        address = (TextView) findViewById(R.id.address_text);
        // 获得phoneNum
        SharedPreferences sharedPreferences = getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");
        new DownloadAddress(phone, new DownloadAddress.SuccessCallback() {

            @Override
            public void onSuccess(String school, String area, String building, String room) {
                address.setText(school + area + building + room);
                setSpinner(area, building, room);
            }
        }, new DownloadAddress.FailCallback() {

            @Override
            public void onFail() {
                Toast.makeText(AtyAddressMng.this, R.string.fail_to_commit, Toast.LENGTH_LONG).show();
            }
        });


        //change address!!!!
        findViewById(R.id.btn_change_Address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AtyAddressMng.this, AtyMainFrame.class);
                i.putExtra("page","me");
                startActivity(i);
                finish();
            }
        });


//        findViewById(R.id.Register_back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

//        bindView();
//
//        //随机代号事件
//        tvcode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String s = randomCode();
//                String code = "您的代号：" + s;
//                tvcode.setText(code);
//            }
//        });
//
//        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                if (TextUtils.isEmpty(etUserName.getText())) {
//                    Toast.makeText(AtyAddress.this, R.string.username_cannot_be_empty, Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(etPassword_1.getText())) {
//                    Toast.makeText(AtyAddress.this, R.string.password_cannot_be_empty, Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (!etPassword_1.getText().toString().equals(etPassword_2.getText().toString())) {
//                    Toast.makeText(AtyAddress.this, R.string.password_cannot_be_dismatch, Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(etPhoneNumber.getText())) {
//                    Toast.makeText(AtyAddress.this, R.string.phone_num_cannot_be_empty, Toast.LENGTH_LONG).show();
//                    return;
//                }
//                final ProgressDialog pd = ProgressDialog.show(AtyAddress.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
//                new Register(etUserName.getText().toString(), etPassword_1.getText().toString(), etPhoneNumber.getText().toString(), etEmail.getText().toString(), etAddress.getText().toString(), new Register.SuccessCallback() {
//                    @Override
//                    public void onSuccess(String token) {
//                        pd.dismiss();
//                        Toast.makeText(AtyAddress.this, R.string.suc_to_regist, Toast.LENGTH_LONG).show();
//                    }
//                }, new Register.FailCallback() {
//                    @Override
//                    public void onFail() {
//                        pd.dismiss();
//                        Toast.makeText(AtyAddress.this, R.string.fail_to_regist, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
    }

    void setSpinner(String area, String building, String room) {
        SpinnerAdapter apsAdapter1 = area_spinner.getAdapter(); //得到SpinnerAdapter对象
        int k1 = apsAdapter1.getCount();
        for (int i = 0; i < k1; i++) {
            if (room.equals(apsAdapter1.getItem(i).toString())) {
//                spinner.setSelection(i,true);// 默认选中项
                area_spinner.setSelection(i);// 默认选中项
                break;
            }
        }
        SpinnerAdapter apsAdapter2 = building_spinner.getAdapter(); //得到SpinnerAdapter对象
        int k2 = apsAdapter2.getCount();
        for (int i = 0; i < k2; i++) {
            if (room.equals(apsAdapter2.getItem(i).toString())) {
//                spinner.setSelection(i,true);// 默认选中项
                building_spinner.setSelection(i);// 默认选中项
                break;
            }
        }
        SpinnerAdapter apsAdapter3 = room_spinner.getAdapter(); //得到SpinnerAdapter对象
        int k3 = apsAdapter3.getCount();
        for (int i = 0; i < k3; i++) {
            if (room.equals(apsAdapter3.getItem(i).toString())) {
//                spinner.setSelection(i,true);// 默认选中项
                room_spinner.setSelection(i);// 默认选中项
                break;
            }
        }
    }
}
