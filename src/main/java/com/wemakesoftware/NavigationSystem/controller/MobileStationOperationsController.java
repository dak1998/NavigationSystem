package com.wemakesoftware.NavigationSystem.controller;

import com.wemakesoftware.NavigationSystem.dto.MobileStationLocationDto;
import com.wemakesoftware.NavigationSystem.service.MobileStationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/mobileStation/")
@AllArgsConstructor
public class MobileStationOperationsController {

    MobileStationService mobileStationService;

    @GetMapping("location/{uuid}")
    public MobileStationLocationDto getMobileStationLocation(@PathVariable String uuid) {
        return mobileStationService.getMobileStationLocation(uuid);
    }
}
