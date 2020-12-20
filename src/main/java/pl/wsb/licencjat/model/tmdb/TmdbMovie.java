package pl.wsb.licencjat.model.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import pl.wsb.licencjat.model.Media;
import pl.wsb.licencjat.model.enumerations.MediaType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TmdbMovie extends Media {

    private String title;
    private String tagline;
    private String overview;

    public TmdbMovie() {
        this.mediaType = MediaType.MOVIE;
    }

    @Override
    public String toString() {
        return "TMDBMovie{" +
                "title='" + title + '\'' +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
