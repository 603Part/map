package com.example.lbstest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText account;
    AppCompatEditText pass;
    private static final String TAG = "LoginActivity";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String status = getIntent().getStringExtra("status");
        if (!TextUtils.isEmpty(status) && status.equals("logout")) {
            Toast.makeText(this, "成功退出", Toast.LENGTH_SHORT).show();
        }
        account = (AppCompatEditText) findViewById(R.id.ed_account);
        pass = (AppCompatEditText) findViewById(R.id.ed_pass);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = DBManager.login(account.getText().toString(), pass.getText().toString());// 0 success 1 not empty 2 error
                if (user != null){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    if(user.getRole().equals("管理员")){
                        Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        if (user.getStatus().equals("0")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", user.getUsername());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "不允许登录，请联系管理员", Toast.LENGTH_SHORT).show();
                        }

                    }

                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码错",Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.tv_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        {
            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }

            if (permissions.size() != 0) {
                requestPermissionsForM(permissions);
            }
        }
    }

    private void requestPermissionsForM(final ArrayList<String> per) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per.toArray(new String[per.size()]), 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                DBManager.copyDb(this, "");
                DBManager.dbManager(this);
//                User login = DBManager.login("admin", "admin");
                break;
        }
    }
}
