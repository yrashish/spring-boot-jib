package com.jib.example.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ExceptionHandler implements ResponseErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            LOGGER.error(httpResponse.getStatusText());
        } else if (httpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            LOGGER.error(httpResponse.getStatusText());
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                LOGGER.error(httpResponse.getStatusText());            }
        }
    }
}
