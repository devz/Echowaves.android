package com.echowaves.android.model;

import com.echowaves.android.WavePickerFragment;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * copyright echowaves
 * Created by dmitry
 * on 6/18/14.
 */

public class EWBlend extends EWDataModel {
    public static void autoCompleteFor(String waveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("term", waveName);
        HTTP_CLIENT.get(getAbsoluteUrl("/autocomplete-wave-name.json"), params, responseHandler);
    }

    public static void requestBlendingWith(String waveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("wave_name", waveName);
        params.put("from_wave", WavePickerFragment.getCurrentWaveName());
        HTTP_CLIENT.post(getAbsoluteUrl("/request-blending.json"), params, responseHandler);
    }

}
