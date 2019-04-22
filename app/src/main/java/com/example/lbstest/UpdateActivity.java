package com.example.lbstest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

public class UpdateActivity extends AppCompatActivity {

    AppCompatEditText account;
    AppCompatEditText pass;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadte);

        account = (AppCompatEditText) findViewById(R.id.ed_update_account);
        pass = (AppCompatEditText) findViewById(R.id.ed_pass);

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = DBManager.findUserByUserName(account.getText().toString());
                user.setPassword(pass.getText().toString());
                DBManager.update(user);
                Toast.makeText(UpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
