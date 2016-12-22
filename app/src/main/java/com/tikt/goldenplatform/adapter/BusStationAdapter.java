package com.tikt.goldenplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TikT on 2016/12/21.
 * 无线公交的某辆公交的目前行驶位置
 */

public class BusStationAdapter extends RecyclerView.Adapter<BusStationAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private Context context;


    public BusStationAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
