package com.example.naruto.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.naruto.Model.Cards;
import com.example.naruto.R;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ImageViewHolder> {

    private Context context;
    private List<Cards> mCards;

    public AlbumAdapter(Context context, List<Cards> mCards) {
        this.context = context;
        this.mCards = mCards;
    }

    @NonNull
    @Override
    public AlbumAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fotos_item, parent, false);
        return new AlbumAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Cards cards = mCards.get(position);
        Glide.with(context).load(cards.getImageurl()).into(holder.post_image);

    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public ImageView post_image;

        public ImageViewHolder(@NonNull View itemView){
            super(itemView);

            post_image = itemView.findViewById(R.id.post_image);
        }


    }
}
