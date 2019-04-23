package com.example.lbstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lbstest.db.DBManager;
import com.example.lbstest.model.User;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity implements ManagerAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    ManagerAdapter mAdapter;

    private List<User> list = new ArrayList<>();
    private User allUser;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        username = getIntent().getStringExtra("username");
        allUser = DBManager.findUserByUserName(username);
        list.add(allUser);
        mAdapter = new ManagerAdapter(getApplicationContext(), list);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(SearchActivity.this, DeleteAndUpdateActivity.class);
        intent.putExtra("username", list.get(position).getUsername());
//        intent.putExtra("position", position + "");
        startActivity(intent);
    }


}
