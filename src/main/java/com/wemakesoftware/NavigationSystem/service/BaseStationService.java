package com.wemakesoftware.NavigationSystem.service;

import com.wemakesoftware.NavigationSystem.dto.ReportMSDto;
import com.wemakesoftware.NavigationSystem.model.BaseStation;
import com.wemakesoftware.NavigationSystem.model.MobileStation;

import java.util.List;

public interface BaseStationService {

    List<ReportMSDto> reportDetectedMS();

}
