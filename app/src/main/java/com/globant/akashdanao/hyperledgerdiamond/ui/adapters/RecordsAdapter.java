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
import com.globant.akashdanao.hyperledgerdiamond.utils.Constants;
import com.globant.akashdanao.hyperledgerdiamond.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Diamond> diamondList;
    private View view;


    public RecordsAdapter(List<Diamond> diamondList) {
        this.diamondList = diamondList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.ITEM_ONE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diamond_item, parent, false);
            return new ViewHolderDefault(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diamond_item_2, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Diamond diamond = diamondList.get(position);
        switch (holder.getItemViewType()) {
            case Constants.ITEM_ONE:
                ViewHolderDefault viewHolderDefault = (ViewHolderDefault) holder;
                viewHolderDefault.bind(diamond);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DiamondDetailsFragment fragment = new DiamondDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("RECORD_NUMBER", diamond.getKey());
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                    }
                });
                break;

            case Constants.ITEM_TWO:
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.bind(diamond);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DiamondDetailsFragment fragment = new DiamondDetailsFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("RECORD_NUMBER", diamond.getKey());
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                    }
                });
                break;
        }


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

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Constants.ITEM_ONE;
        } else {
            return Constants.ITEM_TWO;
        }
    }

    public static class ViewHolderDefault extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_holder_name)
        TextView holderName;

        @BindView(R.id.tv_size)
        TextView size;

        @BindView(R.id.iv_diamond)
        ImageView imageViewDiamond;

        public ViewHolderDefault(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Diamond diamond) {
            holderName.setText(diamond.getRecord().getName());
            size.setText("#" + diamond.getKey());
            if (!diamond.getRecord().getImage().toString().equalsIgnoreCase("")) {
                imageViewDiamond.setImageBitmap(Utility.getBitmap(diamond.getRecord().getImage()));
            }
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        String TAG = RecordsAdapter.class.getSimpleName();
        @BindView(R.id.tv_diamond_item_name)
        TextView tvDiamondName;

        @BindView(R.id.tv_diamond_id)
        TextView tvDimondId;

        @BindView(R.id.iv_circle)
        ImageView imageViewIcon;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Diamond diamond) {
            tvDiamondName.setText(diamond.getRecord().getName());
            tvDimondId.setText("#" + diamond.getKey());
            int drawableId;
            if (diamond.getRecord().getType().equalsIgnoreCase(Constants.ADD)) {
                drawableId = R.drawable.diamond_add_background;
            } else {
                drawableId = R.drawable.drawable_transfer_background;
            }
            imageViewIcon.setImageDrawable(itemView.getContext().getResources().getDrawable(drawableId));
        }
    }
}