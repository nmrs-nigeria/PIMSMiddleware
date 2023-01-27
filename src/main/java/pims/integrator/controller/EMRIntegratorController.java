package pims.integrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pims.integrator.dto.FingerDto;
import pims.integrator.dto.GlobalNumber;
import pims.integrator.dto.GoldenRecord;
import pims.integrator.dto.GoldenRecordWrapperDto;
import pims.integrator.service.impl.ABISServiceImpl;
import pims.integrator.service.impl.JeMPIServiceImpl;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pims/")
public class EMRIntegratorController {
    @Autowired
    JeMPIServiceImpl jeMPIService;

    @Autowired
    ABISServiceImpl abisService;

    @GetMapping("/getclientbyglobalid" )
    public GoldenRecordWrapperDto getClientRegisterByGlobalID(@Valid @RequestBody GlobalNumber globalNumber){
        return jeMPIService.getClientByGlobalID(globalNumber);
    }

    @GetMapping("/getclientsbydfinger" )
    public GoldenRecordWrapperDto getClientRegisterByFinger(@Valid @RequestBody FingerDto fingerDto){
        String id = abisService.verifyFinger(fingerDto);
        GlobalNumber globalNumber = new GlobalNumber();
        globalNumber.setGlobalID(id);
        return jeMPIService.getClientByGlobalID(globalNumber);
    }

    
}
