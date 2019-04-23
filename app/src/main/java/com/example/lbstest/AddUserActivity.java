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

public class AddUserActivity extends AppCompatActivity {

    AppCompatEditText account;
    AppCompatEditText pass;
    Button mButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        account = (AppCompatEditText) findViewById(R.id.ed_update_account);
        pass = (AppCompatEditText) findViewById(R.id.ed_pass);
        mButton = (Button) findViewById(R.id.btn_update);

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(account.getText().toString());
                user.setPassword(pass.getText().toString());
                if (DBManager.insertNewUser(user) !=0) {
                    Toast.makeText(AddUserActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddUserActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
