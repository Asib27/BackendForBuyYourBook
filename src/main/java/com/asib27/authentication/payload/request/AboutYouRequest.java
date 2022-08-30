package com.asib27.authentication.payload.request;

public class AboutYouRequest {
    private String description;
    private String fav_books;
    private String fav_genre;

    public AboutYouRequest() {
    }

    public AboutYouRequest(String description, String fav_books, String fav_genre) {
        this.description = description;
        this.fav_books = fav_books;
        this.fav_genre = fav_genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFav_books() {
        return fav_books;
    }

    public void setFav_books(String fav_books) {
        this.fav_books = fav_books;
    }

    public String getFav_genre() {
        return fav_genre;
    }

    public void setFav_genre(String fav_genre) {
        this.fav_genre = fav_genre;
    }
}
