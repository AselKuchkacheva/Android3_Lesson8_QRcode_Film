package com.example.android3_lesson2_retrofit.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android3_lesson2_retrofit.data.model.Film;

@Database(entities = {
        Film.class},
        version = 1)
abstract public class AppDataBase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
