package com.example.lbstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

import java.util.ArrayList;
import java.util.List;

public class ManagerActivity extends AppCompatActivity implements ManagerAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    ManagerAdapter mAdapter;
    private ImageView search;
    private EditText searchEd;
    private TextView addUser;

    private List<User> list = new ArrayList<>();
    private List<User> allUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        addUser = (TextView) findViewById(R.id.add_user);
        search = (ImageView)findViewById(R.id.search);
        searchEd = (EditText) findViewById(R.id.search_username);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerActivity.this, SearchActivity.class);
                intent.putExtra("username", searchEd.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        allUser = DBManager.getAllUser();

        mAdapter = new ManagerAdapter(getApplicationContext(), allUser);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
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
        intent.putExtra("username", allUser.get(position).getUsername());
//        intent.putExtra("position", position + "");
        startActivity(intent);
    }


}
