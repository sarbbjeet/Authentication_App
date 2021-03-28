package com.tees.ac.uk.a0321466.authentication_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //logout event listener
      findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              //firebase signOut process call
          FirebaseAuth.getInstance().signOut();

              //move to register page/ Activity
              startActivity(new Intent(getApplicationContext(),Register.class));
              finish();

          }
      });
        }

    }
