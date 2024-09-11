package com.example.college.controller;

//import com.example.college.model.dto.AuthRequest;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;

//@Tag(name = "Auth")
//@RestController
//@AllArgsConstructor
//@RequestMapping("api/v1/auth")
public class AuthenticationController {

//    private AuthenticationManager authenticationManager;
//
//    @PostMapping()
//    public ResponseEntity<Authentication> authenticate(@RequestBody AuthRequest request) {
//        try {
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
//            return ResponseEntity.ok(authenticationManager.authenticate(usernamePasswordAuthenticationToken));
//        } catch (AuthenticationException e) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password!");
//        }
//    }
}
