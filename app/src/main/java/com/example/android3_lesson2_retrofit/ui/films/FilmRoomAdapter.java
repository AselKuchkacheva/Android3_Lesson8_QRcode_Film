package com.example.android3_lesson2_retrofit.ui.films;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmRoomAdapter extends RecyclerView.Adapter<FilmRoomAdapter.FilmRoomViewHolder> {
    private List<Film> list = new ArrayList<>();

    @NonNull
    @Override
    public FilmRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room_film, parent, false);
        return new FilmRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmRoomViewHolder holder, int position) {
        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Film> list) {
        this.list = list;
    }

    public class FilmRoomViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvNumber;

        public FilmRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_pos_number_room);
            tvTitle = itemView.findViewById(R.id.tv_film_title_room);
        }

        public void onBind(Film film){
            tvTitle.setText(film.getTitle());
            tvNumber.setText((1+getAdapterPosition()) + "");
        }
    }
}
