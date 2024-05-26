package org.weathersensor.SpringRESTClient.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDto {

    private float value;

    private boolean raining;

    private SensorDto sensorDto;
}
