package com.globant.akashdanao.hyperledgerdiamond.tracker;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.globant.akashdanao.hyperledgerdiamond.data.ApiClient;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DiamondLocationTracker extends Service {
    private boolean isLocationBeingTracked;

    private static final String PUNE_MUMBAI = "{\n" +
            "   \"version\": \"1.1\",\n" +
            "   \"creator\": \"http://www.geoplaner.com\",\n" +
            "   \"schemaLocation\": \"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\",\n" +
            "   \"wpt\": [\n" +
            "      {\n" +
            "         \"lat\": \"19.07598\",\n" +
            "         \"lon\": \"72.87766\",\n" +
            "         \"ele\": \"5.5\",\n" +
            "         \"name\": \"WP02-B\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.04421\",\n" +
            "         \"lon\": \"72.92333\",\n" +
            "         \"ele\": \"9\",\n" +
            "         \"name\": \"WP03-C\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.05557\",\n" +
            "         \"lon\": \"72.94771\",\n" +
            "         \"ele\": \"3\",\n" +
            "         \"name\": \"WP04-D\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.06108\",\n" +
            "         \"lon\": \"72.96934\",\n" +
            "         \"ele\": \"1.6\",\n" +
            "         \"name\": \"WP05-E\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.06855\",\n" +
            "         \"lon\": \"72.98444\",\n" +
            "         \"ele\": \"2.7\",\n" +
            "         \"name\": \"WP06-F\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.06887\",\n" +
            "         \"lon\": \"72.9968\",\n" +
            "         \"ele\": \"5.3\",\n" +
            "         \"name\": \"WP07-G\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.06757\",\n" +
            "         \"lon\": \"73.015\",\n" +
            "         \"ele\": \"10.8\",\n" +
            "         \"name\": \"WP08-H\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.05492\",\n" +
            "         \"lon\": \"73.01946\",\n" +
            "         \"ele\": \"6.2\",\n" +
            "         \"name\": \"WP09-I\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.0348\",\n" +
            "         \"lon\": \"73.03011\",\n" +
            "         \"ele\": \"16.9\",\n" +
            "         \"name\": \"WP10-J\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.02441\",\n" +
            "         \"lon\": \"73.04933\",\n" +
            "         \"ele\": \"12.4\",\n" +
            "         \"name\": \"WP11-K\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.03415\",\n" +
            "         \"lon\": \"73.06924\",\n" +
            "         \"ele\": \"3.1\",\n" +
            "         \"name\": \"WP12-L\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.03642\",\n" +
            "         \"lon\": \"73.08057\",\n" +
            "         \"ele\": \"1.5\",\n" +
            "         \"name\": \"WP13-M\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.02733\",\n" +
            "         \"lon\": \"73.09602\",\n" +
            "         \"ele\": \"4.6\",\n" +
            "         \"name\": \"WP14-N\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"19.00786\",\n" +
            "         \"lon\": \"73.12212\",\n" +
            "         \"ele\": \"8.3\",\n" +
            "         \"name\": \"WP15-O\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.98838\",\n" +
            "         \"lon\": \"73.14237\",\n" +
            "         \"ele\": \"13.5\",\n" +
            "         \"name\": \"WP16-P\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.96598\",\n" +
            "         \"lon\": \"73.15851\",\n" +
            "         \"ele\": \"12.8\",\n" +
            "         \"name\": \"WP17-Q\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.93276\",\n" +
            "         \"lon\": \"73.16146\",\n" +
            "         \"ele\": \"27\",\n" +
            "         \"name\": \"WP18-R\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.89508\",\n" +
            "         \"lon\": \"73.20403\",\n" +
            "         \"ele\": \"35.2\",\n" +
            "         \"name\": \"WP19-S\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.8665\",\n" +
            "         \"lon\": \"73.21914\",\n" +
            "         \"ele\": \"23.2\",\n" +
            "         \"name\": \"WP20-T\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.8366\",\n" +
            "         \"lon\": \"73.2466\",\n" +
            "         \"ele\": \"36.7\",\n" +
            "         \"name\": \"WP21-U\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.81451\",\n" +
            "         \"lon\": \"73.27407\",\n" +
            "         \"ele\": \"71.1\",\n" +
            "         \"name\": \"WP22-V\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.78201\",\n" +
            "         \"lon\": \"73.31389\",\n" +
            "         \"ele\": \"128.4\",\n" +
            "         \"name\": \"WP23-W\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.7677\",\n" +
            "         \"lon\": \"73.3496\",\n" +
            "         \"ele\": \"221.5\",\n" +
            "         \"name\": \"WP24-X\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.7378\",\n" +
            "         \"lon\": \"73.43062\",\n" +
            "         \"ele\": \"620.3\",\n" +
            "         \"name\": \"WP25-Y\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.7443\",\n" +
            "         \"lon\": \"73.48281\",\n" +
            "         \"ele\": \"616.6\",\n" +
            "         \"name\": \"WP26-Z\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.73519\",\n" +
            "         \"lon\": \"73.53774\",\n" +
            "         \"ele\": \"837.1\",\n" +
            "         \"name\": \"WP27\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.71569\",\n" +
            "         \"lon\": \"73.55971\",\n" +
            "         \"ele\": \"601.3\",\n" +
            "         \"name\": \"WP28\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.71048\",\n" +
            "         \"lon\": \"73.6119\",\n" +
            "         \"ele\": \"625.3\",\n" +
            "         \"name\": \"WP29\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.71438\",\n" +
            "         \"lon\": \"73.65584\",\n" +
            "         \"ele\": \"648\",\n" +
            "         \"name\": \"WP30\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.69908\",\n" +
            "         \"lon\": \"73.67954\",\n" +
            "         \"ele\": \"590.5\",\n" +
            "         \"name\": \"WP31\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.66786\",\n" +
            "         \"lon\": \"73.70563\",\n" +
            "         \"ele\": \"582.3\",\n" +
            "         \"name\": \"WP32\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.63403\",\n" +
            "         \"lon\": \"73.73996\",\n" +
            "         \"ele\": \"574.4\",\n" +
            "         \"name\": \"WP33\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.61061\",\n" +
            "         \"lon\": \"73.75095\",\n" +
            "         \"ele\": \"580.3\",\n" +
            "         \"name\": \"WP34\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.54031\",\n" +
            "         \"lon\": \"73.77979\",\n" +
            "         \"ele\": \"613.2\",\n" +
            "         \"name\": \"WP35\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.49929\",\n" +
            "         \"lon\": \"73.78872\",\n" +
            "         \"ele\": \"613.3\",\n" +
            "         \"name\": \"WP36\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.46803\",\n" +
            "         \"lon\": \"73.81343\",\n" +
            "         \"ele\": \"570.4\",\n" +
            "         \"name\": \"WP37\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.4511\",\n" +
            "         \"lon\": \"73.85738\",\n" +
            "         \"ele\": \"652.2\",\n" +
            "         \"name\": \"WP38\"\n" +
            "      },\n" +
            "      {\n" +
            "         \"lat\": \"18.51232\",\n" +
            "         \"lon\": \"73.85738\",\n" +
            "         \"ele\": \"566\",\n" +
            "         \"name\": \"WP39\"\n" +
            "      }\n" +
            "   ]\n" +
            "}";

    private Disposable disposable;

    public class DiamondLocationTrackerBinder extends Binder {
        public DiamondLocationTracker getService() {
            return DiamondLocationTracker.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new DiamondLocationTrackerBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY_COMPATIBILITY;
    }

    public boolean isLocationBeingTracked() {
        return isLocationBeingTracked;
    }

    public void startLocationUpdates(String diamondId) {
        Gson gson = new Gson();
        LocationData locationData = gson.fromJson(PUNE_MUMBAI, LocationData.class);

        disposable = Flowable.interval(2, TimeUnit.SECONDS, Schedulers.io())
                .zipWith(Flowable.fromIterable(locationData.wpt), (aLong, wpt) -> wpt)
                .flatMap(wpt -> ApiClient.instance.updateLocationData(diamondId, wpt.lat, wpt.lon))
                .subscribe();

        isLocationBeingTracked = true;
    }

    public void stopLocationUpdates() {
        isLocationBeingTracked = false;
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static class LocationData {
        public List<Wpt> wpt;
    }

    public static class Wpt {
        public double lat;
        public double lon;
    }
}