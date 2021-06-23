package com.example.android3_lesson2_retrofit.data.storage;

import android.util.Log;

import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.model.People;
import com.example.android3_lesson2_retrofit.data.remote.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GhibliStorage {

    public void getFilm(String filmId, GhibliCallback<Film> callback) {
        RetrofitBuilder.getInstance().getFilm(filmId).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Request error...");
                }
            }
            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getFilms(GhibliCallback<Film> callback){
        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccessList(response.body());

                } else {
                    callback.onFailure("Request error...");
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getPeoples (GhibliCallback<People> callback){
        RetrofitBuilder.getInstance().getPeoples().enqueue(new Callback<List<People>>() {
            @Override
            public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                if (response.isSuccessful() && response.body() != null){
                    callback.onSuccessList(response.body());
                } else {
                    callback.onFailure("Request error...");
                }
            }

            @Override
            public void onFailure(Call<List<People>> call, Throwable t) {

            }
        });
    }

    public interface GhibliCallback<Data> {
        void onSuccess(Data data);
        void onSuccessList(List<Data> dataList);
        default void onFailure(String errorMsg){
            Log.d("tag", errorMsg);
        };
    }
}
