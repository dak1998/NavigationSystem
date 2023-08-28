package com.wemakesoftware.NavigationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Report {

    private String mobileStationId;

    private float distance;

    private Date timestamp;
}
