package com.globant.akashdanao.hyperledgerdiamond.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.ui.adapters.RecordsAdapter;
import com.globant.akashdanao.hyperledgerdiamond.utils.PicassoCircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LandingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LandingFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    String TAG = LandingFragment.class.getSimpleName();


    @BindView(R.id.rv_diamond)
    RecyclerView recyclerViewDiamond;

    @BindView(R.id.iv_avatar)
    ImageView imageViewUser;

    @BindView(R.id.tv_username)
    TextView textViewUserName;

    private RecordsAdapter recordsAdapter;

    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;


    public LandingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(getActivity()).load(R.drawable.userpic)
                .transform(new PicassoCircleTransformation()).into((imageViewUser));
        init();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewFlipper.setDisplayedChild(0);
        ApiClient.instance.getAllDiamonds()
                .flatMap(Flowable::fromIterable)
                .toList()
                .toFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bindDataToRecyclerView, this::handleError);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewDiamond.setLayoutManager(layoutManager);
    }

    private void bindDataToRecyclerView(List<Diamond> diamond) {
        Collections.sort(diamond);
        viewFlipper.setDisplayedChild(1);
        recordsAdapter = new RecordsAdapter(diamond);
        recyclerViewDiamond.setAdapter(recordsAdapter);
    }

    private void handleError(Throwable throwable) {
        viewFlipper.setDisplayedChild(1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
