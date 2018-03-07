package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDiamondFragment extends Fragment implements SwitchButton.OnCheckedChangeListener{

    @BindView(R.id.sb_igi)
    SwitchButton sbIGI;
    @BindView(R.id.sb_gia)
    SwitchButton sbGIA;
    @BindView(R.id.sb_hrd)
    SwitchButton sbHRD;
    @BindView(R.id.et_clarity)
    TextInputEditText etClarity;
    @BindView(R.id.et_carat)
    TextInputEditText etCarat;
    @BindView(R.id.et_color)
    TextInputEditText etColor;
    @BindView(R.id.et_cut)
    TextInputEditText etCut;


    public AddDiamondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        sbIGI.setOnCheckedChangeListener(this);
        sbGIA.setOnCheckedChangeListener(this);
        sbHRD.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.sb_gia:
                break;
            case R.id.sb_hrd:
                break;
            case R.id.sb_igi:
                break;
        }
    }

    @OnClick(R.id.iv_add_photos)
    public void onAddPhotosClick() {

    }

    @OnClick(R.id.button_add_record)
    public void onAddRecordButtonClick() {
    }

    /* @OnClick(R.id.bt_create)
    public void onClickAdd() {

        ApiClient.instance.saveRecord(id.getText().toString(), holderName.getText().toString(), Long.parseLong(latitude.getText().toString()), Long.parseLong(longitude.getText().toString()), Long.parseLong(timeStamp.getText().toString()), vessel.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        id -> Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_SHORT).show(),
                        e -> Toast.makeText(this, "There is some error", Toast.LENGTH_SHORT).show());
    }*/

}
