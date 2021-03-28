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

public class Login extends AppCompatActivity {

    //global defined
    EditText et_email;
    EditText et_password;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //get id of view components
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);

        //initialise firebase
        fAuth= FirebaseAuth.getInstance();

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Start visible ProgressBar///
                findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

                //get date from editText fields in the form of string
                String dEmail= et_email.getText().toString().trim();  //remove white space
                String dPassword= et_password.getText().toString().trim(); // remove white space


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

                //start login process using firebase function ..
                fAuth.signInWithEmailAndPassword(dEmail,dPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Hide ProgressBar///
                        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                        //login Successfully..
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                        //reset edit text fields..
                        et_password.setText("");
                        et_email.setText("");

                    }
                    else{
                        Toast.makeText(Login.this,"wrong username or password", Toast.LENGTH_SHORT).show();

                        //Hide ProgressBar///
                        findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
                    }
                    }
                });


            }
        });

        //create new user account // event listener
        findViewById(R.id.tv_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(getApplicationContext(),Register.class));
              finish();
            }
        });
    }
}