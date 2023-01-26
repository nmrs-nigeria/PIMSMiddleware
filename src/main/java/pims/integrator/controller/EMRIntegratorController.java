package pims.integrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pims.integrator.dto.GlobalNumber;
import pims.integrator.dto.GoldenRecordDto;
import pims.integrator.entity.ClientRegister;
import pims.integrator.service.impl.ClientRegisterServiceImpl;
import pims.integrator.service.impl.JeMPIServiceImpl;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class EMRIntegratorController {
    @Autowired
    JeMPIServiceImpl jeMPIService;

    @GetMapping("/getclientbyglobalid" )
    public GoldenRecordDto getClientRegisterByGlobalID(@Valid @RequestBody GlobalNumber globalNumber){
        return jeMPIService.getClientByGlobalID(globalNumber);
//        if(goldenRecordDto!=null)
//            return ResponseEntity.ok(goldenRecordDto);
//        else
//            return new ResponseEntity("Client Not Found",HttpStatus.NOT_FOUND);
    }

    
}
