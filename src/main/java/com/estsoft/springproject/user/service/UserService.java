package com.estsoft.springproject.user.service;

import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.domain.entity.Users;
import com.estsoft.springproject.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Users save(AddUserRequest dto) {
        return repository.save(
                Users.builder()
                        .email(dto.getEmail())
                        .password(encoder.encode(dto.getPassword()))    // 패스워드 암호화
                        .build()
        );
    }
}
