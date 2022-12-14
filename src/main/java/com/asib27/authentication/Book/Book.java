package com.asib27.authentication.Book;


import com.asib27.authentication.Publisher.Publisher;
import com.asib27.authentication.Reviews.Review;
import com.asib27.authentication.Writer.Writer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Table(name = "books")
@Entity(name = "books")
public class Book {

    @Id
    @Column(
        name = "isbn",
        length = 13
    )
    private String id;

    @Column(
            name = "name",
            nullable = false,
            length = 100
    )
    private String name;

    @Column(
            name= "edition"
    )
    private int edition;

    @Column(
            name = "language",
            length = 50
    )
    private String language;

    @Column(
            name = "genre",
            nullable = false,
            length = 50
    )
    private String genre;

    @Column(
            name = "price",
            nullable = false
    )
    private int price;

    @Column(
            name = "quantity_available",
            nullable = false
    )
    private int quantity_available;

    @Column(
            name="link"
    )
    private String link;

    @Column(name = "description")
    private String description;


    @ManyToMany
    @JoinTable(name = "authorOfBooks",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Writer> writersOfTheBook = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    private Publisher publisher;


    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private Set<Review> reviews = new HashSet<>();


    public Book() {
    }

    public Book(String name, int edition,
                String language, String genre, int price,
                int quantity_available,String link, String des) {
        this.name = name;
        this.edition = edition;
        this.language = language;
        this.genre = genre;
        this.price = price;
        this.link = link;
        this.quantity_available = quantity_available;
        this.description = des;
    }



    public Book(String isbn, String name, int edition,
                String language, String genre, int price,
                int quantity_available, String link, String description) {
        this.id = isbn;
        this.name = name;
        this.edition = edition;
        this.language = language;
        this.genre = genre;
        this.price = price;
        this.quantity_available = quantity_available;
        this.link = link;
        this.description = description;
    }

    public String getIsbn() {
        return id;
    }

    public void setIsbn(String isbn) {
        this.id = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity_available() {
        return quantity_available;
    }

    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;
    }

    public Set<Writer> getWritersOfTheBook() {
        return writersOfTheBook;
    }


    public void addWriters(Writer writer) {
        writersOfTheBook.add(writer);
    }

    public void addPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
