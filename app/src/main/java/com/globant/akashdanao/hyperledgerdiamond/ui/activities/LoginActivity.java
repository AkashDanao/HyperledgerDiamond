package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.utils.Constants;
import com.globant.akashdanao.hyperledgerdiamond.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_username)
    EditText et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_login)
    public void onClickLogin(View v) {
        String email = et_username.getText().toString().trim();
        if (!Utility.isValidEmail(email)) {
            et_username.setError("Please enter valid email id");
        } else if (et_password.getText().toString().equalsIgnoreCase("")) {
            et_password.setError("Required field");
        } else if (!et_password.getText().toString().equalsIgnoreCase("123456")) {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
        } else if (et_password.getText().toString().equalsIgnoreCase("123456")) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
