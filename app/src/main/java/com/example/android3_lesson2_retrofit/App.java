package com.example.android3_lesson2_retrofit;

import android.app.Application;

import androidx.room.Room;

import com.example.android3_lesson2_retrofit.data.local.AppDataBase;

public class App extends Application {

    public static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room
                .databaseBuilder(
                        this,
                        AppDataBase.class,
                        "FilmApi")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
