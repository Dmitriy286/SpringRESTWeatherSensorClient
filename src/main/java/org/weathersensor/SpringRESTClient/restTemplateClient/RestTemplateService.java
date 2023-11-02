package org.weathersensor.SpringRESTClient.restTemplateClient;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public void firstRestTemplateRequest() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:6161/operators";

        String response = restTemplate.getForObject(url, String.class);

        System.out.println(response);
    }
}
