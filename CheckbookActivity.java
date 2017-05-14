package com.example.administrator.project207;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CheckbookActivity extends AppCompatActivity {


    private ListView listView ;
    private UserListAdapter adapter ;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbook);

        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();

       /* userList.add(new User("13:00~13:30", "20170522", "", "방문예정시간"));
        userList.add(new User("14:00~14:30", "20170611", "", "방문예정시간"));
        userList.add(new User("15:00~15:30", "20170710", "", "방문예정시간"));
        userList.add(new User("16:00~16:30", "20170810", "", "방문예정시간"));
*/

        adapter =  new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);
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
