package com.example.naruto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.naruto.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class StartActivity extends AppCompatActivity {
    Button login;
    Button register;

    DatabaseReference reference;

    FirebaseUser firebaseUser;

    protected void onStart(){
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //redirect if user is not null
        if(firebaseUser != null){
          //  prueba();
            startActivity(new Intent(StartActivity.this,MainActivity.class));
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            }
        });
    }

    private void prueba(){

       // int numberInt = 2;
        for(int i = 2; i < 10;i++) {
            reference = FirebaseDatabase.getInstance().getReference().child("Cards").child(String.valueOf(i));

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("number", String.valueOf(i));
            hashMap.put("username", "");
            hashMap.put("category", "");
            hashMap.put("imageurl", "");
            reference.updateChildren(hashMap);
        }

    }
}