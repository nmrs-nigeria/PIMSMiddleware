package pims.integrator.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class SignUpDto {
    @NotNull(message = "Invalid Name: Name is NULL")
    @NotEmpty(message = "Invalid Name: Empty name")
    private String name;
    @NotNull(message = "Invalid UserName: UserName is NULL")
    @NotEmpty(message = "Invalid UserName: Empty UserName")
    private String username;
    @NotNull(message = "Invalid Email: Email is NULL")
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Invalid Email: Empty Email")
    private String email;
//    @NotEmpty
    @Size(min = 8,message = "Please enter a minimum of 8 characters as password")
    @NotNull(message = "Invalid Password: Password is NULL")
    @NotEmpty(message = "Invalid Password: Empty Password")
    private String password;
    @NotNull(message = "Invalid Role: Role is NULL")
    @NotEmpty(message = "Invalid Role: Empty Role")
    private String role;
}
