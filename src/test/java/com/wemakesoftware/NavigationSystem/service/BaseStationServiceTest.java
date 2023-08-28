package com.wemakesoftware.NavigationSystem.service;

import com.wemakesoftware.NavigationSystem.dto.ReportMSDto;
import com.wemakesoftware.NavigationSystem.exception.MobileStationDetectionException;
import com.wemakesoftware.NavigationSystem.model.BaseStation;
import com.wemakesoftware.NavigationSystem.model.MobileStation;
import com.wemakesoftware.NavigationSystem.repository.BaseStationRepository;
import com.wemakesoftware.NavigationSystem.repository.MobileStationRepository;
import com.wemakesoftware.NavigationSystem.service.impl.BaseStationServiceImpl;
import com.wemakesoftware.NavigationSystem.utils.ErrorCode;
import com.wemakesoftware.NavigationSystem.utils.MobileStationLiveLocationUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BaseStationServiceTest {

    @Mock
    private BaseStationRepository baseStationRepositoryMock;

    @InjectMocks
    private BaseStationServiceImpl baseStationServiceUnderTest;


    @Test
    public void testReportDetectedMS() {
        BaseStation baseStation = mock(BaseStation.class);
        when(baseStationRepositoryMock.findAll()).thenReturn(Collections.singletonList(baseStation));

        MobileStation mobileStation = mock(MobileStation.class);
        MobileStationLiveLocationUtils.mobileStationList = Collections.singletonList(mobileStation);
        List<ReportMSDto> response = baseStationServiceUnderTest.reportDetectedMS();

        //Mock object sets both MS and BS at (0,0) with radius 0.0 and hence BS detects the MS
        //Asserting 1 MS detection report was generated
        assertEquals(1, response.size());
    }

    @Test
    public void testReportDetectedMS_error() {
        when(baseStationRepositoryMock.findAll()).thenReturn(null);

        //Simulating error while fetching from database
        //If null response is received from database, we get a null pointer exception Handled by our custom MobileStationDetectionException
        MobileStationDetectionException exception = assertThrows(MobileStationDetectionException.class, () -> baseStationServiceUnderTest.reportDetectedMS());

        String expectedMessage = ErrorCode.error_103.getErrorDescription();
        String actualMessage = exception.getMessage();

        //Asserting if error_103 message has been received in exception thrown by method
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
