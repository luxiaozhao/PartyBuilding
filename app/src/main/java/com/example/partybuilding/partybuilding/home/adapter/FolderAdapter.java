package com.example.partybuilding.partybuilding.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.home.bean.Bean;

import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ViewHolder> {
    private Context context;
    private List<Bean> list;

    //接口监听     通知 FolderAdapter
    FolderAdapter.onClickLinstener onClickLinstener;

    public void setOnClickLinstener(FolderAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public FolderAdapter(Context context, List<Bean> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public FolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_results_list_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        FolderAdapter.ViewHolder viewHolder = new FolderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FolderAdapter.ViewHolder holder, final int position) {

        Glide.with(context)
                .load(list.get(position).getImg())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .crossFade(1000)
                .into(holder.adapter_img);
        holder.adapter_name.setText(list.get(position).getString());

        holder.adapter_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onClickLinstener != null) {
                    onClickLinstener.setOnClick(view, position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView adapter_img;
        private TextView adapter_name;
        private LinearLayout adapter_linear;

        public ViewHolder(View itemView) {
            super(itemView);
            adapter_img = itemView.findViewById(R.id.adapter_img);
            adapter_name = itemView.findViewById(R.id.adapter_name);
            adapter_linear = itemView.findViewById(R.id.adapter_linear);
        }
    }

    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }

}
