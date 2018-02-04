package com.globant.akashdanao.hyperledgerdiamond.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.akashdanao.hyperledgerdiamond.Data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.R;

import java.util.List;

/**
 * Created by akash.danao on 04/02/18.
 */

public class DiamondAdapter extends RecyclerView.Adapter<DiamondAdapter.ViewHolder>{
    private List<Diamond> diamondList;

    public DiamondAdapter(List<Diamond> diamondList){
        this.diamondList = diamondList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_login, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.diamondList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view){
            super(view);
        }
    }
}
