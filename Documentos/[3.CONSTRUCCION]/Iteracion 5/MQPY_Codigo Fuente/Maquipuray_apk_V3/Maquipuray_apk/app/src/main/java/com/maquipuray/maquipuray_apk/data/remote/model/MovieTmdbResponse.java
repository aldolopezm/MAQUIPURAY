package com.maquipuray.maquipuray_apk.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by  on 20/05/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
//EXAMPLE REQUEST NETWORK RETROFIT
public class MovieTmdbResponse {


    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @Expose
    @SerializedName("item_count")
    private int item_count;
    @Expose
    @SerializedName("items")
    private List<Items> items;
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("favorite_count")
    private int favorite_count;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("created_by")
    private String created_by;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public static class Items {
        @Expose
        @SerializedName("release_date")
        private String release_date;
        @Expose
        @SerializedName("overview")
        private String overview;
        @Expose
        @SerializedName("vote_average")
        private double vote_average;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("genre_ids")
        private List<Integer> genre_ids;
        @Expose
        @SerializedName("original_title")
        private String original_title;
        @Expose
        @SerializedName("original_language")
        private String original_language;
        @Expose
        @SerializedName("backdrop_path")
        private String backdrop_path;
        @Expose
        @SerializedName("adult")
        private boolean adult;
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("media_type")
        private String media_type;
        @Expose
        @SerializedName("video")
        private boolean video;
        @Expose
        @SerializedName("vote_count")
        private int vote_count;
        @Expose
        @SerializedName("popularity")
        private double popularity;
        @Expose
        @SerializedName("poster_path")
        private String poster_path;

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public boolean getAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public boolean getVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPoster_path() {
            return "https://image.tmdb.org/t/p/original" + poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }
    }
}
