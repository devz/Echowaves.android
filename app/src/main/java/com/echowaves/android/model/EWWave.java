package com.echowaves.android.model;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * copyright echowaves
 * Created by dmitry
 * on 6/18/14.
 */

public class EWWave extends EWDataModel {

    public static void tuneInWithNameAndPassword(String waveName,
                                                 String wavePassword,
                                                 AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("name", waveName);
        params.put("pass", wavePassword);
        client.post(getAbsoluteUrl("/login.json"), params, responseHandler);
    }

}
