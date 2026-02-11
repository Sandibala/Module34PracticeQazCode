package task2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class HttpService {
    private static final Logger logger = Logger.getLogger(HttpService.class.getName());
    private final HttpClient httpClient;

    public HttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("HTTP Response Code: " + response.statusCode());
        return response.body();
    }
}
