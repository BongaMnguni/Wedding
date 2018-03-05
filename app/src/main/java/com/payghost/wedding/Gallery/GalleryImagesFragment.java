package com.payghost.wedding.Gallery;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.payghost.wedding.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryImagesFragment extends Fragment {

    TextView tvcake,tvsuit,tvdress,tvpublic;

    LinearLayout layout;

    View view;
CircleImageView cakeImage,suitImage,dressImage;
ImageView publicimages;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.gallery_layout, container, false);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final Images mainfragment = new Images();

        layout = view.findViewById(R.id.layout);

        cakeImage = view.findViewById(R.id.gallery_cake);
        tvcake = view.findViewById(R.id.tvcake);
        suitImage = view.findViewById(R.id.gallery_suit);
        tvsuit = view.findViewById(R.id.tvsuit);
        dressImage = view.findViewById(R.id.gallery_dress);
        tvdress = view.findViewById(R.id.tvdress);
        publicimages = view.findViewById(R.id.gallery_public);
        tvpublic = view.findViewById(R.id.tvpublic);


        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"custom_font.ttf");
        tvcake.setTypeface(font);
        tvsuit.setTypeface(font);
        tvdress.setTypeface(font);
        tvpublic.setTypeface(font);

        cakeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("url","http://mydm.co.za/Wedding/Cakeretrieve.php");
                mainfragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.content, mainfragment).commit();

            }
        });

        suitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url","http://mydm.co.za/Wedding/Suitretrieve.php");
                mainfragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.content, mainfragment).commit();
            }
        });

        dressImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url","http://mydm.co.za/Wedding/Dressetrieve.php");
                mainfragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.content, mainfragment).commit();
            }
        });

        publicimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url","http://mydm.co.za/Wedding/Publicretrieve.php");
                mainfragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.content, mainfragment).commit();
            }
        });

        Animation upAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.simple_grow);
        upAnim.reset();
        layout.clearAnimation();
        layout.setAnimation(upAnim);

        return view;
    }
}
