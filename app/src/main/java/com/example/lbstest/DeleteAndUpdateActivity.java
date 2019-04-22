package com.example.lbstest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

public class DeleteAndUpdateActivity extends AppCompatActivity {

    AppCompatEditText account;
    AppCompatEditText deleteAccount;
    AppCompatEditText pass;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        account = (AppCompatEditText) findViewById(R.id.ed_update_account);
        deleteAccount = (AppCompatEditText) findViewById(R.id.ed_delete_account);
        pass = (AppCompatEditText) findViewById(R.id.ed_pass);

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                User user = DBManager.findUserByUserName(account.getText().toString());
                user.setPassword(pass.getText().toString());
                DBManager.update(user);
                Toast.makeText(DeleteAndUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DBManager.deleteUserByUserName(deleteAccount.getText().toString());
                User user = DBManager.findUserByUserName(deleteAccount.getText().toString());
                if (user == null)
                    Toast.makeText(DeleteAndUpdateActivity.this, "删除用户成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
