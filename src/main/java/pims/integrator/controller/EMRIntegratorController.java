package pims.integrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pims.integrator.dto.Clients;
import pims.integrator.dto.FingerDto;
import pims.integrator.dto.GlobalNumber;
import pims.integrator.dto.GoldenRecordDto;
import pims.integrator.service.impl.ABISServiceImpl;
import pims.integrator.service.impl.JeMPIServiceImpl;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class EMRIntegratorController {
    @Autowired
    JeMPIServiceImpl jeMPIService;

    @Autowired
    ABISServiceImpl abisService;

    @GetMapping("/getclientbyglobalid" )
    public GoldenRecordDto getClientRegisterByGlobalID(@Valid @RequestBody GlobalNumber globalNumber){
        return jeMPIService.getClientByGlobalID(globalNumber);
    }

    @GetMapping("/getclientsbydfinger" )
    public GoldenRecordDto getClientRegisterByFinger(@Valid @RequestBody FingerDto fingerDto){
        String id = abisService.verifyFinger(fingerDto);
        GlobalNumber globalNumber = new GlobalNumber();
        globalNumber.setGlobalID(id);
        return jeMPIService.getClientByGlobalID(globalNumber);
    }

    
}
