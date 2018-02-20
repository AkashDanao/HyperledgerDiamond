package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

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

public class AddDiamondActivity extends AppCompatActivity {

    @BindView(R.id.et_id)
    TextView id;

    @BindView(R.id.et_holder_name)
    TextView holderName;

    @BindView(R.id.et_latitude)
    TextView latitude;

    @BindView(R.id.et_longitude)
    TextView longitude;

    @BindView(R.id.et_vessel)
    TextView vessel;

    @BindView(R.id.et_timestamp)
    TextView timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diamond);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_create)
    public void onClickAdd() {

        ApiClient.instance.saveRecord(id.getText().toString(), holderName.getText().toString(), Long.parseLong(latitude.getText().toString()), Long.parseLong(longitude.getText().toString()), Long.parseLong(timeStamp.getText().toString()), vessel.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        id -> Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_SHORT).show(),
                        e -> Toast.makeText(this, "There is some error", Toast.LENGTH_SHORT).show());
    }
}
