package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.globant.akashdanao.hyperledgerdiamond.utils.Utility;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
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
    @BindView(R.id.textViewHolderName)
    TextView textViewHolderName;
    @BindView(R.id.textViewDiamondJourney)
    TextView textViewDiamondJourney;
    private List<Record> listRecords;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diamond_detail_fragment, null);
        ButterKnife.bind(this, view);
        viewFlipper.setDisplayedChild(0);
        disposable.add(ApiClient.instance.searchRecord(getArguments().getString("RECORD_NUMBER"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bindDataToView, this::handleError));
        textViewDiamondJourney.setOnClickListener(v -> {
            DiamondJourneyFragment journeyFragment = new DiamondJourneyFragment();
            journeyFragment.setListRecords(listRecords);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fl_home, journeyFragment, journeyFragment.getClass().getName());
            ft.commit();
        });
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.clear();
    }

    private void handleError(Throwable throwable) {
        viewFlipper.setDisplayedChild(1);
        Toast.makeText(getActivity(), "No record found", Toast.LENGTH_SHORT).show();
    }

    private void bindDataToView(List<Record> listRecords) {
        this.listRecords = listRecords;
        viewFlipper.setDisplayedChild(1);
        textViewDiamondTitle.setText(listRecords.get(0).getName());
        textViewCarat.setText(listRecords.get(0).getCarat());
        textViewCertificationName.setText(listRecords.get(0).getCert());
        textViewClarity.setText(listRecords.get(0).getClarity());
        textViewColor.setText(listRecords.get(0).getColor());
        textViewDiamondNumber.setText("#" + getArguments().getString("RECORD_NUMBER"));
        textViewTransactionHash.setText(listRecords.get(0).getTransid());
        textViewCut.setText(listRecords.get(0).getCut());
        textViewHolderName.setText(listRecords.get(0).getHoldername());
        imageViewDiamond.setImageBitmap(listRecords.get(0).getImage() != "" ? Utility.getBitmap(listRecords.get(0).getImage()) : BitmapFactory.decodeResource(view.getContext().getResources(),
                R.drawable.diamond_placeholder));
    }

}
