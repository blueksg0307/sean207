package com.example.administrator.beacontest;


import android.support.v7.app.AppCompatActivity;
import android.app.Application;
import android.os.Bundle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private BeaconManager beaconManager ;
    private Region region ;
    private String beacon_UUID ;
    private int beacon_Major;
    private int beacon_Minor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textview1);

        beaconManager = new BeaconManager(this);
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if(!list.isEmpty()){

                    Beacon nearestBeacon = list.get(0);
                    beacon_Major = nearestBeacon.getMajor();
                    beacon_Minor = nearestBeacon.getMinor();
                    beacon_UUID = nearestBeacon.getProximityUUID().toString();

                    textView.setText("UUID:"+ beacon_UUID + "Major:" + beacon_Major + "Minor:" + beacon_Minor );
                }
            }
        });

        region = new Region("ranged region",
                UUID.fromString("43CBDA6E-28FA-4F5B-AF12-416CAF3E3737"),
                null,null);


    }

    @Override
    public void onResume(){
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);



    }

    @Override
    public void onPause(){
        beaconManager.stopRanging(region);
        super.onPause();
    }
}
