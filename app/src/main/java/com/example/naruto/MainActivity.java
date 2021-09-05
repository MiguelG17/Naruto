package com.example.naruto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.naruto.Fragment.AlbumFragment;
import com.example.naruto.Fragment.ChestFragment;
import com.example.naruto.Fragment.ShopFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = null;

    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new AlbumFragment()).commit(); //Se inicia en el fragment que se ponga
        /////////

    /*    String number ="1";

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

                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                }else{

                    Exception e = task.getException();

                    Log.e("TAG", e.getMessage());
                }
            }
        });*/
        /////////////

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()){

                        case R.id.album:
                            selectedFragment = new AlbumFragment();
                            break;

                        case R.id.chest:
                            selectedFragment = new ChestFragment();
                            break;

                        case R.id.shop:
                        selectedFragment = new ShopFragment();
                            break;

                    }

                    if(selectedFragment != null){ //Creo que aquí se puede iniciar el selector donde se quiera
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    }
                    return true;
                }
            };
}