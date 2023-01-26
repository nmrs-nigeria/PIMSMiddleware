package pims.integrator.service;

import pims.integrator.entity.ClientRegister;

import java.util.List;

public interface ClientRegistryService {
    List<ClientRegister> findClientByDatimPepfarID(String datimCode, String pepfarID);
    List<ClientRegister> findClientByName(String firstName, String lastName);
    ClientRegister findClientByGlobalID(String globalID);
}
