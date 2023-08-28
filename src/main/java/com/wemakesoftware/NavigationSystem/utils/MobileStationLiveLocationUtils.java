package com.wemakesoftware.NavigationSystem.utils;

import com.wemakesoftware.NavigationSystem.model.MobileStation;

import java.util.List;

public class MobileStationLiveLocationUtils {

    public static List<MobileStation> mobileStationList;

    public static float coordinatesGenerator() {
        return (float) Math.random() * (100);
    }
}
