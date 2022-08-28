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

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "middle_name")
    private String middle_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "phone_number", length = 11)
    private String phone_number;

    @Column(name = "backup_phone_number", length = 11)
    private String backup_phone_number;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBackup_phone_number() {
        return backup_phone_number;
    }

    public void setBackup_phone_number(String backup_phone_number) {
        this.backup_phone_number = backup_phone_number;
    }
}
