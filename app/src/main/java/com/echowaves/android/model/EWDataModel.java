package com.echowaves.android.model;

import android.app.ProgressDialog;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

/**
 * copyright echowaves
 * Created by dmitry
 * on 6/18/14.
 */

public class EWDataModel {
    private static final String BASE_URL = "http://echowaves.com";
//    private static final String BASE_URL = "http://192.168.1.101:3000";
//    private static final String BASE_URL = "http://10.0.20.92:3000";


    protected final static AsyncHttpClient HTTP_CLIENT = new AsyncHttpClient();

    private static ProgressDialog progressDialog = null;

    static {
        PersistentCookieStore cookieStore = new PersistentCookieStore(ApplicationContextProvider.getContext());
        HTTP_CLIENT.setCookieStore(cookieStore);
    }

    public static void showLoadingIndicator(Context context) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, "", "Loading...", true);
        }
    }

    public static void hideLoadingIndicator() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

//    public static boolean isLoadingIndicatorShowing() {
//        if(progressDialog != null)
//            return true;
//        return false;
//    }

    protected static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
