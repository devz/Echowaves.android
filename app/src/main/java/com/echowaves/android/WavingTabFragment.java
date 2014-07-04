package com.echowaves.android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WavingTabFragment extends EWTabFragment {


    @Override
    public void onStart() {
        super.onStart();
        Log.d("!!!!!!!!!!!!!", "WavingTabFragment onStart()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_waving, container, false);
        Log.d("WavingTabFragment", view.toString());

        return view;
    }

    @Override
    public void updateWave(final String waveName) {
        super.updateWave(waveName);
        Log.d("&&&&&&&&&&&&&&&& WavingTabFragment updateWave", waveName);

        View view = getView();
        if (view != null) {
            Button editWaveButton = (Button) view.findViewById(R.id.waving_editWaveButton);
            editWaveButton.setText(waveName);
            //Listening to button event
            editWaveButton.
                    setOnClickListener(
                            new View.OnClickListener() {

                                public void onClick(final View v) {

//                                ((Button)getView().findViewById(R.id.waving_waveSelectedLabel)).setText(waveName);

////                        Intent tuneIn = new Intent(getApplicationContext(), NavigationTabBarActivity.class);
//                                Intent tuneIn = new Intent(getApplicationContext(), NavigationTabBarActivity.class);
//                                startActivity(tuneIn);
                                }
                            }
                    );
        }
    }
}
