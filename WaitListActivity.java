package com.example.administrator.project207;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WaitListActivity extends AppCompatActivity {

    private ListView listView ;
    private UserListAdapter adapter ;
    private List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_list);

        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<User>();

       /* userList.add(new User("1", "20170410", "예약시간", "홍길동"));
        userList.add(new User("2", "20170411", "예약시간", "김갑수"));
        userList.add(new User("3", "20170410", "예약시간", "김선광"));
        userList.add(new User("4", "20170410", "예약시간", "최윤준"));

*/

        adapter =  new UserListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);
    }
}
