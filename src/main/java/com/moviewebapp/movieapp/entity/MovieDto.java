package com.moviewebapp.movieapp.entity;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


public class MovieDto {
    @NotEmpty(message = "The name is required")
    private String title;

    @NotEmpty(message = "The name is required")
    private String director;

    @NotEmpty(message = "The subject is required")
    private String category;

    @Min(0)
    private Integer rate;

    private MultipartFile imageFileName;


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

    public MultipartFile getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(MultipartFile imageFileName) {
        this.imageFileName = imageFileName;
    }
}