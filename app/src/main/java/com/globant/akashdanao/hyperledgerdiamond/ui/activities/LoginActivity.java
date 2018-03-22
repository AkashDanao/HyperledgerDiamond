package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.utils.Utility;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.et_username) EditText et_username;

    enum UserType {
        MINER, TRANSPORTER, DISTRIBUTOR, OTHER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_login)
    public void onClickLogin(View v) {
        String email = et_username.getText().toString().trim();
        String password = et_password.getText().toString();

        Observable.just(email)
                .filter(e -> !TextUtils.isEmpty(e))
                .filter(Utility::isValidEmail)
                .switchIfEmpty(Observable.error(new InvalidEmailException()))
                .map(__ -> password)
                .filter(p -> !TextUtils.isEmpty(p))
                .filter(p -> p.equalsIgnoreCase("123456"))
                .switchIfEmpty(Observable.error(new InvalidPasswordException()))
                .subscribe(__ -> launchUserBasedHome(email), throwable -> {
                    if (throwable instanceof InvalidEmailException) {
                        et_username.setError("Please enter valid email id");
                    }

                    if (throwable instanceof InvalidPasswordException) {
                        Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void launchUserBasedHome(String email) {
        UserType userType;

        switch (email.substring(0, email.indexOf("@"))) {
            case "miner":
                userType = UserType.MINER;
                break;

            case "transporter":
                userType = UserType.TRANSPORTER;
                break;

            case "distributor":
                userType = UserType.DISTRIBUTOR;
                break;

            default:
                userType = UserType.OTHER;
        }

        HomeActivity.launchFor(this, userType);

        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private static class InvalidEmailException extends RuntimeException {}

    private static class InvalidPasswordException extends RuntimeException {}
}
