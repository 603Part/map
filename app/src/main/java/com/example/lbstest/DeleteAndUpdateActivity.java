package com.example.lbstest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

public class DeleteAndUpdateActivity extends AppCompatActivity {

    EditText account,nickname,phone;
    private RadioGroup gender,isLogin;
    private RadioButton man,woman,allowLogin,disAllowLogin;
    private User userByUserName;
    private String sex;
    private User user;
    private String status;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        String username = getIntent().getStringExtra("username");
        user = DBManager.findUserByUserName(username);
        account = (EditText) findViewById(R.id.ed_update_account);
        nickname = (EditText) findViewById(R.id.nickname);
        phone = (EditText) findViewById(R.id.phone);

        gender = (RadioGroup) findViewById(R.id.gender);
        isLogin = (RadioGroup) findViewById(R.id.isLogin);

        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        allowLogin = (RadioButton) findViewById(R.id.allow);
        disAllowLogin = (RadioButton) findViewById(R.id.disAllow);
        initData(user);
        initListener();
    }

    private void initData(User userByUserName) {
        account.setText(userByUserName.getUsername());
        nickname.setText(userByUserName.getNickname());
        phone.setText(userByUserName.getPhone());

        sex = userByUserName.getSex();
        status = userByUserName.getStatus();
        if ("1".equals(sex)) {
            man.setChecked(true);
            woman.setChecked(false);
        } else if ("0".equals(sex)){
            woman.setChecked(true);
            man.setChecked(false);
        }

        if ("1".equals(status)) {
            allowLogin.setChecked(false);
            disAllowLogin.setChecked(true);
        } else if ("0".equals(status)) {
            allowLogin.setChecked(true);
            disAllowLogin.setChecked(false);
        }

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

        isLogin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.allow:
                        status = "0";
                        break;
                    case R.id.disAllow:
                        status = "1";
                        break;
                }
            }
        });
    }


    public void save(View view) {
        user.setUsername(account.getText().toString());
        user.setNickname(nickname.getText().toString());
        user.setSex(sex);
        user.setPhone(phone.getText().toString());
        user.setStatus(status);
        DBManager.update(user);
        setResult(Activity.RESULT_OK);
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
