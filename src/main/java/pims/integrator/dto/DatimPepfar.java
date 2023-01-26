package pims.integrator.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DatimPepfar {
    @NotNull
    @NotBlank(message = "Datime Code cannot be null")
    private String datimCode;
    @NotNull
    @NotBlank(message = "Pepfar ID cannot be null")
    private String pepfarID;
}
