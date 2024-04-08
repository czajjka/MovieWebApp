package com.moviewebapp.movieapp.entity;

import javax.persistence.*;

@Entity
@Table(name="watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String director;
    private String category;
    private Integer rate;
    private String imageFileName;

    public Watchlist(int id, String title, String director, String category, Integer rate, String imageFileName) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.category = category;
        this.rate = rate;
        this.imageFileName = imageFileName;

    }

    public Watchlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
