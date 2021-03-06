package com.echowaves.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.echowaves.android.model.EWBlend;
import com.echowaves.android.util.EWJsonHttpResponseHandler;
import com.echowaves.android.util.Utility;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlendsTabFragment extends EWTabFragment {

    private ArrayList<String> blendedWith;

    private ListView blendedWithListView;

    private View blendedWithHeader;


    private LayoutInflater layoutInflater;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("!!!!!!!!!!!!!", "BlendsTabFragment onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("!!!!!!!!!!!!!", "BlendsTabFragment onResume()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_blends, container, false);

        layoutInflater = inflater;

        Log.d("BlendsTabFragment", view.toString());

        blendedWithListView = (ListView) view.findViewById(R.id.blends_blendedWith);

        blendedWithHeader = layoutInflater.inflate(R.layout.header_blended_with, null);
        blendedWithListView.addHeaderView(blendedWithHeader);

        return view;
    }

    @Override
    public void updateWave(final String waveName) {
        super.updateWave(waveName);
        Log.d("BlendsTabFragment updateWave", waveName);

        EWBlend.getBlendedWith(new EWJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonResponseArray) {
                Log.d(">>>>>>>>>>>>>>>>>>>> ", jsonResponseArray.toString());

                blendedWith = new ArrayList<>(jsonResponseArray.length());
                for (int i = 0; i < jsonResponseArray.length(); i++) {
                    try {
                        Log.d("jsonObject::::::::::::::::::::::", jsonResponseArray.getJSONObject(i).toString());
                        Log.d("label:", jsonResponseArray.getJSONObject(i).getString("name"));
                        blendedWith.add(jsonResponseArray.getJSONObject(i).getString("name"));
                    } catch (JSONException e) {
                        Log.d("JSONException", e.toString(), e);
                    }
                }

                TextView headerText = (TextView) blendedWithHeader.findViewById(R.id.header_blendedWith_textView);
                headerText.setText(waveName + " BLENDS WITH: " + blendedWith.size());

                BlendedWithCustomAdapter defaultAdapter = new BlendedWithCustomAdapter(getActivity(), blendedWith);
                blendedWithListView.setAdapter(defaultAdapter);

                Utility.setListViewHeightBasedOnChildren(blendedWithListView);

            }

        });

    }


    /**
     * Static class used to avoid the calling of "findViewById" every time the getView() method is called,
     * because this can impact to your application performance when your list is too big. The class is static so it
     * cache all the things inside once it's created.
     */
    private static class ViewHolder {
        protected TextView itemName;
    }


    public class BlendedWithCustomAdapter extends BaseAdapter {
        private ArrayList<String> mListItems;
        private LayoutInflater mLayoutInflater;

        public BlendedWithCustomAdapter(Context context, ArrayList<String> arrayList) {

            mListItems = arrayList;

            //get the layout inflater
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            //getCount() represents how many items are in the list
            return mListItems.size();
        }

        @Override
        //get the data of an item from a specific position
        //i represents the position of the item in the list
        public Object getItem(int i) {
            return null;
        }

        @Override
        //get the position id of the item from the list
        public long getItemId(int i) {
            return 0;
        }

        @Override

        public View getView(int position, View view, ViewGroup viewGroup) {

            // create a ViewHolder reference
            ViewHolder holder;

            //check to see if the reused view is null or not, if is not null then reuse it
            if (view == null) {
                holder = new ViewHolder();

                view = mLayoutInflater.inflate(R.layout.row_blended_with, null);
                holder.itemName = (TextView) view.findViewById(R.id.row_blendedWith_textView);

                // the setTag is used to store the data within this view
                view.setTag(holder);
            } else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder) view.getTag();
            }

            //get the string item from the position "position" from array list to put it on the TextView
            final String waveName = mListItems.get(position);
            if (waveName != null) {
                if (holder.itemName != null) {
                    //set the item name on the TextView
                    holder.itemName.setText(waveName);
                }
            }


            Button deleteButton = (Button) view.findViewById(R.id.row_blendedWith_deleteButton);

//                Click listener for the searched item that was selected

            deleteButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(final View v) {
                    AlertDialog.Builder alertDialogConfirmWaveDeletion = new AlertDialog.Builder(
                            v.getContext());
                    alertDialogConfirmWaveDeletion.setTitle("Unblend Wave?");

                    // set dialog message
                    alertDialogConfirmWaveDeletion
                            .setMessage("Are you really sure you want to unblend? The " + waveName + "'s photos will be gone from your waveName " + WavePickerFragment.getCurrentWaveName() + " !")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    EWBlend.unblendFrom(waveName, WavePickerFragment.getCurrentWaveName(), new EWJsonHttpResponseHandler(v.getContext()) {

                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                                            Log.d(">>>>>>>>>>>>>>>>>>>> ", jsonResponse.toString());
                                            WavePickerFragment.resetCurrentWaveIndex();
                                            ((NavigationTabBarActivity) getActivity()).onAWaveSelected(WavePickerFragment.getCurrentWaveName());
                                        }


                                    });
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogConfirmWaveDeletion.create();

                    // show it
                    alertDialog.show();

                }
            });


            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View v) {
                    Intent acceptBlendingIntent = new Intent(v.getContext(), AcceptBlendingRequestActivity.class);

                    acceptBlendingIntent.putExtra("FROM_WAVE", waveName);
                    startActivity(acceptBlendingIntent);
                }
            });


            //this method must return the view corresponding to the data at the specified position.
            return view;

        }

    }

}
