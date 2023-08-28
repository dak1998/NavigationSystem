package com.wemakesoftware.NavigationSystem.service;

import com.wemakesoftware.NavigationSystem.dto.MobileStationLocationDto;
import com.wemakesoftware.NavigationSystem.model.MobileStation;
import com.wemakesoftware.NavigationSystem.repository.MobileStationRepository;
import com.wemakesoftware.NavigationSystem.service.impl.MobileStationServiceImpl;
import com.wemakesoftware.NavigationSystem.utils.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MobileStationServiceTest {

    @Mock
    private MobileStationRepository mobileStationRepositoryMock;

    @InjectMocks
    private MobileStationServiceImpl mobileStationServiceUnderTest;

    @Test
    public void getMobileStationLocationTest() {
        MobileStation mobileStation = mock(MobileStation.class);
        when(mobileStation.getId()).thenReturn("2cd9a039-de16-4bc0-91dc-2d3503cc4795");

        when(mobileStationRepositoryMock.getById("2cd9a039-de16-4bc0-91dc-2d3503cc4795")).thenReturn(mobileStation);

        String inputId = "2cd9a039-de16-4bc0-91dc-2d3503cc4795";
        MobileStationLocationDto response = mobileStationServiceUnderTest.getMobileStationLocation(inputId);

        //Asserting for a non-null response parsed in dto with correct MS id.
        Assertions.assertNotNull(response);
        Assertions.assertEquals(inputId, response.getMobileId());
    }

    @Test
    public void getMobileStationLocationTest_EntityNotFound() {
        MobileStation mobileStation = mock(MobileStation.class);

        //Simulating uuid of MS not found in database
        when(mobileStationRepositoryMock.getById(anyString())).thenThrow(new EntityNotFoundException());

        String inputId = "1";
        MobileStationLocationDto response = mobileStationServiceUnderTest.getMobileStationLocation(inputId);

        //Asserting error_101 description is returned after EntityNotFoundException thrown.
        Assertions.assertEquals(ErrorCode.error_101.getErrorCode(), response.getError_code());
        Assertions.assertEquals(ErrorCode.error_101.getErrorDescription(), response.getError_description());
    }

    @Test
    public void getMobileStationLocationTest_NullPointer() {

        //Simulating data not available in database
        when(mobileStationRepositoryMock.getById(anyString())).thenReturn(null);

        String inputId = "2cd9a039-de16-4bc0-91dc-2d3503cc4795";
        MobileStationLocationDto response = mobileStationServiceUnderTest.getMobileStationLocation(inputId);

        //Asserting error_102 description is returned after NullPointerException thrown.
        Assertions.assertEquals(ErrorCode.error_102.getErrorCode(), response.getError_code());
        Assertions.assertEquals(ErrorCode.error_102.getErrorDescription(), response.getError_description());
    }

}
