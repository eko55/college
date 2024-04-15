package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.model.dto.UserCreationRequest;
import com.example.college.model.entity.User;
import com.example.college.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreationRequest request){
        if(!userRepository.existsByUsername(request.username())){
            User user = new User(request);
            return userRepository.save(user);
        }
        else{
            throw new ResourceAlreadyExistsException(String.format("User with username %s already exists!",request.username()));
        }

    }
}
