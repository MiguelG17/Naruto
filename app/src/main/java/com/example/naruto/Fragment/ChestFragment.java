package com.example.naruto.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.naruto.Model.User;
import com.example.naruto.R;
import com.example.naruto.RandomActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChestFragment extends Fragment {

    FirebaseUser firebaseUser;
    TextView text_coins;
    ImageButton cero_cero;
    DatabaseReference reference;
    User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChestFragment newInstance(String param1, String param2) {
        ChestFragment fragment = new ChestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chest, container, false);
        text_coins = view.findViewById(R.id.text_coins);
        cero_cero = view.findViewById(R.id.cero_cero);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
         reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 user = dataSnapshot.getValue(User.class);
                text_coins.setText(user.getCoin());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cero_cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        if(checkCoin(1)) {
                            Intent intent = new Intent(getContext(), RandomActivity.class);
                            intent.putExtra("number",1);
                            startActivity(intent);

                        }

            }
        });

      //  startActivity(new Intent(getContext(), AdActivity.class));
        // Inflate the layout for this fragment
        return view;
    }

    private void updateProfile(String coin ){
         reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("coin",coin);


        reference.updateChildren(hashMap);
    }

    private boolean checkCoin(int i){
        int coinUser = Integer.parseInt(user.getCoin());
        boolean retorno = false;

        switch (i){
            case 1:
                if(coinUser >= 50){
                    coinUser = coinUser - 50;
                    user.setCoin(String.valueOf(coinUser));
                    updateProfile(user.getCoin());
                    retorno = true;
                }else{
                    retorno = false;
                    Toast.makeText(getContext(),"No tienes suficientes monedas",Toast.LENGTH_LONG).show();
                }

                break;
            default:
               retorno = false;
                break;
        }
       return retorno;

    }



}