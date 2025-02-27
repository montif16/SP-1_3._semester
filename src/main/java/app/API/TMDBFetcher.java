package app.API;

import app.DTO.MovieDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TMDBFetcher {
    private static final String API_KEY = System.getenv("API_Key");
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static MovieDTO fetchMovieById(Long movieId) {
        String urlString = BASE_URL + movieId + "?api_key=" + API_KEY;

        try {
            // Open Connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Check Response
            if (conn.getResponseCode() != 200) {
                System.out.println("Failed to fetch data. HTTP Error Code: " + conn.getResponseCode());
                return null;
            }

            // Read Response
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder jsonResponse = new StringBuilder();
            while (scanner.hasNext()) {
                jsonResponse.append(scanner.nextLine());
            }
            scanner.close();

            // Convert JSON to DTO
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse.toString(), MovieDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Test fetching a movie (Example: Fight Club, ID = 550)
        MovieDTO movie = fetchMovieById(550L);

        if (movie != null) {
            System.out.println("Movie Title: " + movie.getTitle());
            System.out.println("Release Date: " + movie.getRelease_date());
            System.out.println("Overview: " + movie.getOverview());
            System.out.println("Rating: " + movie.getVote_average());
            System.out.println("Genres (IDs): " + movie.getGenres());
        } else {
            System.out.println("Failed to fetch movie.");
        }
    }
}
