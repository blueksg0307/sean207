package com.example.administrator.project207;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView ;
    private UserListAdapter adapter ;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();
        adapter =  new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);


        try{

            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("resposne");
            int count = 0 ;
            String userID, userName, bookDate, Purpose, userOrder;

            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("id");
                userName = object.getString("usernumber");
               // bookDate = object.getString("wanttime");
                Purpose = object.getString("status");
              //  userOrder = object.getString("");
                User user = new User(userID, userName,Purpose);
                userList.add(user);
                count ++;
            }
        }
        catch (Exception e){

            e.printStackTrace();

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
}
