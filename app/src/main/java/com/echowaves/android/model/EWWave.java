package com.echowaves.android.model;

import android.content.SharedPreferences;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * copyright echowaves
 * Created by dmitry
 * on 6/18/14.
 */

public class EWWave extends EWDataModel {
    static final private SharedPreferences prefs = new SecurePreferences(ApplicationContextProvider.getContext());

    public static void storeCredentialForWave(String waveName, String wavePassword) {
        prefs.edit().putString(ApplicationContextProvider.LOGIN_KEY, waveName).commit();
        prefs.edit().putString(ApplicationContextProvider.PASS_KEY, wavePassword).commit();
    }

    public static void storeDeviceToken(String token) {
        prefs.edit().putString(ApplicationContextProvider.DEVICE_TOKEN, token).commit();
    }

    public static void storeAppVersion(Integer appVersion) {
        prefs.edit().putInt(ApplicationContextProvider.PROPERTY_APP_VERSION, appVersion).commit();
    }

    public static String getStoredDeviceToken() {
        return prefs.getString(ApplicationContextProvider.DEVICE_TOKEN, "");
    }


    public static Integer getStoredAppVersion() {
        return prefs.getInt(ApplicationContextProvider.PROPERTY_APP_VERSION, Integer.MIN_VALUE);
    }

    public static String getStoredWaveName() {
        return prefs.getString(ApplicationContextProvider.LOGIN_KEY, "");
    }

    public static String getStoredWavePassword() {
        return prefs.getString(ApplicationContextProvider.PASS_KEY, "");
    }

    public static void createWaveWithName(String waveName,
                                          String wavePassword,
                                          String confirmPassword,
                                          AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("name", waveName);
        params.put("pass", wavePassword);
        params.put("pass1", confirmPassword);
        HTTP_CLIENT.post(getAbsoluteUrl("/register.json"), params, responseHandler);
    }

    public static void createChildWaveWithName(String waveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("name", waveName);
        HTTP_CLIENT.post(getAbsoluteUrl("/create-child-wave.json"), params, responseHandler);
    }

    public static void makeWaveActive(String waveName, boolean active, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("wave_name", waveName);
        params.put("active", active ? "1" : "0");
        HTTP_CLIENT.post(getAbsoluteUrl("/make-wave-active.json"), params, responseHandler);
    }

    public static void getWaveDetails(String waveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("wave_name", waveName);
        HTTP_CLIENT.get(getAbsoluteUrl("/wave-details.json"), params, responseHandler);
    }


    public static void deleteChildWave(String waveName, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("wave_name", waveName);
        HTTP_CLIENT.post(getAbsoluteUrl("/delete-child-wave.json"), params, responseHandler);
    }


    synchronized public static void getAllMyWaves(AsyncHttpResponseHandler responseHandler) {
        HTTP_CLIENT.get(getAbsoluteUrl("/all-my-waves.json"), new RequestParams(), responseHandler);
    }

    public static void storeAndroidTokenForWave(String waveName, String token, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("name", waveName);
        params.put("token", token);
        HTTP_CLIENT.post(getAbsoluteUrl("/register-android-token.json"), params, responseHandler);
    }


    public static void sendPushNotifyBadge(Integer numberOfImages, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("badge", numberOfImages.toString());
        HTTP_CLIENT.post(getAbsoluteUrl("/push-notify.json"), params, responseHandler);
    }


    public static void tuneInWithNameAndPassword(String waveName,
                                                 String wavePassword,
                                                 AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("name", waveName);
        params.put("pass", wavePassword);
        HTTP_CLIENT.post(getAbsoluteUrl("/login.json"), params, responseHandler);
    }

    public static void tuneOut(AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        HTTP_CLIENT.post(getAbsoluteUrl("/logout.json"), params, responseHandler);
    }


}
