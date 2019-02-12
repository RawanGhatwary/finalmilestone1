package com.example.protocol1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText email;
    private EditText password;
    private Button buttonlogin;
    private Button buttonregister;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //finish();
            startActivity(new Intent(getApplicationContext(),signinactivity.class));

        }

        progressDialog=new ProgressDialog(this);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        buttonlogin=(Button) findViewById(R.id.buttonlogin);
        buttonregister= (Button) findViewById(R.id.buttonregister);


        buttonregister.setOnClickListener(this);
        buttonlogin.setOnClickListener(this);



    }

    private void registerUser(){
        String Email=email.getText().toString().trim();
        String Password=password.getText().toString().trim();
        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Please enter your E-mail", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Please enter a Password", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registration in progress.......");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signupActivity.this,"You registered successfully", Toast.LENGTH_LONG).show();



                }
                else {
                    Toast.makeText(signupActivity.this,"Registeration failed,try again", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();

            }
        });




    }
    @Override
    public void onClick(View view){
        if(view==buttonregister){
            registerUser();
        }

        if(view==buttonlogin){

            startActivity(new Intent(signupActivity.this,signinactivity.class));

        }



    }
}