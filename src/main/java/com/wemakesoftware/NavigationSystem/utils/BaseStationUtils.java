package com.wemakesoftware.NavigationSystem.utils;

import com.wemakesoftware.NavigationSystem.model.BaseStation;
import com.wemakesoftware.NavigationSystem.model.MobileStation;

public class BaseStationUtils {

    public static double distanceIfMSInRange(BaseStation baseStation, MobileStation mobileStation) {
        double distance = distanceBetweenBSAndMS(baseStation.getX(), baseStation.getY(), mobileStation.getLastKnownX(), mobileStation.getLastKnownY());

        if ( distance <= baseStation.getDetectionRadiusInMeters() )
            return distance;
        else
            return -1;
    }

    public static double distanceBetweenBSAndMS(double baseStationX, double baseStationY, double mobileStationX, double mobileStationY) {
        return Math.sqrt(
                ( squareOf (mobileStationX - baseStationX) )
                        + ( squareOf(mobileStationY - baseStationY) )
        );
    }

    public static double squareOf(double input) {
        return Math.pow(input, 2.0);
    }
}
