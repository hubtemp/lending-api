package com.company.domain.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RequestError {

    E1("E1", "System error", HttpStatus.INTERNAL_SERVER_ERROR),
    E2("E2", "Invalid session access token", HttpStatus.FORBIDDEN),
    E3("E3", "Loan not found", HttpStatus.NOT_FOUND),
    E4("E4", "Invalid loan application details", HttpStatus.BAD_REQUEST),
    E5("E5", "Loan application rejected because of high risk", HttpStatus.OK);

    @JsonIgnore
    private HttpStatus httpStatus;
    private int status;
    private String code;
    private String message;

    RequestError(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
