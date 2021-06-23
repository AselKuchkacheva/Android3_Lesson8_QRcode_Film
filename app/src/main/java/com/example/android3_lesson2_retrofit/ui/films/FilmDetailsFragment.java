package com.example.android3_lesson2_retrofit.ui.films;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.storage.GhibliStorage;
import com.example.android3_lesson2_retrofit.ui.people.PeopleFragment;

import java.util.List;

public class FilmDetailsFragment extends Fragment {

    private final GhibliStorage ghibliStorage = new GhibliStorage();

    private TextView tvDescription;
    private TextView tvTitle;
    private TextView tvDirector;
    private TextView tvProducer;
    private TextView tvReleaseDate;
    private Button btnPeople;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        ghibliStorage.getFilm(getArguments().getString(FilmsFragment.KEY_FILM),
                new GhibliStorage.GhibliCallback<Film>() {
            @Override
            public void onSuccess(Film film) {
                tvTitle = getView().findViewById(R.id.tv_title);
                tvTitle.setText(film.getTitle());
                tvReleaseDate = getView().findViewById(R.id.tv_releaseDate);
                tvReleaseDate.setText(film.getReleaseDate());
                tvDirector = getView().findViewById(R.id.tv_director);
                tvDirector.setText(film.getDirector());
                tvProducer = getView().findViewById(R.id.tv_producer);
                tvProducer.setText(film.getProducer());
                tvDescription = getView().findViewById(R.id.tv_description);
                tvDescription.setText(film.getDescription());

                btnPeople = getView().findViewById(R.id.btn_people);
                btnPeople.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.frame_layout,new PeopleFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
                    @Override
                    public void onSuccessList(List<Film> films) {

                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film_details, container, false);
    }
}