package com.example.android.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout mNameTextInputLayout;
    EditText mNameEditTextView;
    TextInputLayout mEmailTextInputLayout;
    EditText mEmailEditTextView;
    TextInputLayout mPasswordTextInputLayout;
    EditText mPasswordEditTextView;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    AppCompatButton mAppCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();
        mNameTextInputLayout=(TextInputLayout)findViewById(R.id.nameTextInputLayout);
        mNameEditTextView=(EditText)findViewById(R.id.nameEditTextView);

        mEmailTextInputLayout=(TextInputLayout)findViewById(R.id.emailTextInputLayout);
        mEmailEditTextView=(EditText)findViewById(R.id.emailEditTextView);

        mPasswordTextInputLayout=(TextInputLayout)findViewById(R.id.passwordTextInputLayout);
        mPasswordEditTextView=(EditText)findViewById(R.id.passwordEditTextView);
   mAppCompatButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String email=mEmailEditTextView.getText().toString().trim();
           String password=mPasswordEditTextView.getText().toString().trim();
           mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(SignUpActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();

               }
           }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()) {
                       startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                   } else {
                       Toast.makeText(SignUpActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                   }
               }
               });
       }
   });
    }
}
