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
         //   prueba();
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

        String number ="1";

        reference = FirebaseDatabase.getInstance().getReference().child("Cards").child(number);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("number",number);
        hashMap.put("username","sasuke");
        hashMap.put("category","normal");
        hashMap.put("imageurl","https://firebasestorage.googleapis.com/v0/b/naruto-database-89a36.appspot.com/o/Sasuke.png?alt=media&token=5d5ca06c-41f1-4db8-af28-2fc7a946f93d");


        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Exception e = task.getException();

//                    Log.e("TAG", e.getMessage());
                    Intent intent = new Intent();
                 //   Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }else{

                    Exception e = task.getException();

                    Log.e("TAG", e.getMessage());
                }
            }
        });
    }
}