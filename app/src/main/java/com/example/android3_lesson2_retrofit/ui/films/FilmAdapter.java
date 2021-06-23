package com.example.android3_lesson2_retrofit.ui.films;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_lesson2_retrofit.App;
import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private final List<Film> list = new ArrayList<>();
    private TitleListener titleListener;

    @NonNull
    @Override
    public FilmAdapter.FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.FilmViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Film> films) {
        list.addAll(films);
        notifyDataSetChanged();
    }

    public void setTitleListener(TitleListener titleListener) {
        this.titleListener = titleListener;
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final Button btnQR;
        private final CheckBox btnSaveToRoom;


        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_film_title);
            btnQR = itemView.findViewById(R.id.btn_qr_code);
            btnSaveToRoom = itemView.findViewById(R.id.ch_box_choose);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleListener.openDetails(list.get(getAdapterPosition()).getId());
                }
            });

            btnSaveToRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = ((CheckBox) v).isChecked();

                    if (checked) {
                        titleListener.saveToRoom(list.get(getAdapterPosition()));
                    } else {
                        titleListener.removeFromRoom(list.get(getAdapterPosition()));
                    }
                }
            });

            btnQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleListener.createScan(list.get(getAdapterPosition()));
                }
            });
        }

        public void onBind(Film film) {
            tvTitle.setText(film.getTitle());
            //  tvNumber.setText((1 + getAdapterPosition()) + "");

            if (App.dataBase.filmDao().getFilmById(film.getId()) != null) {
                btnSaveToRoom.setChecked(true);
            } else {
                btnSaveToRoom.setChecked(false);
            }
//                btnSaveToRoom.setChecked(film.isSaved());

        }
    }

    public interface TitleListener {
        void openDetails(String id);

        void saveToRoom(Film film);

        void removeFromRoom(Film film);

        void createScan(Film film);
    }

}
