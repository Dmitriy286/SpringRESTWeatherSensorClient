package org.weathersensor.SpringRESTClient;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.weathersensor.SpringRESTClient.restTemplateClient.RestTemplateService;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringRestClientApplication {
	private final RestTemplateService restTemplateService;

	public static void main(String[] args) {
		SpringApplication.run(SpringRestClientApplication.class, args);
	}

	@PostConstruct
	private void first() {
		restTemplateService.firstRestTemplateRequest();
	}

}
