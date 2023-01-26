package pims.integrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pims.integrator.entity.ClientRegister;

import java.util.List;
@Repository
public interface ClientRegistoryRepo extends JpaRepository<ClientRegister, Integer> {
    ClientRegister findClientRegisterByPatientUniqueID(String globalID);
    List<ClientRegister> findClientRegisterByFirstNameAndLastName(String firstName, String lastName);
    List<ClientRegister> findClientRegisterByDatimCodeAndPatientID(String datimCode, String pepfarID);
}
