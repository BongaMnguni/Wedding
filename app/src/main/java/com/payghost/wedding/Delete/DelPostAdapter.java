package com.payghost.wedding.Delete;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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
import com.payghost.wedding.Post.CoupleItems;
import com.payghost.wedding.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DelPostAdapter extends RecyclerView.Adapter<DelPostAdapter.PostviewHolder>{
    public List<CoupleItems> list ;
    private Context context;
    RequestQueue requestQueue;
    SharedPreferences prefs ;

    public DelPostAdapter(Context context, List<CoupleItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public PostviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.del_post_cardview,null);
        PostviewHolder viewHolder = new PostviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final PostviewHolder holder, final int position) {

        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        prefs = context.getSharedPreferences("Table",Context.MODE_PRIVATE);
        final String tbl = prefs.getString("table", null);

        Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
         holder.textViewdescription.setText(list.get(position).getDescription());

        Typeface font = Typeface.createFromAsset(context.getAssets(),"custom_font.ttf");

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
        holder.textViewdescription.setTypeface(font);

        Animation upAnim = AnimationUtils.loadAnimation(context, R.anim.translate);
        upAnim.reset();
        holder.itemView.clearAnimation();
        holder.itemView.setAnimation(upAnim);
        holder.itemView.setAnimation(upAnim);


    }
    @Override
    public int getItemCount() {
        return this.list.size();
    }


    public class PostviewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textViewdescription;
        FloatingActionButton delete;

        public  PostviewHolder(View v){
            super(v);
            image = itemView.findViewById(R.id.imgcouple);
            textViewdescription = itemView.findViewById(R.id.pdescription);
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

