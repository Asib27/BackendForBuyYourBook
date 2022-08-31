package com.asib27.authentication.Transaction;


import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Locations.Location;
import com.asib27.authentication.UserCloned.UserCloned;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Transaction")
@Entity
public class Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tx_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",  referencedColumnName = "userid")
    private UserCloned user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;


    @Column(name = "total_price")
    private Double total_price;

    @Column(name = "added_time")
    private Timestamp added_time;


    public Transaction() {
    }

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

    public Timestamp getAdded_time() {
        return added_time;
    }

    public void setAdded_time(Timestamp added_time) {
        this.added_time = added_time;
    }
}
