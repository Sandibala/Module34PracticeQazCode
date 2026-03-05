package com.example.qazcode2.week5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import week5.service.HttpClientService;
import week5.model.HttpResult;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HttpClientServiceTest {
    @Mock
    HttpClient httpClient;

    @Mock
    HttpResponse<String> httpResponse;

    @Test
    void shouldReturnSuccessWhenStatus200() throws Exception {
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpClient.send(
                ArgumentMatchers.any(HttpRequest.class),
                ArgumentMatchers.any(HttpResponse.BodyHandler.class)
        )).thenReturn(httpResponse);

        HttpClientService service = new HttpClientService(httpClient);

        HttpResult result = service.sendGet("https://test.com");

        assertTrue(result.isSuccess());
        assertTrue(result.getDuration() >= 0);
    }

    @Test
    void shouldReturnErrorWhenExceptionThrown() throws Exception {
        when(httpClient.send(
                any(HttpRequest.class),
                any(HttpResponse.BodyHandler.class)
        )).thenThrow(new IOException("Network error"));

        HttpClientService service = new HttpClientService(httpClient);

        HttpResult result = service.sendGet("https://test.com");

        assertFalse(result.isSuccess());
        assertTrue(result.getDuration() >= 0);
    }
}
