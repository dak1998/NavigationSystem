package com.wemakesoftware.NavigationSystem.service.impl;

import com.wemakesoftware.NavigationSystem.dto.Report;
import com.wemakesoftware.NavigationSystem.dto.ReportMSDto;
import com.wemakesoftware.NavigationSystem.exception.MobileStationDetectionException;
import com.wemakesoftware.NavigationSystem.model.BaseStation;
import com.wemakesoftware.NavigationSystem.model.MobileStation;
import com.wemakesoftware.NavigationSystem.repository.BaseStationRepository;
import com.wemakesoftware.NavigationSystem.repository.MobileStationRepository;
import com.wemakesoftware.NavigationSystem.service.BaseStationService;
import com.wemakesoftware.NavigationSystem.utils.BaseStationUtils;
import com.wemakesoftware.NavigationSystem.utils.ErrorCode;
import com.wemakesoftware.NavigationSystem.utils.MobileStationLiveLocationUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BaseStationServiceImpl implements BaseStationService {

    private BaseStationRepository baseStationRepository;

    private MobileStationRepository mobileStationRepository;

    @Override
    public List<ReportMSDto> reportDetectedMS() {

        try {
            List<BaseStation> baseStationList = baseStationRepository.findAll();

            List<ReportMSDto> reportMSDtoList = new ArrayList<>();

            baseStationList
                    .forEach(baseStation -> {
                        List<Report> reports = new ArrayList<>();
                        List<MobileStation> mobileStationList = MobileStationLiveLocationUtils.mobileStationList;
                        mobileStationList
                                .forEach(mobileStation -> {

                                    double distance = BaseStationUtils.distanceIfMSInRange(baseStation, mobileStation);
                                    if (distance != -1) {
                                        reports.add(Report.builder()
                                                .mobileStationId(mobileStation.getId())
                                                .distance((float) distance)
                                                .timestamp(new Date())
                                                .build());
                                    }
                                });
                        if (!CollectionUtils.isEmpty(reports)) {
                            reportMSDtoList.add(ReportMSDto.builder()
                                    .baseStationId(baseStation.getId())
                                    .reports(reports)
                                    .build());
                        }
                    });

            log.info("Base Station Detection completed. Report size: {}", reportMSDtoList.size());

            CompletableFuture.runAsync(() -> updateMobileStationInDB(reportMSDtoList));

            return reportMSDtoList;

        } catch (Exception e) {
            log.error("Error while detecting MS: ", e);
            throw new MobileStationDetectionException("Error " + ErrorCode.error_103.getErrorCode() + ": " + ErrorCode.error_103.getErrorDescription());
        }

    }

    private void updateMobileStationInDB(List<ReportMSDto> reportMSDtoList) {
        List<String> mobileStationIdsToUpdate = reportMSDtoList.stream()
                .flatMap(reportMSDto -> reportMSDto.getReports().stream().map(Report::getMobileStationId))
                .collect(Collectors.toList());

        MobileStationLiveLocationUtils.mobileStationList.stream()
                .filter(mobileStation -> mobileStationIdsToUpdate.contains(mobileStation.getId()))
                .forEach(mobileStation -> {
                    mobileStationRepository.updateCoordinates(mobileStation.getId(), mobileStation.getLastKnownX(), mobileStation.getLastKnownY());
                });

        log.info("Updated Database with detected MS new location. Updated {} MS", mobileStationIdsToUpdate.size());
    }


}
