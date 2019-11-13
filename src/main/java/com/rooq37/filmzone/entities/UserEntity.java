package com.rooq37.filmzone.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private Set<FavouriteListEntity> favouriteLists;

    @OneToMany(mappedBy = "user")
    private Set<RatingEntity> ratings;

    @ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="USER_FOLLOWED",
            joinColumns={@JoinColumn(name="USER_ENTITY_ID")},
            inverseJoinColumns={@JoinColumn(name="FOLLOWED_ID")})
    private Set<UserEntity> followed = new HashSet<>();

    @ManyToMany(mappedBy="followed")
    private Set<UserEntity> followers = new HashSet<>();

    @Column(name = "blocked_till_date")
    private Date blockedTill;

    @Column(name = "block_reason")
    private String blockReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<FavouriteListEntity> getFavouriteLists() {
        return favouriteLists;
    }

    public void setFavouriteLists(Set<FavouriteListEntity> favouriteLists) {
        this.favouriteLists = favouriteLists;
    }

    public Set<RatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(Set<RatingEntity> ratings) {
        this.ratings = ratings;
    }

    public Set<UserEntity> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<UserEntity> followed) {
        this.followed = followed;
    }

    public Set<UserEntity> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserEntity> followers) {
        this.followers = followers;
    }

    public Date getBlockedTill() {
        return blockedTill;
    }

    public void setBlockedTill(Date blockedTill) {
        this.blockedTill = blockedTill;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

}
