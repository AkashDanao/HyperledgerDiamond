package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.utils.Constants;
import com.suke.widget.SwitchButton;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDiamondFragment extends Fragment implements SwitchButton.OnCheckedChangeListener {

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
    @BindView(R.id.et_new_record)
    TextInputEditText et_diamond_name;
    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.et_diamond_id)
    TextInputEditText et_diamond_id;

    String certification;
    String TAG = AddDiamondFragment.class.getSimpleName();

    HashMap<String, String> mapCertification = new HashMap<>();

    public AddDiamondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        viewFlipper.setDisplayedChild(1);
        sbIGI.setOnCheckedChangeListener(this);
        sbGIA.setOnCheckedChangeListener(this);
        sbHRD.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.sb_gia:
                if (isChecked) {
                    mapCertification.put(Constants.GIA, Constants.GIA);
                } else {
                    mapCertification.remove(Constants.GIA);
                }
                break;
            case R.id.sb_hrd:
                if (isChecked) {
                    mapCertification.put(Constants.HRD, Constants.HRD);
                } else {
                    mapCertification.remove(Constants.HRD);
                }
                break;
            case R.id.sb_igi:
                if (isChecked) {
                    mapCertification.put(Constants.IGI, Constants.IGI);
                } else {
                    mapCertification.remove(Constants.IGI);
                }
                break;
        }
    }

    @OnClick(R.id.iv_add_photos)
    public void onAddPhotosClick() {

    }

    @OnClick(R.id.button_add_record)
    public void onAddRecordButtonClick() {
        certification = "";
        for (Map.Entry<String, String> entry : mapCertification.entrySet()) {
            if (certification.equalsIgnoreCase("")) {
                certification = entry.getValue();
            } else {
                certification = certification + "," + entry.getValue();
            }
        }
        Log.d(TAG, "onAddRecordButtonClick: " + certification);
        // id , color, cut, carat, clarity, certification, name
        viewFlipper.setDisplayedChild(0);
        ApiClient.instance.saveDiamondRecord(et_diamond_id.getText().toString(), etColor.getText().toString(), etCut.getText().toString(), etCarat.getText().toString(), etClarity.getText().toString(), certification, et_diamond_name.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        id -> {
                            viewFlipper.setDisplayedChild(1);
                            Toast.makeText(getActivity(), "Record Added Successfully", Toast.LENGTH_SHORT).show();
                        },
                        e -> Toast.makeText(getActivity(), "There is some error", Toast.LENGTH_SHORT).show());

    }
}
