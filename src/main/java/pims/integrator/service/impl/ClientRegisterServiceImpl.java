package pims.integrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pims.integrator.entity.ClientRegister;
import pims.integrator.repository.ClientRegistoryRepo;
import pims.integrator.service.ClientRegistryService;

import java.util.List;
@Service
public class ClientRegisterServiceImpl implements ClientRegistryService {
    @Autowired
    ClientRegistoryRepo clientRegistoryRepo;

    @Override
    public List<ClientRegister> findClientByDatimPepfarID(String datimCode, String pepfarID) {
        return clientRegistoryRepo.findClientRegisterByDatimCodeAndPatientID(datimCode,pepfarID);
    }

    @Override
    public List<ClientRegister> findClientByName(String firstName, String lastName) {
        return clientRegistoryRepo.findClientRegisterByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public ClientRegister findClientByGlobalID(String globalID) {
        return clientRegistoryRepo.findClientRegisterByPatientUniqueID(globalID);
    }
}
