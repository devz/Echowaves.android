package com.echowaves.android;

import android.os.Bundle;

import com.echowaves.android.model.EWBlend;

public class BlendsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blends);

        EWBlend.debug();
    }

}
