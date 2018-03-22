package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.AddDiamondFragment;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.LandingFragment;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.SearchFragment;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.TransferFragment;
import com.globant.akashdanao.hyperledgerdiamond.ui.fragments.TransporterFragment;
import com.globant.akashdanao.hyperledgerdiamond.utils.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements LandingFragment.OnFragmentInteractionListener {
    private static final String USER_TYPE = LoginActivity.UserType.class.getSimpleName();

    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;

    public static void launchFor(Context context, LoginActivity.UserType userType) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(USER_TYPE, userType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initBottomNavigation((LoginActivity.UserType) getIntent().getSerializableExtra(USER_TYPE));
    }

    private void initBottomNavigation(LoginActivity.UserType userType) {
        int menuResId;

        switch (userType) {
            case TRANSPORTER:
                menuResId = R.menu.navigation_transporter;
                break;

            case MINER:
                menuResId = R.menu.navigation_miner;
                break;

            default:
                menuResId = R.menu.navigation_common;
                break;
        }

        bottomNavigationView.inflateMenu(menuResId);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(0).getItemId());
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.action_diamond:
                fragment = new LandingFragment();
                break;

            case R.id.action_add:
                fragment = new AddDiamondFragment();
                break;

            case R.id.action_search:
                fragment = new SearchFragment();
                break;

            case R.id.action_transfer:
                fragment = new TransferFragment();
                break;

            default:
                fragment = new TransporterFragment();
                break;
        }

        loadFragment(fragment);

        return true;
    };

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_home, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int firstItemId = bottomNavigationView.getMenu().getItem(0).getItemId();

        if (bottomNavigationView.getSelectedItemId() != firstItemId) {
            bottomNavigationView.setSelectedItemId(firstItemId);
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
