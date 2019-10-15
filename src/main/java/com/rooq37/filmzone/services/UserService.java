package com.rooq37.filmzone.services;

import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.register.RegisterForm;
import com.rooq37.filmzone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String checkRegisterAbility(RegisterForm registerForm){
        if(userRepository.findByEmail(registerForm.getEmail()) != null) return "Użytkownik o podanym adresie email już istnieje!";
        if(!registerForm.getPassword().equals(registerForm.getConfirmPassword())) return "Podane hasła różnią się od siebie!";
        return null;
    }

    public String registerUser(RegisterForm registerForm){
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname(registerForm.getNickname());
        userEntity.setEmail(registerForm.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder(11).encode(registerForm.getPassword()));
        userRepository.save(userEntity);
        return "Konto zostało pomyślnie utworzone!";
    }

    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
