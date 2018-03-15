package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
    private View view;

    public TransferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_transfer, container, false);
        ButterKnife.bind(this, view);
        viewFlipper.setDisplayedChild(1);
        return view;
    }


    @OnClick(R.id.button_change_holder)
    public void onChangeHolder() {
        hideKeyBoard();
        String diamondId = etDiamondId.getText().toString().trim();
        String holderName = etHolderName.getText().toString().trim();
        if (diamondId.equalsIgnoreCase("")) {
            etDiamondId.setError("Required field");
        } else if (holderName.equalsIgnoreCase("")) {
            etHolderName.setError("Required field");
        } else {
            viewFlipper.setDisplayedChild(0);
            ApiClient.instance.changeHolderName(diamondId, holderName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            id -> {
                                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.change_holder_success), Toast.LENGTH_SHORT).show();
                                DiamondDetailsFragment fragment = new DiamondDetailsFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("RECORD_NUMBER", etDiamondId.getText().toString());
                                fragment.setArguments(bundle);
                                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.fl_home, fragment, fragment.getClass().getName());
                                ft.commit();
                            },
                            e -> {
                                viewFlipper.setDisplayedChild(1);
                                Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.change_holder_error), Toast.LENGTH_SHORT).show();
                            }
                    );
        }

    }

    public void hideKeyBoard() {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
