package pk.org.cas.notepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavpuriteViewHolder>{

    List<Favourite> favourites;
    OnItemClickListener onItemClickListener;
    public FavouriteAdapter(List<Favourite> favourites){
        this.favourites = favourites;
    }
    public void setOnItemClickListener(FavouriteAdapter.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FavpuriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View roeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_note, null);
        return new FavpuriteViewHolder(roeView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavpuriteViewHolder holder, int position) {
        Favourite favourite = favourites.get(position);
        holder.tvTitle.setText(favourite.getTitle());
        holder.tvNote.setText(favourite.getNote());

        DateFormat df = new SimpleDateFormat("HH:mm:ss a, dd/MM/yyyy", Locale.getDefault());
        String currentDateAndTime = df.format(new Date());
        holder.tvDate.setText(currentDateAndTime);
    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }

    public class FavpuriteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvNote, tvDate;
        public FavpuriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvDate = itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null){
                        int position = getAdapterPosition();
                        onItemClickListener.onItemClick(FavouriteAdapter.FavpuriteViewHolder.this, position);
                    }
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(FavpuriteViewHolder holder, int position);
    }
}
