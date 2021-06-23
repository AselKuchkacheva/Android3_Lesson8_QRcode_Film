package com.example.android3_lesson2_retrofit.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.ui.films.FilmDetailsFragment;
import com.example.android3_lesson2_retrofit.ui.films.FilmsFragment;
import com.example.android3_lesson2_retrofit.ui.films.OnClickFilmFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements OnClickFilmFragment {


    protected IntentIntegrator intentIntegrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, new FilmsFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String value = result.getContents();
        Bundle bundle = new Bundle();

        bundle.putString(FilmsFragment.KEY_FILM, value);
        FilmDetailsFragment filmDetailsFragment = new FilmDetailsFragment();
        filmDetailsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, filmDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void openScan() {
        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }
}