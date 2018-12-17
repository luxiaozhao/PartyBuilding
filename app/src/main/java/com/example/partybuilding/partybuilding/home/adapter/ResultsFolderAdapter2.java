package com.example.partybuilding.partybuilding.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.partybuilding.partybuilding.R;
import com.example.partybuilding.partybuilding.home.bean.Beanw;

import java.util.List;

public class ResultsFolderAdapter2 extends RecyclerView.Adapter<ResultsFolderAdapter2.ViewHolder> {
private Context context;
private List<Beanw> list;

        //接口监听
        ResultsFolderAdapter2.onClickLinstener onClickLinstener;


public void setOnClickLinstener(ResultsFolderAdapter2.onClickLinstener onClickLinstener) {
        this.onClickLinstener = onClickLinstener;
        }

public ResultsFolderAdapter2(Context context, List<Beanw> datalist) {
        this.context = context;
        this.list = datalist;
        }

@Override
public ResultsFolderAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_results_list_layout2, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        ResultsFolderAdapter2.ViewHolder viewHolder = new ResultsFolderAdapter2.ViewHolder(view);
        return viewHolder;
        }


@Override
public void onBindViewHolder(final ResultsFolderAdapter2.ViewHolder holder, final int position) {

        holder.item_results_list_layout2_name.setText(list.get(position).getStr());
        holder.item_results_list_layout2_shijian.setText(list.get(position).getStr1());
        holder.item_results_list_layout2_gaoyao.setText(list.get(position).getStr2());

        }

@Override
public int getItemCount() {
        return list.size();
        }

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView item_results_list_layout2_name;
    private TextView item_results_list_layout2_shijian;
    private TextView item_results_list_layout2_gaoyao;

    public ViewHolder(View itemView) {
        super(itemView);
        item_results_list_layout2_name = itemView.findViewById(R.id.item_results_list_layout2_name);
        item_results_list_layout2_shijian = itemView.findViewById(R.id.item_results_list_layout2_shijian);
        item_results_list_layout2_gaoyao = itemView.findViewById(R.id.item_results_list_layout2_gaoyao);
    }
}

//定义点击接口
public interface onClickLinstener {
    void setOnClick(View view, int position);
}

}
