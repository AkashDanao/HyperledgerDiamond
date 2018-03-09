package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DiamondDetailsFragment extends Fragment {

    private View view;
    private Record record;
    @BindView(R.id.textViewDiamondTitle)
    TextView textViewDiamondTitle;
    @BindView(R.id.textViewDiamondNumber)
    TextView textViewDiamondNumber;
    @BindView(R.id.textViewTransactionHash)
    TextView textViewTransactionHash;
    @BindView(R.id.textViewCertificationName)
    TextView textViewCertificationName;
    @BindView(R.id.textViewClarity)
    TextView textViewClarity;
    @BindView(R.id.textViewColor)
    TextView textViewColor;
    @BindView(R.id.textViewCut)
    TextView textViewCut;
    @BindView(R.id.textViewCarat)
    TextView textViewCarat;
    @BindView(R.id.imageViewDiamond)
    ImageView imageViewDiamond;
    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diamond_detail_fragment, null);
        ButterKnife.bind(this, view);
        viewFlipper.setDisplayedChild(0);
        ApiClient.instance.searchRecord(getArguments().getString("RECORD_NUMBER"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bindDataToView, this::handleError);
        return view;
    }


    private void handleError(Throwable throwable) {
        Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
    }

    private void bindDataToView(Record record) {
        viewFlipper.setDisplayedChild(1);
        textViewDiamondTitle.setText(record.getName());
        textViewCarat.setText(record.getCarat());
        textViewCertificationName.setText(record.getCert());
        textViewClarity.setText(record.getClarity());
        textViewColor.setText(record.getColor());
        textViewDiamondNumber.setText("#" + getArguments().getString("RECORD_NUMBER"));
        textViewTransactionHash.setText(record.getTransid());
        textViewCut.setText(record.getCut());
        Picasso.with(view.getContext()).load(R.drawable.diamond_placeholder).into(imageViewDiamond);
    }

}
