package com.example.administrator.project207;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private BeaconManager beaconManager ;
    private Region region;
    private String beacon_uuid;
    private int beacon_major;
    private int beacon_minor;
    private AlertDialog dialog;
    private boolean success ;
    private String userID;
    private String userName;

    Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                boolean success = jsonObject.getBoolean("success");
                if(success){



                }

                /*success = jsonObject.getBoolean("success");
                Toast.makeText(getApplicationContext(),"진료접수가 정상적으로 되었습니다",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("진료순서를 확인하시겠습니까?")
                        .setPositiveButton("네", null)
                        .setNegativeButton("아니요",null)
                        .show();

*/


            } catch (Exception e) {

                e.printStackTrace();}
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView booking  = (ImageView) findViewById(R.id.booking);
        final ImageView myinfo   = (ImageView) findViewById(R.id.myinfo);
        final ImageView callbook = (ImageView) findViewById(R.id.callbook);
        final ImageView checkbook  = (ImageView) findViewById(R.id.bookcheck);
        final ImageView waitingList   = (ImageView) findViewById(R.id.waitinglist);
        final ImageView history = (ImageView) findViewById(R.id.histroy);


        Intent Information = getIntent();
        userID = Information.getStringExtra("userID");
        userName = Information.getStringExtra("userName");
        /* userBirth = Information.getStringExtra("userPhone");
        userNumeber = Information.getStringExtra("userPhone");*/


        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(MainActivity.this, MyinfoActivity.class);
                Intent.putExtra("userID" , userID);
                Intent.putExtra("userName" , userName);
                // Intent.putExtra("userPhone" , userPhone);
                MainActivity.this.startActivity(Intent);
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(MainActivity.this, ReserveActivity.class);
                Intent.putExtra("userID" , userID);
                Intent.putExtra("userName" , userName);
               /* Intent.putExtra("userPhone" , userPhone);*/
                MainActivity.this.startActivity(Intent);
            }
        });

        callbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("tel:010-7759-5051");
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);


            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener histroyresponseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        try {

                            JSONArray jsonArray = new JSONArray(response);

                            if(jsonArray.length() >= 1) {


                                Intent Intent = new Intent(MainActivity.this, HistoryActivity.class);
                                Intent.putExtra("userList", jsonArray.toString());
                                MainActivity.this.startActivity(Intent);

                            }
                            else{

                                Toast.makeText(MainActivity.this,"진료기록이 없습니다",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                HistoryRequest historyRequest = new HistoryRequest(userID, histroyresponseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(historyRequest);

            }
        });

        waitingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckWaiting.class);
                MainActivity.this.startActivity(intent);
            }
        });

        checkbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(MainActivity.this, CheckbookActivity.class);
                MainActivity.this.startActivity(Intent);
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager = new BeaconManager(this);
        region = new Region("ranged region",
                UUID.fromString("43cbda6e-28fa-4f5b-af12-416caf3e3737"),null,null);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {

                    Beacon nearestBeacon = list.get(0);
                    beacon_uuid = nearestBeacon.getProximityUUID().toString();
                    beacon_major = nearestBeacon.getMajor();
                    beacon_minor = nearestBeacon.getMinor();

                }
            }
        });

       beacon_uuid = "b9407f30-f5f8-466e-aff9-25556b57fe19";

        if(!beacon_uuid.isEmpty()) {

            if(success){
                Toast.makeText(this,"이미 진료대기중인 환자입니다", Toast.LENGTH_SHORT).show();
            }

            else{
                new BackgroundTask().execute();}

        }
    }


    private long lastTimeBackPressed;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500)
        {
            finish();
            return;
        }
        Toast.makeText(this, "’뒤로’ 버튼을 한번 더 누르면 종료합니다." ,Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {


            // RequestStt

            BeaconRequest beaconRequest = new BeaconRequest(beacon_uuid, userID, responseListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(beaconRequest);

            return null;
        }

    }
}