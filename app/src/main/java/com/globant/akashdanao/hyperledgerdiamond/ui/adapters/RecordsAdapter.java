package com.globant.akashdanao.hyperledgerdiamond.ui.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.DiamondDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {
    private List<Diamond> diamondList;
    private View view;

    public RecordsAdapter(List<Diamond> diamondList) {
        this.diamondList = diamondList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diamond_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Diamond diamond = diamondList.get(position);
        holder.bind(diamond);
        holder.imageViewDiamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiamondDetailsFragment fragment = new DiamondDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("RECORD_NUMBER", diamond.getKey());
                fragment.setArguments(bundle);
                loadFragment(fragment);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
        FragmentTransaction ft = appCompatActivity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_home, fragment, fragment.getClass().getName());
        ft.commit();
    }

    @Override
    public int getItemCount() {
        return diamondList.size();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_holder_name)
        TextView holderName;

        @BindView(R.id.tv_size)
        TextView size;

        @BindView(R.id.iv_diamond)
        ImageView imageViewDiamond;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Diamond diamond) {
            holderName.setText(diamond.getRecord().getName());
            size.setText("#" + diamond.getKey());
            Picasso.with(itemView.getContext()).load(R.drawable.diamond_placeholder).into(imageViewDiamond);
        }
    }
}