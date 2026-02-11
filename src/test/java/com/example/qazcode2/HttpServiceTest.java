package com.example.qazcode2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task2.HttpService;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HttpServiceTest {
    private HttpClient httpClient;
    private HttpService httpService;

    @BeforeEach
    void setUp() {
        httpClient = mock(HttpClient.class);
        httpService = new HttpService(httpClient);
    }

    @Test
    void testGetReturnsBody() throws IOException, InterruptedException {
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.body()).thenReturn("Hello World");
        when(response.statusCode()).thenReturn(200);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        String result = httpService.get("http://example.com");
        assertEquals("Hello World", result);
        verify(httpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        assertEquals(200, response.statusCode());
    }
}
