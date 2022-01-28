package com.example.naruto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.naruto.Adapter.AlbumAdapter;
import com.example.naruto.Model.Cards;
import com.example.naruto.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RandomActivity extends AppCompatActivity {
    ImageView imageView;


    ArrayList<Cards> normalCards = new ArrayList<>();
    ArrayList<Cards> specialCards = new ArrayList<>();
    ArrayList<Cards> epicCards = new ArrayList<>();
    ArrayList<Cards> legendaryCards = new ArrayList<>();

    int number = 0;
    int touch = 10;
    // System.out.println("Holaaaaaa");

    //number = getIntent().getIntExtra("number",number);



    FirebaseUser firebaseUser;

    ArrayList<String> nameCards = new ArrayList<>();
    List<Cards> cardsList;
    String listUser;
    int coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        imageView = findViewById(R.id.image_card);

        checkCards();




        //   int number = 0;
        // System.out.println("Holaaaaaa");

        number = getIntent().getIntExtra("number",number);

        System.out.println("Repito "+number);



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cards");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cards cards = snapshot.getValue(Cards.class);
                    if(cards.getCategory().equals("comun")){
                        normalCards.add(cards);
                    }
                    if(cards.getCategory().equals("especial")){
                        specialCards.add(cards);
                    }
                    if(cards.getCategory().equals("epica")){
                        epicCards.add(cards);
                    }
                    if(cards.getCategory().equals("legendaria")){
                        legendaryCards.add(cards);
                    }

                }
            //    int number = 0;
                // System.out.println("Holaaaaaa");

                //number = getIntent().getIntExtra("number",number);
             //   imprimir();
                randomCards(number);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



       // myCards();



      imageView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
               // imprimir();
              randomCards(1);
          }
      });
       // randomCards(number);
    }

   /* private void firstCard(){
        randomComun();
    }*/



    private void randomCards(int number){
        switch (number){
            case 1:

                randomComun();

                break;
        }
    }

    private void randomComun(){
    int numero;
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
    HashMap<String, Object> hashMap = new HashMap<>();
        numero = (int) (Math.random() * 1000);
        if(numero >= 0 && numero < 800){
            numero = (int) (Math.random() * normalCards.size());
            if(nameCards.contains(normalCards.get(numero).getUsername())){

                coins = coins + 50;
                hashMap.put("coin",String.valueOf(coins) );
                reference.updateChildren(hashMap);
                checkCards();
                Picasso.with(this)
                        .load(normalCards.get(numero).getImageurl())
                        .error(R.drawable.blanco) //Cambiar por imagen defecto
                        .fit()
                        .centerInside()
                        .into(imageView);
               // randomComun();
                normalCards.remove(numero);
            }else{
                Picasso.with(this)
                        .load(normalCards.get(numero).getImageurl())
                        .error(R.drawable.ic_book) //Cambiar por imagen defecto
                        .fit()
                        .centerInside()
                        .into(imageView);

                hashMap.put("list",listUser+","+normalCards.get(numero).getUsername() );
                reference.updateChildren(hashMap);
                checkCards();
              //  imageView.setImageIcon(normalCards.get(numero).getImageurl());
                normalCards.remove(numero);
            }

        }else if(numero > 800 && numero <= 900){
            numero = (int) (Math.random() * specialCards.size());
            if(nameCards.contains(specialCards.get(numero).getUsername())){

                randomComun();
            }else{
              //  firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
               // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
              //  HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("list",listUser+","+specialCards.get(numero).getUsername() );
                reference.updateChildren(hashMap);
                checkCards();

            }


        }else if(numero > 900 && numero <= 980){
            numero = (int) (Math.random() * epicCards.size());
            if(nameCards.contains(epicCards.get(numero).getUsername())){

                randomComun();
            }else{
              //  firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
               // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
               // HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("list",listUser+","+epicCards.get(numero).getUsername() );
                reference.updateChildren(hashMap);
                checkCards();

            }

        }else{
            numero = (int) (Math.random() * legendaryCards.size());
            if(nameCards.contains(legendaryCards.get(numero).getUsername())){

                randomComun();
            }else{
               // firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
               // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
              //  HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("list",listUser+","+legendaryCards.get(numero).getUsername() );
                reference.updateChildren(hashMap);
                checkCards();

            }



        }
    }



   /* private void myCards(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cards");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cards cards = snapshot.getValue(Cards.class);
                    if(cards.getCategory().equals("comun")){
                        normalCards.add(cards);
                    }
                    if(cards.getCategory().equals("especial")){
                        specialCards.add(cards);
                    }
                    if(cards.getCategory().equals("epica")){
                        epicCards.add(cards);
                    }
                    if(cards.getCategory().equals("legendaria")){
                        legendaryCards.add(cards);
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        imprimir();
    }
*/
    private void imprimir(){
        touch--;
        System.out.println("Holaaaaaa impri "+touch );

        for(int i = 0; i < normalCards.size(); i++){

            Cards cards =normalCards.get(i);
                    System.out.println("Cartas "+cards.getUsername());
        }
    }



    public void checkCards(){
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user.getList() != null){
                    listUser = user.getList();
                    coins = Integer.parseInt(user.getCoin());
                    filtList(user.getList());}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void filtList(String list){
        String[] caracters = list.split(",");
        for(int i = 0; i < caracters.length; i++) {
            nameCards.add(caracters[i]);
        }
       // myCards();
    }

   /* private void myCards(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cards");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cardsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cards cards = snapshot.getValue(Cards.class);
                    if(nameCards.contains(cards.getUsername())){
                        cardsList.add(cards);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}