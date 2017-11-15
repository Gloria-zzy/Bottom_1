package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bottom.R;
import com.example.administrator.bottom.net.Register;

import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class AtyRegister extends Activity {
    private EditText etUserName;
    private EditText etPassword_1;
    private EditText etPassword_2;
    private EditText etPhoneNumber;
    private EditText etEmail;
    private EditText etAddress;

    private TextView tvcode;

    //UI组件初始化
    private void bindView() {
        tvcode = (TextView) findViewById(R.id.tvCode);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword_1 = (EditText) findViewById(R.id.etPassword_1);
        etPassword_2 = (EditText) findViewById(R.id.etPassword_2);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress = (EditText) findViewById(R.id.etAddress);
    }

    //随机六位数字代号
    public String randomCode() {
        int i = (int) (Math.random() * 1000000);
        if (i < 10) {
            return "00000" + i;
        } else if (i < 100) {
            return "0000" + i;
        } else if (i < 1000) {
            return "000" + i;
        } else if (i < 10000) {
            return "00" + i;
        } else if (i < 100000) {
            return "0" + i;
        } else {
            return "" + i;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_register);

        findViewById(R.id.Register_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bindView();

        //随机代号事件
        tvcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = randomCode();
                String code = "您的代号：" + s;
                tvcode.setText(code);
            }
        });

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (TextUtils.isEmpty(etUserName.getText())) {
                    Toast.makeText(AtyRegister.this, R.string.username_cannot_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(etPassword_1.getText())) {
                    Toast.makeText(AtyRegister.this, R.string.password_cannot_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                if (etPassword_1.getText().toString().equals(etPassword_2.getText().toString())) {
                    Toast.makeText(AtyRegister.this, R.string.password_cannot_be_dismatch, Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(etPhoneNumber.getText())) {
                    Toast.makeText(AtyRegister.this, R.string.phone_num_cannot_be_empty, Toast.LENGTH_LONG).show();
                    return;
                }
                final ProgressDialog pd = ProgressDialog.show(AtyRegister.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server));
                new Register(etUserName.getText().toString(), etPassword_1.getText().toString(), etPhoneNumber.getText().toString(), etEmail.getText().toString(), etAddress.getText().toString(), new Register.SuccessCallback() {
                    @Override
                    public void onSuccess(String token) {
                        pd.dismiss();
                        Toast.makeText(AtyRegister.this, R.string.suc_to_regist, Toast.LENGTH_LONG).show();
                    }
                }, new Register.FailCallback() {
                    @Override
                    public void onFail() {
                        pd.dismiss();
                        Toast.makeText(AtyRegister.this, R.string.fail_to_regist, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
