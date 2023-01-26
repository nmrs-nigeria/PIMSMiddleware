package pims.integrator.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotNull(message = "Invalid UsernameOrEmail: UsernameOrEmail is NULL")
    @NotEmpty(message = "Invalid UsernameOrEmail: Empty UsernameOrEmail")
    private String usernameOrEmail;
//    @NotEmpty(message = "Please enter password")
    @Size(min = 8,message = "Invalid Password: Please enter a minimum of 8 characters as password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @NotEmpty(message = "Invalid Password: Empty Password")
    private String password;
}
