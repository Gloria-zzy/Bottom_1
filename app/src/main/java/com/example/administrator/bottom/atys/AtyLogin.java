package com.example.administrator.bottom.atys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.bottom.Config;
import com.example.administrator.bottom.R;
import com.example.administrator.bottom.net.GetCode;
import com.example.administrator.bottom.net.Login;
import com.example.administrator.bottom.tools.MD5Tool;

public class AtyLogin extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);
		
		etPhone = (EditText)findViewById(R.id.etPhoneNum);
		etCode = (EditText)findViewById(R.id.etCode);
		
		findViewById(R.id.btnGetCode).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (TextUtils.isEmpty(etPhone.getText())) {
					Toast.makeText(AtyLogin.this, R.string.phone_num_cannot_be_empty, Toast.LENGTH_LONG).show();
					return;
				}
				
				final ProgressDialog pd = ProgressDialog.show(AtyLogin.this, getResources().getString(R.string.connecting), getResources().getString(R.string.connecting_to_server)); 
				
				new GetCode(etPhone.getText().toString(), new GetCode.SuccessCallback() {
					
					@Override
					public void onSuccess() {
						pd.dismiss();
						Toast.makeText(AtyLogin.this, R.string.suc_to_get_code, Toast.LENGTH_LONG).show();
					}
				}, new GetCode.FailCallback() {
					
					@Override
					public void onFail() {
						pd.dismiss();
						Toast.makeText(AtyLogin.this, R.string.fail_to_get_code, Toast.LENGTH_LONG).show();
					}
				});
			}
		});
		
		findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(etPhone.getText())) {
					Toast.makeText(AtyLogin.this, R.string.phone_num_cannot_be_empty, Toast.LENGTH_LONG).show();
					return ;
				}
				
				if (TextUtils.isEmpty(etCode.getText())){
					Toast.makeText(AtyLogin.this, R.string.code_cannot_be_empty, Toast.LENGTH_LONG).show();
					return ;
				}

				new Login(MD5Tool.md5(etPhone.getText().toString()), etCode.getText().toString(), new Login.SuccessCallback() {

					@Override
					public void onSuccess(String token) {

						Config.cacheToken(AtyLogin.this, token);
						Config.cachePhoneNum(AtyLogin.this, etPhone.getText().toString());

						Intent i = new Intent(AtyLogin.this, AtyMainFrame.class);
						startActivity(i);
						finish();
					}
				}, new Login.FailCallback() {

					@Override
					public void onFail() {
						Toast.makeText(AtyLogin.this, R.string.fail_to_login, Toast.LENGTH_LONG).show();
					}
				});
			}
		});

		findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(AtyLogin.this,AtyRegister.class);
				startActivity(intent);
			}
		});
		
	}
	
	private EditText etPhone = null;
	private EditText etCode = null;
}
