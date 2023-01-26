package pims.integrator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pims.integrator.dto.GlobalNumber;
import pims.integrator.dto.GoldenRecordDto;
import reactor.core.publisher.Mono;
@Service
public class JeMPIServiceImpl {

    private final WebClient webClient;

    @Autowired
    public JeMPIServiceImpl() {
        this.webClient = WebClient.create("http://10.10.100.43:50000/JeMPI");
    }

    public GoldenRecordDto getClientByGlobalID(GlobalNumber globalNumber){
        return webClient.post().uri("/GoldenRecord?uid="+globalNumber.getGlobalID())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(globalNumber), GlobalNumber.class)
                .retrieve().bodyToMono(GoldenRecordDto.class).block();
    }
}
