package com.sultanseidov.locationforecast.repository.model;

public class LoadErrorTypeModel {
    private boolean isError;
    private Throwable throwable;

    public LoadErrorTypeModel(boolean isError, Throwable throwable) {
        this.isError = isError;
        this.throwable = throwable;
    }

    public LoadErrorTypeModel(boolean isError) {
        this.isError = isError;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
