package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.net.Login;
import com.example.administrator.bottom.net.Register;
import com.example.administrator.bottom.net.UploadAddress;
import com.example.administrator.bottom.tools.MD5Tool;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.bottom.Config.APP_ID;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class AtyAddress extends Activity {
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
    private String school = "南京信息工程大学";
    private String area = "";
    private String building = "";
    private String room = "";

    //UI组件初始化
    private void bindView() {
//        tvcode = (TextView) findViewById(R.id.tvCode);
//        etUserName = (EditText) findViewById(R.id.etUserName);
//        etPassword_1 = (EditText) findViewById(R.id.etPassword_1);
//        etPassword_2 = (EditText) findViewById(R.id.etPassword_2);
//        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
//        etEmail = (EditText) findViewById(R.id.etEmail);
//        etAddress = (EditText) findViewById(R.id.etAddress);

        area_spinner = (Spinner) findViewById(R.id.area_spinner);
        building_spinner = (Spinner) findViewById(R.id.building_spinner);
        room_spinner = (Spinner) findViewById(R.id.room_spinner);
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
        setContentView(R.layout.aty_address);

        bindView();
        findViewById(R.id.Address_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                area = (String) area_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        building_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                building = (String) building_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        room_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                room = (String) room_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.btnAddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 获得phoneNum
                SharedPreferences sharedPreferences = getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
                String phone = sharedPreferences.getString(Config.KEY_PHONE_NUM, "");

                new UploadAddress(phone, school,area,building,room, new UploadAddress.SuccessCallback() {

                    @Override
                    public void onSuccess() {

                        Intent i = new Intent(AtyAddress.this, AtyMainFrame.class);
                        startActivity(i);
                        finish();

                    }
                }, new UploadAddress.FailCallback() {

                    @Override
                    public void onFail() {
                        Toast.makeText(AtyAddress.this, R.string.fail_to_commit, Toast.LENGTH_LONG).show();
                    }
                });
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
}
