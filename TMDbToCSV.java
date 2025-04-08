import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.*;

public class TMDbToCSV {

    private static final String API_KEY = "d425fc45ef474b869de936521c835e0a";
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            FileWriter csvWriter = new FileWriter("movies.csv");
            csvWriter.append("Title,Release Date\n");

            for (JsonElement element : results) {
                JsonObject movie = element.getAsJsonObject();
                String title = movie.get("title").getAsString().replace(",", ""); // clean commas
                String releaseDate = movie.get("release_date").getAsString();

                csvWriter.append(title).append(",").append(releaseDate).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("CSV file created successfully.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
