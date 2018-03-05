package com.payghost.wedding.Post;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.payghost.wedding.Gallery.GeneralImageUpload;
import com.payghost.wedding.Login;
import com.payghost.wedding.MainActivity;
import com.payghost.wedding.R;
import com.payghost.wedding.UserRegister;
import com.squareup.picasso.Picasso;

/**
 * Created by Payghost on 3/5/2018. wedding
 */

public class More extends AppCompatActivity {
ImageView image;
TextView textView;
String caption,imageurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_more);

        image = findViewById(R.id.imgcouple);
        textView = findViewById(R.id.pdescription);
        Typeface font = Typeface.createFromAsset(getAssets(),"custom_font.ttf");
        textView.setTypeface(font);

        Bundle b = getIntent().getExtras();
        caption = b != null ? b.getString("caption") : null;
        imageurl = b != null ? b.getString("imageurl") : null;

        textView.setText(caption);
        Picasso.with(getApplicationContext()).load(imageurl).into(image);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.action_back){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
