package pims.integrator.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserDto {

    @NotEmpty(message = "Please enter username or email to update user status")
    private String userNameOrEmail;
    @NotNull(message = "Please Enter Status Value")
    private boolean enable;
}
