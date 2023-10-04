package com.example.smartbin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartbin.EventInfo;
import com.example.smartbin.MainActivity2;
import com.example.smartbin.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final int count;
    String[] Author;
    String[] data2;
    String[] data;
    Context context;
    String[] picture;


    public EventAdapter(Context context, String[] data, int count, String[] picture, String[] data2, String[] Author) {
        this.data = data;
        this.context = context;
        this.count = count;
        this.data2 = data2;
        this.picture = picture;
        this.Author = Author;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.new_event, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {

        int position = count - 1 - pos;
        // its in reverse order so the latest post comes on top
        // for undo reverse do(int position =pos;)
        holder.textView.setText(data[position]);
        holder.textView2.setText(data2[position]);
        holder.id.setText(Author[position]+" days left");
        Glide.with(context)
                .load(picture[position])
                .placeholder(R.drawable.coolimg)
                .error(R.drawable.coolimg)
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventInfo.class);
                intent.putExtra("a", data[position]);
                intent.putExtra("b", data2[position]);
                intent.putExtra("c", Author[position]);
                intent.putExtra("d", picture[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView imageView;
        TextView id;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            textView2 = itemView.findViewById(R.id.text2);
            imageView = itemView.findViewById(R.id.imageinrecycler);
            id = itemView.findViewById(R.id.text3);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
