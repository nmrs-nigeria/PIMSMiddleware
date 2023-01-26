package pims.integrator.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Name {
    @NotNull
    @NotBlank(message = "First Name cannot be null")
    private String firstName;
    @NotNull
    @NotBlank(message = "Last Name cannot be null")
    private String lastName;
}
