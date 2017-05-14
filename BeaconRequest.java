package com.example.administrator.project207;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-19.
 */

public class BeaconRequest extends StringRequest {

    final static private String URL = "http://chlqkrtk2.iptime.org:3000/test";
    private Map<String, String> parameters;

    public BeaconRequest(String beacon_uuid, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userid", beacon_uuid);


    }
    @Override
    public Map<String, String> getParams()
    {
        return parameters;
    }

}