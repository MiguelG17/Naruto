package com.example.naruto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class RandomActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        imageView = findViewById(R.id.image_card);
        int number = 0;

      number = getIntent().getIntExtra("number",number);

      if(number == 1){

      }

      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });
       // randomCards(number);
    }

    private void firstCard(){

    }

    private void randomCards(int number){
        switch (number){
            case 1:

                break;
        }
    }
}