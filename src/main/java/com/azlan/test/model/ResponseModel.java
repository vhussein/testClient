package com.azlan.test.model;

import java.util.Date;

public class ResponseModel {
    private Date timestamp;
    private String message;
    private String details;

    public ResponseModel(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
