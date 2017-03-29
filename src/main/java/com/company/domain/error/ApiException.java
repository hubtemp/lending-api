package com.company.domain.error;

public class ApiException extends RuntimeException {

    private RequestError requestError;

    public ApiException(RequestError requestError) {
        this.requestError = requestError;
    }

    public RequestError getRequestError() {
        return requestError;
    }

}
