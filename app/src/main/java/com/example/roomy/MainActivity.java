package com.example.roomy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button loginButton = (Button)findViewById(R.id.loginButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action listner for button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login attributes
                EditText username = (EditText) findViewById(R.id.emailAddressInput);
                EditText password = (EditText) findViewById(R.id.passwordInput);


                //Stirngs
                String userString = username.getText().toString();
                String passString = password.getText().toString();


                //call method

            }
        });
    }
}