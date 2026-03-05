package week5.service;

import week5.model.HttpResult;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

public class HttpClientService {
  private static final Logger logger = Logger.getLogger(HttpClientService.class.getName());
  private final HttpClient httpClient;

  public HttpClientService(HttpClient httpClient){
      this.httpClient = httpClient;
  }

  public HttpResult sendGet(String url){
   long start = System.currentTimeMillis();
   logger.info(() -> "Start request:" + url);
      HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url))
              .timeout(Duration.ofSeconds(10))
              .GET()
              .build();

      try{
          HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
          long duration = System.currentTimeMillis() - start;
          logger.info(() -> "Finished request: " + url + " code " + response.statusCode() );
          return new HttpResult(response.statusCode() == 200, duration);

      }
      catch (IOException | InterruptedException e){
          long duration = System.currentTimeMillis() - start;
          logger.severe(() -> "Error Request: " + url + " message " + e.getMessage());
          return new HttpResult(false, duration);
      }
  }
}
