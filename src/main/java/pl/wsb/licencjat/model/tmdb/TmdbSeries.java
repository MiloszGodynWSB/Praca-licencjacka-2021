package pl.wsb.licencjat.model.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import pl.wsb.licencjat.model.Media;
import pl.wsb.licencjat.model.enumerations.MediaType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TmdbSeries extends Media {

    private String name;
    private String tagline;
    private String overview;

    public TmdbSeries() {
        this.mediaType = MediaType.SERIES;
    }

    @Override
    public String toString() {
        return "TMDBMovie{" +
                "title='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
