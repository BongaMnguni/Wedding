package com.payghost.wedding.Gallery;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.payghost.wedding.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 21428 on 10/18/2017.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryviewHolder>{

    public List<GalleryItems> list ;
    private Context context;

    public GalleryAdapter(Context context, List<GalleryItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public GalleryviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_cardview,null);
        GalleryviewHolder viewHolder = new GalleryviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final GalleryviewHolder holder, final int position) {

        Picasso.with(context).load(list.get(position).getImage()).into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.image_layout);
                dialog.setCancelable(true);

                ImageView image = dialog.findViewById(R.id.img_full);
                Picasso.with(context).load(list.get(position).getImage()).into(image);

                dialog.show();

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
    class GalleryviewHolder extends RecyclerView.ViewHolder {

        public ImageView image;


        public GalleryviewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.gallery_image);



        }
    }

}
