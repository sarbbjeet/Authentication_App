package com.tees.ac.uk.a0321466.authentication_app;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

class eventHandler {
    Context mContext;
    private FirebaseAuth mAuth;
    public eventHandler(Context mContext) {
        this.mContext=mContext;
        mAuth = FirebaseAuth.getInstance();
    }

    public void register(String name, String email, String password, String phone, ProgressBar progressBar){
         if(TextUtils.isEmpty(email)  || TextUtils.isEmpty(password) ) {
             Toast.makeText(mContext,"error to create user6",Toast.LENGTH_SHORT).show();
         }
         else {

             //register the user in firebase
             mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         Toast.makeText(mContext,"Successfully created account" + name,Toast.LENGTH_SHORT).show();

                     } else {
                         Toast.makeText(mContext,"error to create user",Toast.LENGTH_SHORT).show();

                     }
                 }
             });
         }
//        if(mAuth.getCurrentUser() ==null){
//
//            Toast.makeText(mContext,"name is " + name,Toast.LENGTH_SHORT).show();
//        }
//        else{
//            progressBar.setVisibility(View.VISIBLE);
//        }


    }
}
