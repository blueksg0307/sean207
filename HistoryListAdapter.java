package com.example.administrator.project207;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017-04-05.
 */

public class HistoryListAdapter extends BaseAdapter {

    private Context context;
    private List<History> historyList;

    public HistoryListAdapter(Context context, List<History> historyList){
        this.context = context;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int i) {
        return historyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.history, null);
        TextView userID =(TextView) v.findViewById(R.id.id);
        TextView userNumber =(TextView) v.findViewById(R.id.usernumber);
        TextView userStatus =(TextView) v.findViewById(R.id.status);

        userID.setText(historyList.get(i).getId()+"번 째");
        userNumber.setText(historyList.get(i).getUsernumber()+"유저넘버");
        userStatus.setText(historyList.get(i).getStatus()+"유저상태");

        v.setTag(historyList.get(i).getId());
        return v;
    }
}
