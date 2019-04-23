package com.example.lbstest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

public class RegisterActivity extends AppCompatActivity {

    AppCompatEditText account;
    AppCompatEditText pass;
    Button mButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = (AppCompatEditText) findViewById(R.id.ed_update_account);
        pass = (AppCompatEditText) findViewById(R.id.ed_pass);
        mButton = (Button) findViewById(R.id.btn_update);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(account.getText().toString());
                user.setPassword(pass.getText().toString());
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
}
