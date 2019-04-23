package com.example.lbstest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText account,nickname,phone,pwd;
    private RadioGroup gender,isLogin;
    private RadioButton man,woman,allowLogin,disAllowLogin;
    private User userByUserName;
    private String sex = "1";
    private User user;
    private String status;
    Button mButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = (EditText) findViewById(R.id.ed_update_account);
        nickname = (EditText) findViewById(R.id.nickname);
        phone = (EditText) findViewById(R.id.phone);
        pwd = (EditText) findViewById(R.id.pwd);
        gender = (RadioGroup) findViewById(R.id.gender);
        isLogin = (RadioGroup) findViewById(R.id.isLogin);

        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        allowLogin = (RadioButton) findViewById(R.id.allow);
        disAllowLogin = (RadioButton) findViewById(R.id.disAllow);
        mButton= (Button)findViewById(R.id.btn_update);
        initListener();
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(account.getText().toString());
                user.setPassword(pwd.getText().toString());
                user.setNickname(nickname.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setSex(sex);
//                DBManager.insertNewUser(user); // 0 success 1 not empty 2 error

                if (DBManager.insertNewUser(user) !=0) {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initListener() {
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.man:
                        sex = "1";
                        break;
                    case R.id.woman:
                        sex = "0";
                        break;
                }
            }
        });

    }

}
