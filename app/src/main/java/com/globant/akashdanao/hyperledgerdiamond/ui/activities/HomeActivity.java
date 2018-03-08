package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.ui.adapters.RecordsAdapter;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.AddDiamondFragment;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.LandingFragment;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        loadFirstFragment(new LandingFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment fragment;
            switch (item.getItemId()){
                case R.id.action_diamond :
                    fragment = new LandingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_add :
                    fragment = new AddDiamondFragment();
                    loadFragment(fragment);
                    return  true;
                case R.id.action_search :
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };


    private void loadFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_home, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadFirstFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_home, fragment);
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
