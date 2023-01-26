package pims.integrator.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
public class FingerDto {
    @NotNull
    @NotEmpty(message = "Finger cannot be null")
    private String finger;
}
