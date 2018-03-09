package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferFragment extends Fragment {

    @BindView(R.id.et_id)
    EditText etDiamondId;
    @BindView(R.id.et_holder_name)
    EditText etHolderName;
    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;

    public TransferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);
        ButterKnife.bind(this, view);
        viewFlipper.setDisplayedChild(1);
        return view;
    }


    @OnClick(R.id.button_change_holder)
    public void onChangeHolder() {
        viewFlipper.setDisplayedChild(0);
        ApiClient.instance.changeHolderName(etDiamondId.getText().toString(), etHolderName.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        id -> {
                            viewFlipper.setDisplayedChild(1);
                            Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.change_holder_success), Toast.LENGTH_SHORT).show();
                        },
                        e -> Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.change_holder_error), Toast.LENGTH_SHORT).show()
                );
    }
}
