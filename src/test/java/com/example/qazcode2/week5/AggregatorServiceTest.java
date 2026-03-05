package com.example.qazcode2.week5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import week5.model.Result;
import week5.service.AggregatorService;
import week5.service.HttpClientService;
import week5.model.HttpResult;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AggregatorServiceTest {

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(2);

    @AfterEach
    void tearDown() {
        executorService.shutdownNow();
    }

    @Test
    void shouldAggregateSuccessAndErrorsCorrectly() {
        HttpClientService httpClientService = mock(HttpClientService.class);

        when(httpClientService.sendGet("url1"))
                .thenReturn(new HttpResult(true, 100));
        when(httpClientService.sendGet("url2"))
                .thenReturn(new HttpResult(false, 200));
        when(httpClientService.sendGet("url3"))
                .thenReturn(new HttpResult(true, 300));

        AggregatorService aggregatorService =
                new AggregatorService(httpClientService, executorService);

        Result result = aggregatorService.aggregate(
                List.of("url1", "url2", "url3")
        );

        assertEquals(2, result.getSuccessCount());
        assertEquals(1, result.getErrorCount());
        assertEquals(200.0, result.getAverageResponseTime());

        verify(httpClientService, times(3)).sendGet(anyString());
    }
}
