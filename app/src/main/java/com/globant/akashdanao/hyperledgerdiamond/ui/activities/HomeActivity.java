package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Diamond;
import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.Models.Record;
import com.globant.akashdanao.hyperledgerdiamond.ui.Adapters.RecordsAdapter;
import com.globant.akashdanao.hyperledgerdiamond.ui.Fragments.LandingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements LandingFragment.OnFragmentInteractionListener {
    String TAG = HomeActivity.class.getSimpleName();


    private RecordsAdapter recordsAdapter;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    LandingFragment loadingFragment;
    android.support.v4.app.FragmentTransaction transaction;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        fragmentManager =  getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        loadFirstFragment();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment fragment;
            switch (item.getItemId()){
                case R.id.action_diamond :
//                    if (fragmentManager.findFragmentByTag("1") == null){
//                        loadingFragment = new LandingFragment();
//                    }
//                    loadFragament(loadingFragment);

                    fragment = new LandingFragment();
                    loadFragament(fragment);
                    return true;
                case R.id.action_add :
                    return  true;

                case R.id.action_search :
                    return true;

            }
            return false;
        }
    };

    public void loadFirstFragment(){
        transaction.replace(R.id.fl_home, new LandingFragment());
        transaction.commit();
    }

    public void loadFragament(android.support.v4.app.Fragment fragment){
        transaction.replace(R.id.fl_home, fragment, "1");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
