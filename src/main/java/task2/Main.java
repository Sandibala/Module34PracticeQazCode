package task2;

import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpService httpService = new HttpService(httpClient);
        try {
            String response = httpService.get("https://jsonplaceholder.typicode.com/posts/1");
            System.out.println("Response body:");
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
