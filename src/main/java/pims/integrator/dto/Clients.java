package pims.integrator.dto;

import lombok.Data;
import pims.integrator.entity.ClientRegister;

import java.util.List;
@Data
public class Clients {
    private List<ClientRegister> clientRegisters;
}
