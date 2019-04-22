package com.example.lbstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends AppCompatActivity implements ManagerAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    ManagerAdapter mAdapter;

    private TextView addUser;

    private List<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        addUser = (TextView) findViewById(R.id.add_user);
        List<User> allUser = DBManager.getAllUser();
        for (User user1 : allUser) {
            list.add(user1);
        }

        mAdapter = new ManagerAdapter(getApplicationContext(), list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(ManagerActivity.this, DeleteAndUpdateActivity.class);
        intent.putExtra("position", position + "");
        startActivity(intent);
    }
}
