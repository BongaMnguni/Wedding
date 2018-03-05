package com.payghost.wedding.Post;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.payghost.wedding.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostviewHolder>{
    public List<CoupleItems> list ;
    private Context context;


    public PostAdapter(Context context, List<CoupleItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public PostviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cardview,null);
        PostviewHolder viewHolder = new PostviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final PostviewHolder holder, final int position) {


        Picasso.with(context).load(list.get(position).getImage()).into(holder.image);
         holder.textViewdescription.setText(list.get(position).getDescription());

        holder.textViewdescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textViewdescription.setSingleLine(false);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,More.class);
                i.putExtra("imageurl",list.get(position).getImage());
                i.putExtra("caption",list.get(position).getDescription());
                context.startActivity(i);

                //Toast.makeText(context, "Awe", Toast.LENGTH_SHORT).show();
            }
        });

        Typeface font = Typeface.createFromAsset(context.getAssets(),"custom_font.ttf");
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

        public  PostviewHolder(View v){
            super(v);
            image = itemView.findViewById(R.id.imgcouple);
            textViewdescription = itemView.findViewById(R.id.pdescription);

        }
    }

    private void shareImage(Uri path){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM,path);
        context.startActivity(Intent.createChooser(shareIntent,"Share image using"));
    }
}

