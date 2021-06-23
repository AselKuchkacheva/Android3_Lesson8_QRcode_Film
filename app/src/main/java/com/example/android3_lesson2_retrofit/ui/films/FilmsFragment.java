package com.example.android3_lesson2_retrofit.ui.films;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android3_lesson2_retrofit.App;
import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.Film;
import com.example.android3_lesson2_retrofit.data.storage.GhibliStorage;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class FilmsFragment extends Fragment implements FilmAdapter.TitleListener {

    public static final String KEY_FILM = "key";
    private RecyclerView recyclerView;
    private FilmAdapter adapter;
    private final GhibliStorage ghibliStorage = new GhibliStorage();
    private Button btnOpenLocal;
    private BarcodeEncoder encodeBitmap = new BarcodeEncoder();
    private Button btnScanQR;

    private OnClickFilmFragment listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_film_title);
        adapter = new FilmAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setTitleListener(this);
        btnOpenLocal = view.findViewById(R.id.btn_open_local_data);
        listener = (OnClickFilmFragment) requireActivity();

        ghibliStorage.getFilms(new GhibliStorage.GhibliCallback<Film>() {
            @Override
            public void onSuccess(Film film) {
            }

            @Override
            public void onSuccessList(List<Film> films) {
                adapter.setList(films);
            }
        });
        btnOpenLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_layout, new FilmRoomFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnScanQR = view.findViewById(R.id.btn_scan_qrcode);
        btnScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openScan();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_films, container, false);
    }

    @Override
    public void openDetails(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FILM, id);
        FilmDetailsFragment filmDetailsFragment = new FilmDetailsFragment();
        filmDetailsFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, filmDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void saveToRoom(Film film) {
        App.dataBase.filmDao().insertFilm(film);
    }

    @Override
    public void removeFromRoom(Film film) {
        App.dataBase.filmDao().deleteFilm(film);
    }

    @Override
    public void createScan(Film film) {


        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Scan");
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.conteiner_for_alert_qrcode, null);
        ImageView imageView = linearLayout.findViewById(R.id.iv_alert_qr);
        try {
            Bitmap bitmap = encodeBitmap.encodeBitmap(
                    film.getId(),
                    BarcodeFormat.QR_CODE,
                    250,
                    250
            );
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        builder.setView(linearLayout);
        builder.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String value = result.getContents();

        openFragment(value);
    }

    private void openFragment(String value) {
        Bundle bundle = new Bundle();
        bundle.putString(FilmsFragment.KEY_FILM, value);
        FilmDetailsFragment detailsFragment = new FilmDetailsFragment();
        detailsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        Toast.makeText(requireContext(), "text", Toast.LENGTH_SHORT).show();

    }


}