package com.asib27.authentication.Locations;


import com.asib27.authentication.Publisher.Publisher;
import com.asib27.authentication.Transaction.Transaction;
import com.asib27.authentication.UserCloned.UserCloned;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Locations")
@Entity(name = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            length = 200,
            name = "street_address"
    )
    private String street;

    @Column(
            nullable = false,
            length = 50,
            name = "district"
    )
    private String District;

    @Column(name = "country")
    private String country;

    public Location(String street, String district, String country) {
        this.street = street;
        District = district;
        this.country = country;
    }

    public Location() {
    }

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private Set<Publisher> publishersInLocation = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private Set<UserCloned> usersInLocation = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private Set<Transaction> transactionLocations = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public Set<Publisher> getPublishersInLocation() {
        return publishersInLocation;
    }

    public Set<UserCloned> getUsersInLocation() {
        return usersInLocation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
