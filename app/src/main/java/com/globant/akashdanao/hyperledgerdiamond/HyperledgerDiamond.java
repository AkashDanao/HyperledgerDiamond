package com.globant.akashdanao.hyperledgerdiamond;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author Anand Soni
 */

public class HyperledgerDiamond extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/sfui_display_light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
