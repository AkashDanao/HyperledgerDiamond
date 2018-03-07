package com.globant.akashdanao.hyperledgerdiamond.ui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.akashdanao.hyperledgerdiamond.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<String> dataList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_single_item, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList != null && dataList.size() > 0 ? dataList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bindData(String data) {

        }
    }


    public void setData(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }
}
