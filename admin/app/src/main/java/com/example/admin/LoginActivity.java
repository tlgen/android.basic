package com.example.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

    private Button bt_login;
    private Button bt_login_registry;
    private Button bt_login_logout;
    private EditText et_login_username;
    private EditText et_login_password;
    private TextView et_login_edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        /*初始化视图对象*/
        bt_login = findViewById(R.id.bt_login);
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        bt_login_registry = findViewById(R.id.bt_login_registry);
        bt_login_logout = findViewById(R.id.bt_login_logout);
        et_login_edit_password = findViewById(R.id.et_login_edit_password);

        /*设置点击监听*/
        bt_login.setOnClickListener(this);
        bt_login_registry.setOnClickListener(this);
        bt_login_logout.setOnClickListener(this);
        et_login_edit_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == et_login_edit_password) {
            Intent intent = new Intent(this, EditPasswordActivity.class);
            startActivity(intent);
        }
        if( v == bt_login) {
            /*创建Intent对象(显式)*/
            Intent intent = new Intent(this, ProfileActivity.class);
            /*通过Intent携带额外数据*/
            /*从文本框中得到输入的内容*/
            String username = et_login_username.getText().toString();
            String password = et_login_password.getText().toString();

            /*得到Intent对象*/
            Intent intent1 = getIntent();
            /*通过intent读取额外数据*/
            String registryed_username = intent1.getStringExtra("REGISTRYED_USERNAME");
            String registryed_password = intent1.getStringExtra("REGISTRYED_PASSWORD");

            if(!username.equals(registryed_username)) {
                Toast.makeText(this, "用户名不是注册的用户名或未经过注册！", 1).show();
                return;
            }else if(assertNullString(password)) {
                Toast.makeText(this, "密码不能为空！", 1).show();
                return;
            } else if(assertNotNullString(password) && !registryed_password.equals(password)) {
                Toast.makeText(this, "您输入的密码不正确！", 1).show();
                return;
            } else if(!registryed_password.equals(password)) {
                Toast.makeText(this, "您输入的密码不正确！", 1).show();
                return;
            } else if(assertNullString(username)) {
                Toast.makeText(this, "用户名不能为空！", 1).show();
                return;
            } else {
                Toast.makeText(this, "登录成功！", 0).show();
                intent.putExtra("USERNAME", username);
                /*启动Activity*/
                startActivity(intent);
            }
        }
        if(v == bt_login_registry) {
            /*创建Intent对象(显式)*/
            Intent intent = new Intent(this, RegistryActivity.class);
            /*启动Activity*/
            startActivity(intent);
        }
        if(v == bt_login_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("确定要退出吗？")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           finish();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        }
    }

private boolean assertNullString(String value){
    return value == null || value.equals("");
}
    private boolean assertNotNullString(String value){
        return !assertNullString(value);
    }

    private boolean exit = false;//标识是否可以退出
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==1) {
                exit = false;
            }
        }
    };
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
            if(!exit) {
                exit = true;
                Toast.makeText(this, "再按一次就退出应用", 0).show();
                //发消息延迟2s将exit=false
                handler.sendEmptyMessageDelayed(1, 2000);
                return true;//不退出
            }
        }
        return super.onKeyUp(keyCode, event);
    }
}

