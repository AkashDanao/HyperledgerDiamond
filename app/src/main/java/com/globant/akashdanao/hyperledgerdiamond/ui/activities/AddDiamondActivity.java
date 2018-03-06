package com.globant.akashdanao.hyperledgerdiamond.ui.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.globant.akashdanao.hyperledgerdiamond.R;
import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.suke.widget.SwitchButton;

import org.w3c.dom.Text;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddDiamondActivity extends AppCompatActivity implements SwitchButton.OnCheckedChangeListener {

    @BindView(R.id.sb_igi)
    SwitchButton sbIGI;
    @BindView(R.id.sb_gia)
    SwitchButton sbGIA;
    @BindView(R.id.sb_hrd)
    SwitchButton sbHRD;
    @BindView(R.id.et_clarity)
    TextInputEditText etClarity;
    @BindView(R.id.et_carat)
    TextInputEditText etCarat;
    @BindView(R.id.et_color)
    TextInputEditText etColor;
    @BindView(R.id.et_cut)
    TextInputEditText etCut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);
        ButterKnife.bind(this);
        sbIGI.setOnCheckedChangeListener(this);
        sbGIA.setOnCheckedChangeListener(this);
        sbHRD.setOnCheckedChangeListener(this);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick(R.id.iv_add_photos)
    public void onAddPhotosClick() {

    }

    @OnClick(R.id.button_add_record)
    public void onAddRecordButtonClick() {
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        switch (view.getId()) {
            case R.id.sb_gia:
                break;
            case R.id.sb_hrd:
                break;
            case R.id.sb_igi:
                break;
        }
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
