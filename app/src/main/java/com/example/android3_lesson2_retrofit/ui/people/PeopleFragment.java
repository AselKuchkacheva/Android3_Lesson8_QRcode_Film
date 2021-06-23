package com.example.android3_lesson2_retrofit.ui.people;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.People;
import com.example.android3_lesson2_retrofit.data.storage.GhibliStorage;

import java.util.List;

public class PeopleFragment extends Fragment implements PeopleAdapter.PeopleDetailsListener {

    private RecyclerView recyclerView;
    private PeopleAdapter adapter;
    private GhibliStorage ghibliStorage = new GhibliStorage();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_people);
        adapter = new PeopleAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNameListener(this);
        ghibliStorage.getPeoples(new GhibliStorage.GhibliCallback<People>() {
            @Override
            public void onSuccess(People people) {

            }

            @Override
            public void onSuccessList(List<People> people) {
                adapter.setList(people);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_people, container, false);
    }

    @Override
    public void openPeopleDetails(String id) {

    }
}