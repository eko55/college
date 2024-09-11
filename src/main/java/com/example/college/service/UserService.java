package com.example.college.service;

import com.example.college.exception.ResourceAlreadyExistsException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.model.Role;
import com.example.college.model.dto.UserCreationRequest;
import com.example.college.model.entity.AppUser;
import com.example.college.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AppUser createUser(UserCreationRequest request){
        if(!userRepository.existsByUsername(request.username())){
            AppUser user = new AppUser(request);
            return userRepository.save(user);
        }
        else{
            throw new ResourceAlreadyExistsException(String.format("User with username %s already exists!",request.username()));
        }
    }

    public AppUser getUser(Long userId) {
        if (userExists(userId)) {
            return userRepository.findById(userId).get();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public void changeRole(Long userId, Role role) {
        if (userExists(userId)) {
            AppUser appUser = getUser(userId);
            appUser.setRole(role);
            userRepository.save(appUser);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        if (userExists(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

//    public void changeUserPassword(Long userId, UserPassChangeInput userInput) {
//        if (userExists(userId)) {
//            AppUser appUser = getUser(userId);
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            if (encoder.matches(userInput.getOldPassword(), appUser.getPassword())) {
//                appUser.setPassword(new BCryptPasswordEncoder().encode(userInput.getNewPassword()));
//                userRepository.save(appUser);
//            } else {
//                throw new OldPasswordNotMatchingException("Old password doesn't match.");
//            }
//        } else {
//            throw new ResourceNotFoundException("User not found");
//        }
//    }

}
