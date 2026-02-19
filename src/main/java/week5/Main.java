package week5;

import week5.model.Result;
import week5.service.AggregatorService;
import week5.service.HttpClientService;

import java.net.http.HttpClient;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpClientService httpClientService = new HttpClientService(httpClient);
        AggregatorService aggregatorService = new AggregatorService(
                httpClientService,
                Executors.newFixedThreadPool(3)
        );

                List<String> urls = List.of(
                        "https://google.com",
                        "https://github.com",
                        "https://bad-url.test"
                );

        Result result = aggregatorService.aggregate(urls);
        System.out.println(result);

        aggregatorService.shutdown();
    }
}
