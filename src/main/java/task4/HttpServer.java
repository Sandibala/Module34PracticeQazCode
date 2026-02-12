package task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server is working on  http://localhost:8080");

        while (true) {
            Socket client = server.accept();
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream());

                    System.out.println("Request: " + in.readLine());

                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/plain");
                    out.println();
                    out.println("Hello, World!");
                    out.flush();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

