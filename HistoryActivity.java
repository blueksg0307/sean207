package com.example.administrator.project207;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class HistoryActivity extends AppCompatActivity {


    private ListView listView;
    private UserListAdapter adapter;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        //listView = (ListView) findViewById(R.id.HistoryListView);
       // userList = new ArrayList<User>();
        TextView textView = (TextView) findViewById(R.id.textView8);
        textView.setText(intent.getStringExtra("userList"));

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");

        }
        catch (Exception e){

        }

        /*

        try{

            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            String userID ;
            String userName;
            String userOrder;
            String bookDate;
            String Purpose;
            int count = 0 ;
/*
            while (count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                userID = object.getString("userID");
                userName = object.getString("userName");
                userOrder = object.getString("userOrder");
                bookDate = object.getString("bookDate");
                Purpose = object.getString("Purpose");
                User user = new User(userID, userName, userOrder, bookDate, Purpose);
                userList.add(user);
                count++;

            }
            if(count ==0){

            }

            adapter.notifyDataSetChanged();
        }
        catch (Exception E){


        }
*/
    }
    private long lastTimeBackPressed;
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500)
        {
            finish();
            return;
        }
        Toast.makeText(this, "’뒤로’ 버튼을 한번 더 누르면 뒤로갑니다.." ,Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
