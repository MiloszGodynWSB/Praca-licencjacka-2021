package pl.wsb.licencjat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.wsb.licencjat.model.tmdb.TmdbMovie;
import pl.wsb.licencjat.model.tmdb.TmdbSeries;

@Service
public class TmdbApiConsumer {

    @Value("${spring.tmdb.api.key}")
    private String API_KEY;
    @Value("${spring.tmdb.api.movie}")
    private String API_URL_MOVIE;
    @Value("${spring.tmdb.api.series}")
    private String API_URL_SERIES;
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

}
