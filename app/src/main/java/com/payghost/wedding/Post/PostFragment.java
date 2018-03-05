package com.payghost.wedding.Post;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.payghost.wedding.Config;
import com.payghost.wedding.R;
import com.payghost.wedding.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.payghost.wedding.R.layout.fragment_post;

public class PostFragment extends Fragment {
    private String JSON_STRING;
    private String image,male,female,description;
    PostAdapter galleryAdapter;
    RecyclerView  recyclerView;
    LinearLayoutManager linearlayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(fragment_post, container, false);

        recyclerView = view.findViewById(R.id.postrecyclerview);
        linearlayout = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);

        getJSON();

        return view;
    }
    private void showCouples(){

        JSONObject jsonObject = null;
        List<CoupleItems> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                image = jo.getString(Config.TAG_IMAGE);
                description = jo.getString(Config.TAG_DESCRIPTION);

                arrList.add(new  CoupleItems(image,description));
            }
            galleryAdapter = new PostAdapter(getActivity(),arrList);
            recyclerView.setAdapter(galleryAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJSON(){

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
                String s = rh.sendGetRequest(Config.URL_GET_ALL_COUPLES);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
