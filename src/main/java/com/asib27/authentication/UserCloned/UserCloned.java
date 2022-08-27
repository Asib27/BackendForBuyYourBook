package com.asib27.authentication.UserCloned;


import com.asib27.authentication.Locations.Location;
import com.asib27.authentication.Reviews.Review;
import com.asib27.authentication.Transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "User_cloned")
@Entity(name = "user_cloned")
public class UserCloned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name="link")
    private String link;

    public UserCloned(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserCloned() {
    }


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;



    @ManyToMany
    @JoinTable(name = "Follows",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "follows_whom")
    )
    private Set<UserCloned> follows = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "follows")
    private Set<UserCloned>followedby = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userCloned")
    private Set<Review> reveiws = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void whomFollows(UserCloned user){
        follows.add(user);
    }

    public Set<UserCloned> getFollows() {
        return follows;
    }

    public Set<UserCloned> getFollowedby() {
        return followedby;
    }

    public void addReview(Review review)
    {
        reveiws.add(review);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
