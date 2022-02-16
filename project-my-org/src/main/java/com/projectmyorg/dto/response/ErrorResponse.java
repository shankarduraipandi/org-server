package com.projectmyorg.dto.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shankar D
 *
 */
public class ErrorResponse {

    private Boolean status;
    private String message;
    private List<String> details;

    public ErrorResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
        this.details = new ArrayList<>();
    }

    public ErrorResponse(Boolean status, String message, List<String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
