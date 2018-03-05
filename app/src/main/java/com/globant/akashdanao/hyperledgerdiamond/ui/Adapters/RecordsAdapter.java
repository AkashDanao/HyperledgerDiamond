package com.globant.akashdanao.hyperledgerdiamond.ui.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;
import com.globant.akashdanao.hyperledgerdiamond.ui.activities.DiamondDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {
    private List<Record> recordList;

    public RecordsAdapter(List<Record> recordList) {
        this.recordList = recordList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diamond_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Record record = recordList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_holder_name)
        TextView holderName;

        @BindView(R.id.tv_size)
        TextView size;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Record record) {
            holderName.setText("Holder Name : " + record.getHolder());
            size.setText("Vessle : " + record.getVessel());
        }
    }
}