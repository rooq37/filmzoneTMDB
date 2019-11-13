package com.rooq37.filmzone.security;

import com.rooq37.filmzone.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserPrincipals implements UserDetails {

    private static final String ROLE_USER = "USER";
    private static final String ROLE_ADMIN = "ADMIN";

    private UserEntity user;
    private final List<SimpleGrantedAuthority> authorities = new LinkedList<>();

    public UserPrincipals(UserEntity user) {
        this.user = user;
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(user.getRole().equals(ROLE_ADMIN))
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        Date blockTillDate = user.getBlockedTill();
        if(blockTillDate != null){
            if(blockTillDate.after(new Date())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
