package pims.integrator.service;


import pims.integrator.dto.SignUpDto;
import pims.integrator.dto.UpdateUserDto;
import pims.integrator.entity.User;

public interface UserService {
    User createUser(SignUpDto signUpDto);
    User updateUserStatus(UpdateUserDto userDto);
    User updateUserStatus(User user);
    User findUserByUserNameOrEmail(String username, String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
