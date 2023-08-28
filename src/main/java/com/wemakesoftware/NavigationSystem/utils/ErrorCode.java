package com.wemakesoftware.NavigationSystem.utils;

public enum ErrorCode {

    error_101(101, "Provided uuid is invalid. No MS with provided uuid found."),
    error_102(102, "Error while fetching details from database."),
    error_103(103, "Error while detecting Mobile stations");
    private final int errorCode;
    private final String errorDescription;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    ErrorCode(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
