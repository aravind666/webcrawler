package com.aravindhu.webcrawler.webcrawler.model.response;

public class ErrorResponse {

    private String exception;
    private StackTraceElement[] stackTraceElements;


    public ErrorResponse(String exception, StackTraceElement[] stackTraceElements) {
        this.exception = exception;
        this.stackTraceElements = stackTraceElements;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public StackTraceElement[] getStackTraceElements() {
        return stackTraceElements;
    }

    public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }
}
