package com.wemakesoftware.NavigationSystem.scheduler;

import com.wemakesoftware.NavigationSystem.repository.MobileStationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import com.wemakesoftware.NavigationSystem.utils.MobileStationLiveLocationUtils;


@Service
@AllArgsConstructor
@Slf4j
public class MobileStationLocationScheduler {

    private MobileStationRepository mobileStationRepository;

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    @Async
    public void run() {

        MobileStationLiveLocationUtils.mobileStationList = mobileStationRepository.findAll();

        MobileStationLiveLocationUtils.mobileStationList.forEach(mobileStation -> {
            mobileStation.setLastKnownX(MobileStationLiveLocationUtils.coordinatesGenerator());
            mobileStation.setLastKnownY(MobileStationLiveLocationUtils.coordinatesGenerator());
        });

        log.info("MobileStationLocationScheduler::Updated all MS locations");
    }
}