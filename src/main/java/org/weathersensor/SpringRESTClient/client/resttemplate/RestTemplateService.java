package org.weathersensor.SpringRESTClient.client.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.weathersensor.SpringRESTClient.dto.MeasurementDto;
import org.weathersensor.SpringRESTClient.dto.OperatorsResponseDto;
import org.weathersensor.SpringRESTClient.dto.SensorDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public void firstRestTemplateRequest() {

        String url = "http://localhost:6161/operators";

        String response = restTemplate.getForObject(url, String.class);

        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode object = null;

        try {
            object = mapper.readTree(response);
        } catch (JsonProcessingException e) {
//            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        System.out.println("Operator names:");
//        System.out.println(object);
        object.forEach(operator -> System.out.println(operator.get("name")));
//        System.out.println(object.get(0).get("name"));
//        object.get()

        OperatorsResponseDto mappedResponse = restTemplate.getForObject(url, OperatorsResponseDto.class);

        System.out.println("Mapped response:");
        mappedResponse.getOperatorDtos().forEach(operatorDto -> {
            System.out.println(operatorDto.getName());
            System.out.println(operatorDto.getPersonalNumber());
        });

    }

    public void secondRestTemplateRequest() {

        String url = "http://localhost:6161/sensors/registration";

        Random random = new Random();

        Map<String, String> jsonForRequest = new HashMap<>();
        jsonForRequest.put("name", "Sensor " + random.nextInt());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonForRequest);

        String response = restTemplate.postForObject(url, request, String.class);

        System.out.println("Response: " + response);

    }

    public void thirdRestTemplateRequest() {

        String url = "http://localhost:6161/operators";

        Random random = new Random();

        Map<String, String> jsonForRequest = new HashMap<>();
        jsonForRequest.put("name", "Operator " + random.nextInt());
        jsonForRequest.put("personalNumber", String.valueOf(random.nextInt()));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonForRequest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Response: " + response.getBody());
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Headers: " + response.getHeaders());

    }

    public void fourthRestTemplateRequest() {

        String url = "http://localhost:6161/measurements/add";

        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            Map<String, String> jsonForRequest = new HashMap<>();
            jsonForRequest.put("value", String.valueOf(random.nextInt(-30, 35)));
            jsonForRequest.put("raining", String.valueOf(random.nextBoolean()));

            Map<String, String> subJson = new HashMap<>();
            subJson.put("name", "Sensor 903448635");

            jsonForRequest.put("sensorDto", subJson.toString());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonForRequest, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Response: " + response.getBody());
            System.out.println("Status: " + response.getStatusCode());

        }

    }

    public void getAllMeasurements() {

        String url = "http://localhost:6161/measurements";

        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = null;

        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        jsonNode.forEach(System.out::println);

    }

    public void fifthRestTemplateRequest() {

        String url = "http://localhost:6161/measurements/add-all";

        Random random = new Random();

        List<MeasurementDto> objectsForRequest = new ArrayList<>();


        for (int i = 0; i < 1000; i++) {

            var sensorDto = new SensorDto();
            sensorDto.setName("Sensor 903448635");

            var measurementDto = new MeasurementDto();
            measurementDto.setValue(random.nextInt(-30, 35));
            measurementDto.setRaining(random.nextBoolean());
            measurementDto.setSensorDto(sensorDto);

            objectsForRequest.add(measurementDto);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        HttpEntity<List<MeasurementDto>> request = new HttpEntity<>(objectsForRequest, headers);

        MeasurementDto[] request = objectsForRequest.toArray(new MeasurementDto[objectsForRequest.size()]);
        String response = restTemplate.postForObject(url, request, String.class);


//        MyObject[] response = restTemplate.postForObject(url, request, MyObject[].class);

        System.out.println("response: " + response);

//        System.out.println("Response: " + response.getBody());
//        System.out.println("Status: " + response.getStatusCode());
    }
}
