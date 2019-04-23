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
        holder.textView.setText("用户名: " + data.get(position).getUsername() + "，电话：" + data.get(position).getPhone());
        holder.textView2.setText(data.get(position).getRole());
        if (onItemClickListener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
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
        TextView textView;
        TextView textView2;
        View     view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.tv_name);
            textView2 = (TextView) itemView.findViewById(R.id.tv_state);
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
