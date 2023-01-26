package pims.integrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pims.integrator.dto.Clients;
import pims.integrator.dto.DatimPepfar;
import pims.integrator.dto.GlobalNumber;
import pims.integrator.dto.Name;
import pims.integrator.entity.ClientRegister;
import pims.integrator.service.impl.ClientRegisterServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pims/")
public class ClientRegistryController {
    @Autowired
    ClientRegisterServiceImpl registerService;
//
//    @GetMapping("/getclientbyglobalid" )
//    public ResponseEntity<ClientRegister> getClientRegisterByGlobalID(@Valid @RequestBody GlobalNumber globalNumber){
//        ClientRegister client = registerService.findClientByGlobalID(globalNumber.getGlobalID());
//        if(client!=null)
//            return ResponseEntity.ok(client);
//        else
//            return new ResponseEntity("Client Not Found",HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/getclientsbyname" )
    public ResponseEntity<Clients> getClientRegisterByNames(@Valid @RequestBody Name name){
        List<ClientRegister> clientList = registerService.findClientByName(name.getFirstName(), name.getLastName());
        Clients clients = new Clients();
        clients.setClientRegisters(clientList);
        if(clientList!=null)
            return ResponseEntity.ok(clients);
        else
            return new ResponseEntity("Clients Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getclientsbydatimpepfar" )
    public ResponseEntity<Clients> getClientRegisterByDatimAndPepfarID(@Valid @RequestBody DatimPepfar datimPepfar){
        List<ClientRegister> clientList = registerService.findClientByDatimPepfarID(datimPepfar.getDatimCode(), datimPepfar.getPepfarID());
        Clients clients = new Clients();
        clients.setClientRegisters(clientList);
        if(clientList!=null)
            return ResponseEntity.ok(clients);
        else
            return new ResponseEntity("Clients Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getclientsbydfinger" )
    public ResponseEntity<Clients> getClientRegisterByFinger(){
        return null;
    }

    @GetMapping("/test" )
    public ResponseEntity<String> getTest(){
            return new ResponseEntity("Test Successfully",HttpStatus.OK);
    }
}
