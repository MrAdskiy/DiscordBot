package api;

import config.BotConfig;
import utils.FileManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class NasaApi {
    private static final String APOD_URL = "https://api.nasa.gov/planetary/apod";
    private static final String NASA_TOKEN = BotConfig.getNasaToken();

    public String getAPOD(int count) throws InterruptedException, IOException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        String url = APOD_URL + "?api_key=" + NASA_TOKEN + "&count=" + count;
        System.out.println(url);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        HttpHeaders headers = response.headers();

        if (headers.firstValue("X-RateLimit-Limit").isPresent()) {
            System.out.println("Есть информация о лимитах API: " + headers.firstValue("X-RateLimit-Remaining").orElse(null));
        }

        if (response.statusCode() != 200) {
            System.out.println(response.body());
            return response.body();
        } else if (response.statusCode() == 200) {
            FileManager.SaveJson("data/out.json", response.body());
        }
        return response.body();
    }
}
