package com.example.android3_lesson2_retrofit.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.android3_lesson2_retrofit.data.converters.ListConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
@TypeConverters(ListConverter.class)
public class Film {


    @SerializedName("id")
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "film_title")
    @SerializedName("title")
    private String title;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_title_romanised")
    private String originalTitleRomanised;
    @SerializedName("description")
    private String description;
    @SerializedName("director")
    private String director;
    @SerializedName("producer")
    @ColumnInfo(name = "film_producer")
    private String producer;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("running_time")
    private String runningTime;
    @SerializedName("rt_score")
    private String rtScore;
    @SerializedName("people")
    private List<String> people = null;
    @SerializedName("species")
    private List<String> species = null;
    @SerializedName("locations")
    private List<String> locations = null;
    @SerializedName("vehicles")
    private List<String> vehicles = null;
    @SerializedName("url")
    private String url;
    private boolean isSaved;

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public Film(String title, String description, String director, String producer, String id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.producer = producer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalTitleRomanised() {
        return originalTitleRomanised;
    }

    public void setOriginalTitleRomanised(String originalTitleRomanised) {
        this.originalTitleRomanised = originalTitleRomanised;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getRtScore() {
        return rtScore;
    }

    public void setRtScore(String rtScore) {
        this.rtScore = rtScore;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", originalTitleRomanised='" + originalTitleRomanised + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", rtScore='" + rtScore + '\'' +
                ", people=" + people +
                ", species=" + species +
                ", locations=" + locations +
                ", vehicles=" + vehicles +
                ", url='" + url + '\'' +
                '}';
    }
}
