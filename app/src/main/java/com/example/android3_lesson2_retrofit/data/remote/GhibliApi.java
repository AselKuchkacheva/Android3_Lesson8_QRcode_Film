package com.example.android3_lesson2_retrofit.data.remote;

import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.model.People;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {
    @GET ("films/")
    Call<List<Film>> getFilms();

    @GET("films/{id}")
    Call<Film> getFilm(
            @Path("id") String filmId);

    @GET("people/")
    Call<List<People>> getPeoples();
}
