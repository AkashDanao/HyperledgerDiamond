package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddDiamondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
