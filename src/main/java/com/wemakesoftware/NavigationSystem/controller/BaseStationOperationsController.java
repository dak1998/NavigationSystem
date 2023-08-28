package com.wemakesoftware.NavigationSystem.controller;

import com.wemakesoftware.NavigationSystem.dto.ReportMSDto;
import com.wemakesoftware.NavigationSystem.service.BaseStationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/baseStation/")
@AllArgsConstructor
public class BaseStationOperationsController {

    private BaseStationService baseStationService;

    @GetMapping("report")
    public List<ReportMSDto> reportDetectedMS() {
        return baseStationService.reportDetectedMS();
    }
}
