package com.example.android3_lesson2_retrofit.ui.people;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_lesson2_retrofit.R;
import com.example.android3_lesson2_retrofit.data.model.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private final List<People> list = new ArrayList<>();
    private PeopleDetailsListener peopleDetailsListener;

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_people, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<People> people) {
        list.addAll(people);
        notifyDataSetChanged();
    }

    public void setNameListener(PeopleDetailsListener peopleDetailsListener){
        this.peopleDetailsListener = peopleDetailsListener;
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvAge;
        private final TextView tvGender;
        private final TextView tvEyeColor;
        private final TextView tvHairColor;
        private final TextView tvPeoplesFilms;
        private final TextView tvPeopleSpecies;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvGender = itemView.findViewById(R.id.tv_gender);
            tvEyeColor = itemView.findViewById(R.id.tv_eye_color);
            tvHairColor = itemView.findViewById(R.id.tv_hair_color);
            tvPeoplesFilms = itemView.findViewById(R.id.tv_people_films);
            tvPeopleSpecies = itemView.findViewById(R.id.tv_people_species);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    peopleDetailsListener.openPeopleDetails(list.get(getAdapterPosition()).getId());
                }
            });
        }

        public void onBind(People people) {
            tvName.setText(people.getName());
            tvAge.setText(people.getAge());
            tvGender.setText(people.getGender());
            tvEyeColor.setText(people.getEyeColor());
            tvHairColor.setText(people.getHairColor());
            tvPeoplesFilms.setText(people.getFilms().get(0));
            tvPeopleSpecies.setText(people.getSpecies());
        }
    }
    public interface PeopleDetailsListener {
        void openPeopleDetails(String id);
    }
}
