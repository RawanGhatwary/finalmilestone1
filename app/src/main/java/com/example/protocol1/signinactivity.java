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

public class signinactivity extends AppCompatActivity implements View.OnClickListener {
    private EditText email1;
    private EditText password1;
    private Button signin1;
    private Button register1;
    private ProgressDialog progressDialog1;
    private FirebaseAuth firebaseAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinactivity);
        firebaseAuth1=FirebaseAuth.getInstance();

        if(firebaseAuth1.getCurrentUser()!=null){
            //finish();
            startActivity(new Intent(getApplicationContext(),listActivity.class));

        }

        progressDialog1=new ProgressDialog(this);
        signin1=(Button) findViewById(R.id.signin1);
        register1=(Button) findViewById(R.id.register1);
        password1=(EditText) findViewById(R.id.password1);
        email1=(EditText) findViewById(R.id.email);

        signin1.setOnClickListener(this);
        register1.setOnClickListener(this);





    }
    private void userlogin() {
        String email2= email1.getText().toString().trim();
        String password2=password1.getText().toString().trim();

        if(TextUtils.isEmpty(email2)){
            Toast.makeText(this,"Please enter your E-mail", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this,"Please enter a Password", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog1.setMessage("Login in progress.......");
        progressDialog1.show();
        firebaseAuth1.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog1.dismiss();
                if(task.isSuccessful()){
                    //finish();
                    startActivity(new Intent(getApplicationContext(),listActivity.class));
                } else{
                    Toast.makeText(signinactivity.this,"Login failed,try again", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v==signin1){
            userlogin();
        }
        if(v==register1){
            //finish();
            Intent intenttt=new Intent(signinactivity.this,signupActivity.class);
            startActivity(intenttt);
        }

    }


}