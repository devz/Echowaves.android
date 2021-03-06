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
//    public static void autoCompleteFor(String waveName, AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("term", waveName);
//        HTTP_CLIENT.get(getAbsoluteUrl("/autocomplete-wave-name.json"), params, responseHandler);
//    }
//
//    public static void requestBlendingWith(String waveName, String fromWaveName, AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("wave_name", waveName);
//        params.put("from_wave", fromWaveName);
//        HTTP_CLIENT.post(getAbsoluteUrl("/request-blending.json"), params, responseHandler);
//    }
//
//
//    public static void getRequestedBlends(AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("wave_name", WavePickerFragment.getCurrentWaveName());
//        HTTP_CLIENT.get(getAbsoluteUrl("/requested-blends.json"), params, responseHandler);
//    }
//
//    public static void getUnconfirmedBlends(AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("wave_name", WavePickerFragment.getCurrentWaveName());
//        HTTP_CLIENT.get(getAbsoluteUrl("/unconfirmed-blends.json"), params, responseHandler);
//    }

    public static void getBlendedWith(AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("wave_name", WavePickerFragment.getCurrentWaveName());
        HTTP_CLIENT.get(getAbsoluteUrl("/blended-with.json"), params, responseHandler);
    }


//    public static void confirmBlendingWith(String waveName, String fromWaveName, AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("wave_name", waveName);
//        params.put("from_wave", fromWaveName);
//        HTTP_CLIENT.post(getAbsoluteUrl("/confirm-blending.json"), params, responseHandler);
//    }

    public static void unblendFrom(String waveName, String fromWaveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("wave_name", waveName);
        params.put("from_wave", fromWaveName);
        HTTP_CLIENT.post(getAbsoluteUrl("/unblend.json"), params, responseHandler);
    }


    public static void acceptBlending(String origMyWaveName, String myWaveName, String frindWaveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("orig_my_wave_name", origMyWaveName);
        params.put("my_wave_name", myWaveName);
        params.put("friend_wave_name", frindWaveName);
        HTTP_CLIENT.post(getAbsoluteUrl("/accept_blending.json"), params, responseHandler);
    }

}
