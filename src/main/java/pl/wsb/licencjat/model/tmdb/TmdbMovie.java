package pl.wsb.licencjat.model.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter
@Setter
public class TmdbMovie {

    private String title;
    private String tagline;
    private String overview;

    @Override
    public String toString() {
        return "TMDBMovie{" +
                "title='" + title + '\'' +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}
