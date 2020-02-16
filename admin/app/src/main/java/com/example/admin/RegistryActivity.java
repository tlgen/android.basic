package com.example.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistryActivity extends Activity implements View.OnClickListener {

    private TextView et_registry_username;
    private TextView et_registry_password;
    private  TextView et_registry_password2;
    private Button bt_registry_yes;
    private Button bt_registry_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registry);

        et_registry_username = findViewById(R.id.et_registry_username);
        et_registry_password = findViewById(R.id.et_registry_password);
        et_registry_password2 = findViewById(R.id.et_registry_password2);
        bt_registry_yes = findViewById(R.id.bt_registry_yes);
        bt_registry_no = findViewById(R.id.bt_registry_no);

        bt_registry_yes.setOnClickListener(this);
        bt_registry_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == bt_registry_yes) {
            String username = et_registry_username.getText().toString();
            String password = et_registry_password.getText().toString();
            String password2 = et_registry_password2.getText().toString();
            if(assertNullString(username)) {
                Toast.makeText(this, "用户名不能为空！", 1).show();
                return;
            } else if(assertNullString(password)) {
                Toast.makeText(this, "密码不能为空！", 1).show();
                return;
            } else if(assertNullString(password2)) {
                Toast.makeText(this, "确认密码也不能为空！", 1).show();
                return;
            }else if(!password.equals(password2)) {
                Toast.makeText(this, "您输入的两次密码不一致", 1).show();
                return;
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                /*通过Intent携带额外数据*/
                /*从文本框中得到输入的内容*/
                intent.putExtra("REGISTRYED_USERNAME", username);
                intent.putExtra("REGISTRYED_PASSWORD", password);

                Toast.makeText(this, "注册成功！", 1).show();
                startActivity(intent);
            }
        }
        if(v == bt_registry_no) {
            Toast.makeText(this, "取消注册!", 1).show();
            finish();
        }
    }

    private boolean assertNullString(String value){
        return value == null || value.equals("");
    }

}
