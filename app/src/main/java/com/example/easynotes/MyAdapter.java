package com.example.easynotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder> {

    Context context;
    ArrayList<Note> userArrayList;

    public MyAdapter(Context context, ArrayList<Note> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note user = userArrayList.get(position);

        holder.title.setText(user.title);
        holder.details.setText(user.details);



    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,details;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvtitle);
            details = itemView.findViewById(R.id.tvdetails);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Note user = userArrayList.get(getAbsoluteAdapterPosition());
            Intent intent = new Intent(context,Update.class);
            intent.putExtra("user",user);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        }
    }

}
