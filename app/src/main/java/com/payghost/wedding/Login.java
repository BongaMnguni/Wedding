package com.payghost.wedding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.payghost.wedding.Admin.AdminDashboardActivity;

public class Login extends AppCompatActivity {

    EditText username,password;
    private String user,pass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnlogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                if(user.equalsIgnoreCase("admin@wedding") && pass.equalsIgnoreCase("adminxfeej$#")){
                    startActivity(new Intent(getApplicationContext(),AdminDashboardActivity.class));
                 }else {
                    Toast.makeText(getApplicationContext(),"Incorrect Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
