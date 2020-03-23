package com.qibu.sdk.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainListAdapter extends RecyclerView.Adapter<VHolder> {

    Context context;
    private List<ItemBean> mList;

    MainListAdapter(Context c) {
        context = c;
        mList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ItemBean bean = new ItemBean();
            bean.imageUrl = "";
            bean.name = "item " + i;
            mList.add(bean);
        }
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_act_list, parent, false);
        return new VHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        holder.textView.setText(mList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}


class VHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    public VHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.text);
    }
}