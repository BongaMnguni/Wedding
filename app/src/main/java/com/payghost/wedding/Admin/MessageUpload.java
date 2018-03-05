package com.payghost.wedding.Admin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.payghost.wedding.Config;
import com.payghost.wedding.R;

import java.util.HashMap;
import java.util.Map;

public class MessageUpload extends Fragment {
    RequestQueue requestQueue;
    EditText subject,message;
    Button SendMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_upload, container, false);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        subject = view.findViewById(R.id.notsubject);
        message = view.findViewById(R.id.notmessage);

        SendMessage = view.findViewById(R.id.notbtncont);
       SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

       /* LinearLayout layout = view.findViewById(R.id.messageLayout);
        Animation upAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
        upAnim.reset();
        upAnim.setDuration(2000);
        layout.clearAnimation();
        layout.setAnimation(upAnim);
*/

        return view;
    }

    public void sendMessage(){


        if(!message.getText().toString().isEmpty() || !subject.getText().toString().isEmpty()){
            StringRequest request = new StringRequest(Request.Method.POST, Config.URL_INSERT_ALL_MESSAGES, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    System.out.println(response.toString());
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parameters = new HashMap<String, String>();

                    parameters.put("subject", subject.getText().toString());
                    parameters.put("message", message.getText().toString());

                    return parameters;
                }
            };
            requestQueue.add(request);
            Toast.makeText(getActivity(), " message sent...", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getActivity(), "All Fields are Required...", Toast.LENGTH_LONG).show();
        }

    }

}


