package com.globant.akashdanao.hyperledgerdiamond.UI.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.globant.akashdanao.hyperledgerdiamond.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_login)
    public void onClickLogin(View v){
        Intent intent = new Intent(this,HomeActivity.class);
        this.startActivity(intent);
    }
}
