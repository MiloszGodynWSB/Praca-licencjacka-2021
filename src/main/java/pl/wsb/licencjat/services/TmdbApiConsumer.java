package pl.wsb.licencjat.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.model.tmdb.TmdbSeries;

import java.util.ArrayList;
import java.util.List;

@Service
public class TmdbApiConsumer {

    @Value("${spring.tmdb.api.key}")
    private String API_KEY;
    @Value("${spring.tmdb.api.movie}")
    private String API_URL_MOVIE;
    @Value("${spring.tmdb.api.series}")
    private String API_URL_SERIES;
    @Value("${spring.tmdb.api.search-movie}")
    private String API_URL_SEARCH_MOVIE;
    @Value("${spring.tmdb.api.search-series}")
    private String API_URL_SEARCH_SERIES;
    private String API_OPTIONS = "?api_key=%s&language=en-US";
    private RestTemplate restTemplate;

    @Autowired
    public TmdbApiConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TmdbMovie getMovie(Long id) {
        return restTemplate.getForObject(buildUrlMovie(id.toString()), TmdbMovie.class);
    }

    public TmdbMovie getMovie(String id) {
        return restTemplate.getForObject(buildUrlMovie(id), TmdbMovie.class);
    }

    public TmdbSeries getSeries(Long id) {
        return restTemplate.getForObject(buildUrlSeries(id.toString()), TmdbSeries.class);
    }

    public TmdbSeries getSeries(String id) {
        return restTemplate.getForObject(buildUrlSeries(id), TmdbSeries.class);
    }

    private String buildUrlMovie(String id) {
        return API_URL_MOVIE + id + String.format(API_OPTIONS, API_KEY);
    }

    private String buildUrlSeries(String id) {
        return API_URL_SERIES + id + String.format(API_OPTIONS, API_KEY);
    }

    private String buildUrlSearchMovie(String query) {
        return API_URL_SEARCH_MOVIE  + String.format(API_OPTIONS, API_KEY) + "&query=" + query;
    }

    private String buildUrlSearchSeries(String query) {
        return API_URL_SEARCH_SERIES  + String.format(API_OPTIONS, API_KEY) + "&query=" + query;
    }

    @SneakyThrows
    public ArrayList<Long> SearchMovie(String query) {
        String builtQuery = buildUrlSearchMovie(query);
        var moviesResponse = restTemplate.exchange(
                builtQuery,
                HttpMethod.GET, null, String.class);

        String JsonString = moviesResponse.getBody();

        JSONObject jsnobj = new JSONObject(JsonString);
        JSONArray jsonArray =  jsnobj.getJSONArray("results");
        ArrayList<Long> movieIDs = new ArrayList<>();

        for(int i = 0; i <jsonArray.length(); i++) {
            movieIDs.add(jsonArray.getJSONObject(i).getLong("id"));
        }

        return movieIDs;
    }

    @SneakyThrows
    public ArrayList<Long> SearchSeries(String query) {
        String builtQuery = buildUrlSearchSeries(query);
        var seriesResponse = restTemplate.exchange(
                builtQuery,
                HttpMethod.GET, null, String.class);

        String JsonString = seriesResponse.getBody();

        JSONObject jsnobj = new JSONObject(JsonString);
        JSONArray jsonArray =  jsnobj.getJSONArray("results");
        ArrayList<Long> seriesIDs = new ArrayList<>();

        for(int i = 0; i <jsonArray.length(); i++) {
            seriesIDs.add(jsonArray.getJSONObject(i).getLong("id"));
        }

        return seriesIDs;
    }


}
