package pl.wsb.licencjat.model.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Setter
public class TmdbSeries {

    private String id;
    private String name;
    private String tagline;
    private String overview;
    private String original_name;
    private String poster_path;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public String getPoster_path() {
        return poster_path;
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
