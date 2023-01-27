package pims.integrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pims.integrator.dto.GlobalNumber;
import pims.integrator.dto.GoldenRecord;
import pims.integrator.dto.GoldenRecordWrapperDto;
import reactor.core.publisher.Mono;
@Service
public class JeMPIServiceImpl {

    private final WebClient webClient;

    @Autowired
    public JeMPIServiceImpl() {
        this.webClient = WebClient.create("http://10.10.100.43:50000/JeMPI");
    }

    public GoldenRecordWrapperDto getClientByGlobalID(GlobalNumber globalNumber){
        return webClient.get().uri("/GoldenRecord?uid="+globalNumber.getGlobalID())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve().bodyToMono(GoldenRecordWrapperDto.class).block();
    }
}
