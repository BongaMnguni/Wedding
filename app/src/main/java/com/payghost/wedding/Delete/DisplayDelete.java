package com.payghost.wedding.Delete;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.payghost.wedding.Config;
import com.payghost.wedding.Gallery.GalleryItems;
import com.payghost.wedding.Messages.MessageAdapter;
import com.payghost.wedding.Messages.messageItems;
import com.payghost.wedding.Post.CoupleItems;
import com.payghost.wedding.Post.PostAdapter;
import com.payghost.wedding.R;
import com.payghost.wedding.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayDelete extends Fragment {

    private String JSON_STRING,URL_GET_GALLERY;
    private String image,id,table,subject,message,time,description;

    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    DelGalleryAdapter galleryAdapter;
    DelMessageAdapter messageViewAdapter;
    DelPostAdapter postAdapter;
    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootview = inflater.inflate(R.layout.fragment_display_delete, container, false);

        sharedpreferences = getActivity().getSharedPreferences("Table", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        table = getArguments().getString("table");
        editor.putString("table",table);
        editor.commit();

        recyclerView = rootview.findViewById(R.id.listGallerys);
        URL_GET_GALLERY = getArguments().getString("url");
        String frag = getArguments().getString("fragment");

        switch (frag){
            case "cake":
                getJSON(URL_GET_GALLERY);
                break;
            case "suit":
                getJSON(URL_GET_GALLERY);
                break;
            case "dress":
                getJSON(URL_GET_GALLERY);
                break;
             case "general":
                getJSON(URL_GET_GALLERY);
                break;
            case "message":
                getMessageJSON(URL_GET_GALLERY);
                break;
            case "couple":
                getCoupleJSON(URL_GET_GALLERY);
                break;

        }

        return rootview;
    }

    private void showGallery(){

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/300);

        if(columns == 1){
            columns = 2;
        }else {
            columns = Math.round(dpWidth/300);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),columns);
        recyclerView.setLayoutManager(layoutManager);


        JSONObject jsonObject = null;
        List<GalleryItems> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                id = jo.getString(Config.TAG_ID);
                image = jo.getString(Config.TAG_IMAGE);

                arrList.add(new GalleryItems(image,id));
            }
            galleryAdapter = new DelGalleryAdapter(getActivity(),arrList);
            recyclerView.setAdapter(galleryAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJSON(final String urlz){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(getActivity());
                loading.setMessage("Fetching Images...");
                loading.setIndeterminate(false);
                loading.setCancelable(true);
                loading.show(); }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showGallery();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(urlz);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    ////////////////////////// messages ////////////////
    private void showMessages(){


        linearlayout = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);


        JSONObject jsonObject = null;
        List<messageItems> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                id = jo.getString(Config.TAG_ID);
                subject = jo.getString(Config.TAG_MESSAGE_SUBJECT);
                message = jo.getString(Config.TAG_MESSAGE_DISPLAY);
                time = jo.getString(Config.TAG_MESSAGE_TIME);

                arrList.add(new messageItems(subject,message,time,id));

            }
            messageViewAdapter = new DelMessageAdapter(getActivity(),arrList);
            recyclerView.setAdapter(messageViewAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getMessageJSON(final String link){

        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(getActivity());
                loading.setMessage("Fetching Messages...");
                loading.setIndeterminate(false);
                loading.setCancelable(true);
                loading.show();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMessages();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(link);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    /////////////////////////// displaying couple /////////////////

    private void showCouples(){

        linearlayout = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);


        JSONObject jsonObject = null;
        List<CoupleItems> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                id = jo.getString(Config.TAG_ID);
                image = jo.getString(Config.TAG_IMAGE);
                description = jo.getString(Config.TAG_DESCRIPTION);

                arrList.add(new  CoupleItems(id,image,description));
            }
            postAdapter = new DelPostAdapter(getActivity(),arrList);
            recyclerView.setAdapter(postAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getCoupleJSON(final String link){

        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(getActivity());
                loading.setMessage("Loading...");
                loading.setIndeterminate(false);
                loading.setCancelable(true);
                loading.show();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showCouples();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(link);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
