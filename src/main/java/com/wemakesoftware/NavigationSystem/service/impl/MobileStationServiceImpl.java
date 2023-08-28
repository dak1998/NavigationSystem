package com.wemakesoftware.NavigationSystem.service.impl;

import com.wemakesoftware.NavigationSystem.dto.MobileStationLocationDto;
import com.wemakesoftware.NavigationSystem.model.MobileStation;
import com.wemakesoftware.NavigationSystem.repository.MobileStationRepository;
import com.wemakesoftware.NavigationSystem.service.MobileStationService;
import com.wemakesoftware.NavigationSystem.utils.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
@Slf4j
public class MobileStationServiceImpl implements MobileStationService {

    private MobileStationRepository mobileStationRepository;

    @Override
    public MobileStationLocationDto getMobileStationLocation(String id) {
        try {
            MobileStation mobileStation = mobileStationRepository.getById(id);
            return MobileStationLocationDto.builder()
                    .mobileId(mobileStation.getId())
                    .x(mobileStation.getLastKnownX())
                    .y(mobileStation.getLastKnownY())
                    .build();
        } catch (EntityNotFoundException e) {
            log.error("MS with given id not found: ", e);
            return MobileStationLocationDto.builder()
                    .error_code(ErrorCode.error_101.getErrorCode())
                    .error_description(ErrorCode.error_101.getErrorDescription())
                    .build();
        } catch (Exception e) {
            log.error("Error while fetching MS from Database: ", e);
            return MobileStationLocationDto.builder()
                    .error_code(ErrorCode.error_102.getErrorCode())
                    .error_description(ErrorCode.error_102.getErrorDescription())
                    .build();
        }
    }
}
