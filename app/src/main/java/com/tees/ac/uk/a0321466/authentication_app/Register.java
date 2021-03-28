package com.tees.ac.uk.a0321466.authentication_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    //global defined view elements
    EditText et_name;
    EditText et_email;
    EditText et_password;
    EditText et_phone;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth= FirebaseAuth.getInstance();  //initalise firebase

        //get id of view components
        et_name= findViewById(R.id.et_name);
        et_email= findViewById(R.id.et_email);
        et_password= findViewById(R.id.et_password);
        et_phone= findViewById(R.id.et_phone);


        //check isUser already login or not
        if(fAuth.getCurrentUser()!=null){
            //go to MainActivity
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        //register button event listener //
        findViewById(R.id.btn_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start visible ProgressBar///
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                //get date from editText fields in the form of string
                String dName= et_name.getText().toString();
                String dEmail= et_email.getText().toString().trim();  //remove white space
                String dPassword= et_password.getText().toString().trim(); // remove white space
                String dPhone= et_phone.getText().toString();

                //add some validation on the input fields(email/password)
                if(TextUtils.isEmpty(dEmail))   //error if  email field is empty
                {
                    findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);  //hide progress Bar
                    et_email.setError("please enter vaild email ");
                    return;
                }
                if(TextUtils.isEmpty(dPassword)) {  //error if password field is empty
                    findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);  //hide progress Bar
                    et_password.setError("Please enter password ");
                    return;
                }

                else  if(dPassword.length()<6){   //error  password length is shorter than 6
                    findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);  //hide progress Bar
                    et_password.setError("enter password length greater than 6");
                    return;
                }
                //create firebase user if above validation is correct ..
                fAuth.createUserWithEmailAndPassword(dEmail,dPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //open MainActivity once user create account //
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();
                            //reset the all edit Text field
                            et_name.setText("");
                            et_email.setText("");
                            et_password.setText("");
                            et_phone.setText("");

                            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);  //hide progress Bar

                        }
                        else{
                            Toast.makeText(Register.this, "Error to create account",Toast.LENGTH_SHORT).show();
                            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);  //hide progress Bar
                        }

                    }
                });

            }
        });


        //login user event listener //text view / /
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //move to Login Activity Page
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

    }



}