package pl.wsb.licencjat.model.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class TmdbSeries {

    private String name;
    private String tagline;
    private String overview;

    @Override
    public String toString() {
        return "TMDBMovie{" +
                "title='" + name + '\'' +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
