package org.weathersensor.SpringRESTClient;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.weathersensor.SpringRESTClient.chart.Chart;
import org.weathersensor.SpringRESTClient.client.resttemplate.RestTemplateService;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringRestClientApplication {
	private final RestTemplateService restTemplateService;
	private final Chart chart;

	public static void main(String[] args) {
//		SpringApplication.run(SpringRestClientApplication.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringRestClientApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@PostConstruct
	private void first() {
//		restTemplateService.firstRestTemplateRequest();
//		restTemplateService.secondRestTemplateRequest();
//		restTemplateService.thirdRestTemplateRequest();
		restTemplateService.getAllMeasurements();

//		restTemplateService.fifthRestTemplateRequest();
		chart.createChart();
	}

}
