package com.payghost.wedding.Delete;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.payghost.wedding.Config;
import com.payghost.wedding.Messages.messageItems;
import com.payghost.wedding.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Payghost on 2/22/2018.
 */


public class DelMessageAdapter extends RecyclerView.Adapter<DelMessageAdapter.messageViewHolder>{

    public List<messageItems> list ;
    private Context context;
    RequestQueue requestQueue;
    SharedPreferences prefs ;


    public DelMessageAdapter(Context context, List<messageItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public messageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.del_list_row_messages,null);
        messageViewHolder viewHolder = new messageViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final messageViewHolder holder, final int position) {
        holder.textViewDate.setText(list.get(position).getTime());
        holder.textViewMessage.setText(list.get(position).getMessage());
        holder.textViewSubject.setText(list.get(position).getSubject());

        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        prefs = context.getSharedPreferences("Table",Context.MODE_PRIVATE);
        final String tbl = prefs.getString("table", null);

        holder.textViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textViewMessage.setSingleLine(false);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage(Html.fromHtml("<font color='#627984'>Proceed with deleting ?</font>"))
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sendValues(tbl,list.get(position).getId());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Animation upAnim = AnimationUtils.loadAnimation(context,R.anim.translate);
        upAnim.reset();
        holder.itemView.clearAnimation();
        holder.itemView.setAnimation(upAnim);

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class messageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewSubject;
        public TextView textViewDate;
        public TextView textViewMessage;
        FloatingActionButton delete;

        public messageViewHolder(View itemView) {
            super(itemView);

           textViewSubject = itemView.findViewById(R.id.messagesubject);
           textViewMessage =  itemView.findViewById(R.id.displayMessage);
           textViewDate = itemView.findViewById(R.id.messageTime);
            delete = itemView.findViewById(R.id.fab_delete);

        }
    }

    public void sendValues(final String table, final String id){

        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_DELETE, new Response.Listener<String>() {
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

                parameters.put("table", table);
                parameters.put("id", id);

                return parameters;
            }
        };
        requestQueue.add(request);
        Toast.makeText(context, " deleted...", Toast.LENGTH_LONG).show();
    }
}
