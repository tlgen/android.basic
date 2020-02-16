package com.example.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPasswordActivity extends Activity implements View.OnClickListener {

    private EditText tv_edit_password_username;
    private EditText et_edit_password_oldPassword;
    private EditText et_edit_password_newPassword;
    private EditText et_edit_password_newPasswordCheck;
    private Button bt_edit_password_yes;
    private Button bt_edit_password_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_password);

        tv_edit_password_username = findViewById(R.id.tv_edit_password_username);
        et_edit_password_oldPassword = findViewById(R.id.et_edit_password_oldPassword);
        et_edit_password_newPassword = findViewById(R.id.et_edit_password_newPassword);
        et_edit_password_newPasswordCheck = findViewById(R.id.et_edit_password_newPasswordCheck);
        bt_edit_password_yes = findViewById(R.id.bt_edit_password_yes);
        bt_edit_password_no = findViewById(R.id.bt_edit_password_no);

        bt_edit_password_yes.setOnClickListener(this);
        bt_edit_password_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == bt_edit_password_yes) {
            String username = tv_edit_password_username.getText().toString();
            String oldPassword = et_edit_password_oldPassword.getText().toString();
            String newPassword = et_edit_password_newPassword.getText().toString();
            String newPasswordCheck = et_edit_password_newPasswordCheck.getText().toString();
            if(assertNullString(username)) {
                Toast.makeText(this, "用户名不能为空！", 0).show();
                return;
            } else  if(assertNullString(oldPassword)) {
                Toast.makeText(this, "旧密码不能为空！", 0).show();
                return;
            } else if(assertNullString(newPassword)) {
                Toast.makeText(this, "新密码不能为空！", 0).show();
                return;
            }else if(assertNullString(newPasswordCheck)) {
                Toast.makeText(this, "确认新密码不能为空！", 0).show();
                return;
            } else if(!newPassword.equals(newPasswordCheck)) {
                Toast.makeText(this, "您输入的两次密码不一致！", 0).show();
                return;
            } else {
                Toast.makeText(this, "修改密码成功,返回登录！", 0).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
        if(v == bt_edit_password_no) {
            finish();
        }
    }

    private boolean assertNullString(String value){
        return value == null || value.equals("");
    }

}
