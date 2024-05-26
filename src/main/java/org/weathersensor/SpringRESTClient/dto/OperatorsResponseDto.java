package org.weathersensor.SpringRESTClient.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OperatorsResponseDto {

    private List<OperatorDto> operatorDtos;
}
