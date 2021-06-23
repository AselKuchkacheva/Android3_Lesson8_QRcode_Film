package com.example.android3_lesson2_retrofit.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android3_lesson2_retrofit.data.model.Film;

import java.util.List;


@Dao
public interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilm(Film film);

    @Update
    void updateFilm(Film film);

    @Delete
    void deleteFilm(Film film);

    @Query("SELECT  * FROM film")
    List<Film> getAllFilms();

    @Query("SELECT * FROM film WHERE id =:filmId")
    Film getFilmById(String filmId);
}
