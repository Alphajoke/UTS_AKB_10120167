package com.rizkyrazak.uts_akb_10120167;

/* NIM      : 10119118
 * NAMA      : Rizky Septiana Abdul Razak
 * KELAS     : IF - 3 */


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter_Notes extends RecyclerView.Adapter<Adapter_Notes.ViewHolder>{
    LayoutInflater inflater;
    List<Note> notes;

    Adapter_Notes(Context context, List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter_Notes.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = inflater.inflate(R.layout.notes_card_view,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Notes.ViewHolder viewHolder, int position) {
        String title = notes.get(position).getTitle();
        String category = notes.get(position).getCategories();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();
        viewHolder.c_Title.setText(title);
        viewHolder.c_Categories.setText(category);
        viewHolder.c_Date.setText(date);
        viewHolder.c_Time.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView c_Title,c_Categories, c_Date, c_Time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c_Title =itemView.findViewById(R.id.c_notesTitle);
            c_Categories = itemView.findViewById(R.id.c_notesCategories);
            c_Date = itemView.findViewById(R.id.c_notesDate);
            c_Time = itemView.findViewById(R.id.c_notesTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), NotesDetail.class);
                    intent.putExtra("ID",notes.get(getAdapterPosition()).getID());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
