package com.payghost.wedding;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.payghost.wedding.Gallery.GeneralImageUpload;
import com.payghost.wedding.Post.AddPost;
import com.payghost.wedding.Post.PostFragment;
import com.payghost.wedding.Gallery.GalleryImagesFragment;
import com.payghost.wedding.Messages.RetrieveNotificationFragment;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();
    AlertDialog dialog1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.content,new PostFragment()).commit();

                    return true;
                case R.id.navigation_dashboard:
                    fragmentManager.beginTransaction().replace(R.id.content,new GalleryImagesFragment()).commit();

                    return true;
                case R.id.navigation_notifications:
                    fragmentManager.beginTransaction().replace(R.id.content,new RetrieveNotificationFragment()).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager.beginTransaction().replace(R.id.content,new PostFragment()).commit();


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);

        menu.findItem(R.id.action_back).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dashboard_dialog, null);
            mBuilder.setView(mView);
            dialog1 = mBuilder.create();

            // dashboard Layout
            mView.findViewById(R.id.dialog_dashboard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
            });

            // General images Layout
            mView.findViewById(R.id.dialog_gen_images).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(getApplicationContext(),GeneralImageUpload.class));
                }
            });

            // couples Layout
            mView.findViewById(R.id.dialog_couples).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),AddPost.class));
                }
            });

            dialog1.setCancelable(true);
            dialog1.show();

            return true;
        }
        if(id == R.id.action_register){
            startActivity(new Intent(getApplicationContext(),UserRegister.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
