package com.payghost.wedding.Messages;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.payghost.wedding.R;
import java.util.List;


/**
 * Created by Payghost on 2/22/2018.
 */


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.messageViewHolder>{

    public List<messageItems> list ;
    private Context context;


    public MessageAdapter(Context context, List<messageItems> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public messageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_messages,null);
        messageViewHolder viewHolder = new messageViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final messageViewHolder holder, int position) {
        holder.textViewDate.setText(list.get(position).getTime());
        holder.textViewMessage.setText(list.get(position).getMessage());
        holder.textViewSubject.setText(list.get(position).getSubject());

        holder.textViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.textViewMessage.setSingleLine(false);
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

        public messageViewHolder(View itemView) {
            super(itemView);

           textViewSubject = itemView.findViewById(R.id.messagesubject);
           textViewMessage =  itemView.findViewById(R.id.displayMessage);
           textViewDate = itemView.findViewById(R.id.messageTime);


        }
    }
}
