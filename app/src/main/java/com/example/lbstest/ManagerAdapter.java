package com.example.lbstest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lbstest.model.User;

import java.util.List;

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {

    private Context    context;
    private List<User> data;

    public ManagerAdapter(Context context, List<User> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_manager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.username.setText(String.format(context.getResources().getString(R.string.username),data.get(position).getUsername()));
        holder.nickname.setText(String.format(context.getResources().getString(R.string.nickname),data.get(position).getNickname()));
        holder.sex.setText(String.format(context.getResources().getString(R.string.gender),data.get(position).getSex().equals("1") ? "男" : "女"));
        holder.phone.setText(String.format(context.getResources().getString(R.string.phone),data.get(position).getPhone()));
        holder.status.setText(String.format(context.getResources().getString(R.string.islogin),data.get(position).getStatus().equals("1") ? "不可登录" : "可登录"));
        holder.role.setText(String.format(context.getResources().getString(R.string.role),data.get(position).getRole()));
        if (onItemClickListener != null) {
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView nickname;
        View     view;
        TextView sex;
        TextView phone;
        TextView status;
        TextView role;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            username = (TextView) itemView.findViewById(R.id.tv_name);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            sex = (TextView) itemView.findViewById(R.id.sex);
            phone = (TextView)itemView.findViewById(R.id.tv_phone);
            status = (TextView)itemView.findViewById(R.id.tv_state);
            role = (TextView)itemView.findViewById(R.id.tv_role);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
}
