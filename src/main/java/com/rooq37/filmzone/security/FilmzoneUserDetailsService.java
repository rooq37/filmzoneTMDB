package com.rooq37.filmzone.security;

import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.notifications.NotificationService;
import com.rooq37.filmzone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FilmzoneUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserEntity user = userService.getUserByEmail(email);
            if (user == null) {
                notificationService.addErrorMessage("Użytkownik o adresie email " + email + " nie istnieje!");
                throw new UsernameNotFoundException("No user found with email: " + email);
            }

            return new UserPrincipals(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
