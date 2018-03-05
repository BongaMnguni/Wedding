package com.payghost.wedding.Gallery;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.payghost.wedding.Config;
import com.payghost.wedding.R;
import com.payghost.wedding.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Images extends Fragment {

    private String JSON_STRING,URL_GET_GALLERY;
    private String image;

    RecyclerView recyclerView;
    GalleryAdapter galleryAdapter;
    final GalleryImagesFragment mainfragment = new GalleryImagesFragment();
    View view;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gallery_images, container, false);

        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.listGallerys);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;
        int columns = Math.round(dpWidth/300);

        if(columns == 1){
            columns = 2;
        }else {
            columns = Math.round(dpWidth/300);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),columns);
        recyclerView.setLayoutManager(layoutManager);

        URL_GET_GALLERY = getArguments().getString("url");

      getJSON(URL_GET_GALLERY);
        

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.findItem(R.id.action_back).setVisible(true);
        menu.findItem(R.id.action_register).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.action_back){

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            GalleryImagesFragment mainfragment = new GalleryImagesFragment();
            fragmentManager.beginTransaction().replace(R.id.content, mainfragment).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showGallery(){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        List<GalleryItems> arrList = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                image = jo.getString(Config.TAG_IMAGE);

                HashMap<String,String> Tenants = new HashMap<>();

                Tenants.put(Config.TAG_IMAGE,image);

                list.add(Tenants);

                arrList.add(new GalleryItems(image));
            }
            galleryAdapter = new GalleryAdapter(getActivity(),arrList);
            recyclerView.setAdapter(galleryAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJSON(final String urlz){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                loading = new ProgressDialog(getActivity());
                loading.setMessage("Fetching Images...");
                loading.setIndeterminate(false);
                loading.setCancelable(true);
                loading.show(); }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showGallery();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(urlz);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}
