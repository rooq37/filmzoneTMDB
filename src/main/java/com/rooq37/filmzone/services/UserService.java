package com.rooq37.filmzone.services;

import com.rooq37.filmzone.entities.UserEntity;
import com.rooq37.filmzone.dtos.RegisterDTO;
import com.rooq37.filmzone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String checkRegisterAbility(RegisterDTO registerDTO){
        if(userRepository.findByEmail(registerDTO.getEmail()) != null) return "Użytkownik o podanym adresie email już istnieje!";
        if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) return "Podane hasła różnią się od siebie!";
        return null;
    }

    public String registerUser(RegisterDTO registerDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname(registerDTO.getNickname());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder(11).encode(registerDTO.getPassword()));
        userEntity.setRegisterDate(new Date());
        userEntity.setRole("USER");
        userRepository.save(userEntity);
        return "Konto zostało pomyślnie utworzone!";
    }

    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> getUsers(String nickname){
        return userRepository.findAllByNicknameContains(nickname);
    }

    public String getUserEmailById(Long id){
        return userRepository.findById(id).get().getEmail();
    }

}
