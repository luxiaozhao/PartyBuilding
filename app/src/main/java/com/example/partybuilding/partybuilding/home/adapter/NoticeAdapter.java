package com.example.partybuilding.partybuilding.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.home.bean.Bww;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private Context context;
    private List<Bww> list;

    //接口监听     通知 FolderAdapter
    NoticeAdapter.onClickLinstener onClickLinstener;


    public void setOnClickLinstener(NoticeAdapter.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
    }

    public NoticeAdapter(Context context, List<Bww> datalist) {
        this.context = context;
        this.list = datalist;
    }

    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_adapter_layout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        NoticeAdapter.ViewHolder viewHolder = new NoticeAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final NoticeAdapter.ViewHolder holder, final int position) {
        holder.notice_adapter_name.setText(list.get(position).getName());
        holder.notice_adapter_time.setText(list.get(position).getShijian());
        holder.notice_adapter_layout.setOnClickListener(new View.OnClickListener() {
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

        private TextView notice_adapter_name;
        private TextView notice_adapter_time;
private LinearLayout notice_adapter_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            notice_adapter_name = itemView.findViewById(R.id.notice_adapter_name);
            notice_adapter_time = itemView.findViewById(R.id.notice_adapter_time);
            notice_adapter_layout=            itemView.findViewById(R.id.notice_adapter_layout);
        }
    }

    //定义点击接口
    public interface onClickLinstener {
        void setOnClick(View view, int position);
    }

}
