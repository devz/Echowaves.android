package com.echowaves.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button tuneInButton = (Button) findViewById(R.id.tuneIn);
        //Listening to button event
        tuneInButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent tuneIn = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(tuneIn);
            }
        });

        Button createWaveButton = (Button) findViewById(R.id.createWave);
        //Listening to button event
        createWaveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent createWave = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(createWave);
            }
        });
    }

}