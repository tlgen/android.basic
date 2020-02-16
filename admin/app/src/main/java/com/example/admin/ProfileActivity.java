package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_profile_detail;
    private TextView tv_profile_username;
    private Button bt_profile_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tv_profile_detail = findViewById(R.id.tv_profile_detail);
        tv_profile_username = findViewById(R.id.tv_profile_username);
        bt_profile_logout = findViewById(R.id.bt_profile_logout);

        /*得到Intent对象*/
        Intent intent = getIntent();
        /*通过intent读取额外数据*/
        String username = intent.getStringExtra("USERNAME");
        /*显式到TextView*/
        tv_profile_username.setText("Hello    " + username + "    欢迎回来！");

        tv_profile_detail.setOnClickListener(this);
        bt_profile_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == tv_profile_detail) {
            /*关闭当前界面*/
            finish();
        }
        if(v == bt_profile_logout) {
            /*关闭当前界面*/
            finish();
        }
    }
}
