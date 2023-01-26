package pims.integrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pims.integrator.dto.JwtAuthResponse;
import pims.integrator.dto.LoginDto;
import pims.integrator.dto.SignUpDto;
import pims.integrator.dto.UpdateUserDto;
import pims.integrator.entity.User;
import pims.integrator.exception.APIException;
import pims.integrator.security.JwtTokenProvider;
import pims.integrator.service.impl.UserServiceImpl;


import javax.validation.Valid;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserServiceImpl userService;



    @PostMapping("/signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return   ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
//        // add check for username exists in a DB
        if(userService.existsByUsername(signUpDto.getUsername())){
           throw new APIException(HttpStatus.BAD_REQUEST,"Username is already taken!");
           // return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
//
//        // add check for email exists in DB
        if(userService.existsByEmail(signUpDto.getEmail())){
            throw  new APIException(HttpStatus.BAD_REQUEST,"Email is already taken!");
           // return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        User user =  userService.createUser(signUpDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/updqateuserstatus")
    public ResponseEntity<?> disableUser(@Valid @RequestBody UpdateUserDto userDto){
        User user =  userService.updateUserStatus(userDto);
        if(user!=null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            throw new APIException(HttpStatus.NOT_FOUND,"Username Not Found!");
    }








}
