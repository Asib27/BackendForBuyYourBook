package com.asib27.authentication.Transaction;


import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Locations.Location;
import com.asib27.authentication.UserCloned.UserCloned;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Transaction")
@Entity
public class Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tx_id")
    private Long id;


//    @ManyToMany
//    @JoinTable(name = "Books_in_transaction",
//            joinColumns = @JoinColumn(name = "tx_id"),
//            inverseJoinColumns = @JoinColumn(name = "isbn")
//    )
//    private Set<Book>booksInTransaction = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",  referencedColumnName = "userid")
    private UserCloned user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;


    @Column(name = "total_price")
    private Double total_price;


    public Transaction() {
    }

//    public Set<Book> getBooksInTransaction() {
//        return booksInTransaction;
//    }
//
//    public void addBook(Book book) {
//        booksInTransaction.add(book);
//    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCloned getUser() {
        return user;
    }

    public void setUser(UserCloned user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
}
