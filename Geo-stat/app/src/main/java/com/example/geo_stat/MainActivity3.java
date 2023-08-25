package com.example.geo_stat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signedupRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        loginUsername= findViewById(R.id.login_username);
        loginPassword= findViewById(R.id.login_password);
        signedupRedirectText= findViewById(R.id.signupRedirectText);
        loginButton= findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUsername()|!validatePassword()){

                }else{
                    checkUser();
                }
            }
        });
        signedupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }


    public boolean validateUsername(){
        String val = loginUsername.getText().toString();
        if(val.isEmpty()){
            loginUsername.setError("Username cannot be empty");
            return false;
        }
        else{
            loginUsername.setError(null);
            return true;
        }
    }
    public boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if(val.isEmpty()){
            loginPassword.setError("Password cannot be empty");
            return false;
        }
        else{
            loginPassword.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUsername= loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if(!Objects.equals(passwordFromDB,userPassword)){
                        loginUsername.setError(null);
                        Intent intent =new Intent(MainActivity3.this, MainActivity5.class);
                        startActivity(intent);
                    }else{
                        loginPassword.setError("Invalid credentials");
                        loginPassword.requestFocus();

                    }
                }else{
                    loginPassword.setError("User does not exist");
                    loginUsername.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}