package com.payghost.wedding.Messages;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payghost.wedding.Config;
import com.payghost.wedding.Messages.MessageAdapter;
import com.payghost.wedding.Messages.messageItems;
import com.payghost.wedding.R;
import com.payghost.wedding.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class RetrieveNotificationFragment extends Fragment {

    private String JSON_STRING;
    String message,time,subject;

    LinearLayoutManager linearlayout;
    RecyclerView recyclerView;
    MessageAdapter messageViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retrieve_notification, container, false);
        recyclerView = view.findViewById(R.id.listMessage);

        linearlayout = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);

        getJSON();

        return view;
    }

    private void showMessages(){

        JSONObject jsonObject = null;
        List<messageItems> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                subject = jo.getString(Config.TAG_MESSAGE_SUBJECT);
                message = jo.getString(Config.TAG_MESSAGE_DISPLAY);
                time = jo.getString(Config.TAG_MESSAGE_TIME);

                arrList.add(new messageItems(subject,message,time));

            }
            messageViewAdapter = new MessageAdapter(getActivity().getApplicationContext(),arrList);
            recyclerView.setAdapter(messageViewAdapter);

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
                String s = rh.sendGetRequest(Config.URL_GET_ALL_MESSAGES);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
