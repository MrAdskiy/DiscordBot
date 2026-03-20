package api;

import java.io.IOException;

public class ApiTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        NasaApi nasaApi = new NasaApi();
        String apod = nasaApi.getAPOD(1);
        System.out.println(apod);


    }
}
