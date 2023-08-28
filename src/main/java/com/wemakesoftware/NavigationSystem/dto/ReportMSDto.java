package com.wemakesoftware.NavigationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ReportMSDto {

    private String baseStationId;

    private List<Report> reports;
}
