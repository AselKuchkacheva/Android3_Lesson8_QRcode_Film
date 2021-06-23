package com.example.android3_lesson2_retrofit.ui.films;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3_lesson2_retrofit.App;
import com.example.android3_lesson2_retrofit.R;

public class FilmRoomFragment extends Fragment {

    private RecyclerView recyclerView;
    private FilmRoomAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_films_room);
        adapter = new FilmRoomAdapter();
        adapter.setList(App.dataBase.filmDao().getAllFilms());
        recyclerView.setAdapter(adapter);
    }
}