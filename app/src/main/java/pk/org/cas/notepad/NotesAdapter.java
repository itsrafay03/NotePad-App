package pk.org.cas.notepad;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder>{

    private List<Notes> notes;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public NotesAdapter(List<Notes> notes){
        this.notes = notes;
    }


    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_note, null);
        return new NoteViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes note = notes.get(position);
        holder.tvTitle.setText(String.valueOf(note.getTitle()));
        holder.tvNote.setText(String.valueOf(note.getNote()));

        DateFormat df = new SimpleDateFormat("HH:mm:ss a, dd/MM/yyyy", Locale.getDefault());
        String currentDateAndTime = df.format(new Date());
        holder.tvDate.setText(currentDateAndTime);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void filterList(List<Notes> filteredList){
        this.notes = filteredList;
        notifyDataSetChanged();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvNote, tvDate;
        ImageView ivFavourite;
        CardView cardContainer;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivFavourite = itemView.findViewById(R.id.ivFavourite);
            cardContainer = itemView.findViewById(R.id.note_container);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null){
                        int position = getAdapterPosition();
                        onItemClickListener.onItemClick(NoteViewHolder.this, position);
                    }
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(NoteViewHolder holder, int position);
    }
}
