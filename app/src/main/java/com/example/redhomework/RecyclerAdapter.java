package com.example.redhomework;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> mlist = new ArrayList();

    //---------创建ViewHolder
    static public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time;
        TextView tv_title;
        public MyViewHolder(@NonNull View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

    public RecyclerAdapter(List<Item> list) {
        mlist = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
                MyViewHolder holder1 = (MyViewHolder) holder;
                Item item = mlist.get(position);
                holder1.tv_title.setText(item.getTitle());
                holder1.tv_time.setText(item.getTime() + "   " + item.getAuthor());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
