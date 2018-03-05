package com.payghost.wedding.Delete;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payghost.wedding.R;

public class Delete extends Fragment {
    SharedPreferences sharedpreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View roortview = inflater.inflate(R.layout.fragment_delete, container, false);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        final DisplayDelete mainfragment = new DisplayDelete();
        sharedpreferences = getActivity().getSharedPreferences("Packages", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        roortview.findViewById(R.id.del_cake).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("fragment","cake");
                bundle.putString("table","cakeImages");
                bundle.putString("url","http://mydm.co.za/Wedding/Cakeretrieve.php");

                mainfragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.del_content, mainfragment).commit();
                roortview.findViewById(R.id.del_layout).setVisibility(View.GONE);

            }
        });

        roortview.findViewById(R.id.del_suit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("fragment","suit");
                bundle.putString("table","suitImages");
                bundle.putString("url","http://mydm.co.za/Wedding/Suitretrieve.php");

                mainfragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.del_content, mainfragment).commit();
                roortview.findViewById(R.id.del_layout).setVisibility(View.GONE);

            }
        });roortview.findViewById(R.id.del_dress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("fragment","dress");
                bundle.putString("table","dressImages");
                bundle.putString("url","http://mydm.co.za/Wedding/Dressetrieve.php");

                mainfragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.del_content, mainfragment).commit();
                roortview.findViewById(R.id.del_layout).setVisibility(View.GONE);


            }
        });roortview.findViewById(R.id.del_general).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("fragment","general");
                bundle.putString("table","publicImages");
                bundle.putString("url","http://mydm.co.za/Wedding/Publicretrieve.php");

                mainfragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.del_content, mainfragment).commit();
                roortview.findViewById(R.id.del_layout).setVisibility(View.GONE);


            }
        });roortview.findViewById(R.id.del_couple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("fragment","couple");
                bundle.putString("table","couplesImages");
                bundle.putString("url","http://mydm.co.za/Wedding/postretrieve.php");

                mainfragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.del_content, mainfragment).commit();
                roortview.findViewById(R.id.del_layout).setVisibility(View.GONE);

            }
        });roortview.findViewById(R.id.del_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.apply();

                Bundle bundle = new Bundle();
                bundle.putString("fragment","message");
                bundle.putString("table","messageTable");
                bundle.putString("url","http://mydm.co.za/Wedding/RetrieveMessages.php");

                mainfragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.del_content, mainfragment).commit();
                roortview.findViewById(R.id.del_layout).setVisibility(View.GONE);

            }
        });
        return roortview;
    }
}
