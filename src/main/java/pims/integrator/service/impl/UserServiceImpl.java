package pims.integrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pims.integrator.dto.SignUpDto;
import pims.integrator.dto.UpdateUserDto;
import pims.integrator.entity.Role;
import pims.integrator.entity.User;
import pims.integrator.exception.ResourceNotFoundException;
import pims.integrator.repository.RoleRepository;
import pims.integrator.repository.UserRepository;
import pims.integrator.service.UserService;


import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public User createUser(SignUpDto signUpDto) {
        User user = createUserObjectFromSignUpDto(signUpDto);
        return userRepository.save(user);
    }

    @Override
    public User updateUserStatus(UpdateUserDto userDto) {
        User user = userRepository.findByUsernameOrEmail(userDto.getUserNameOrEmail(),userDto.getUserNameOrEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with username or email: ",userDto.getUserNameOrEmail()));
        user.setEnabled(userDto.isEnable());
        return userRepository.save(user);
    }

    @Override
    public User updateUserStatus(User user) {
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserNameOrEmail(String username, String email) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    private User createUserObjectFromSignUpDto(SignUpDto signUpDto){
        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Role roles = roleRepository.findByName(signUpDto.getRole()).orElseThrow(() -> new ResourceNotFoundException("Role",signUpDto.getRole()));
        user.setRoles(Collections.singleton(roles));

        return user;
    }


}
