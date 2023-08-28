package com.wemakesoftware.NavigationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MobileStationLocationDto {

    private String mobileId;

    private float x;

    private float y;

    private float errorRadius;

    private int error_code;

    private String error_description;
}
