package pl.wsb.licencjat.model.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Setter
public class TmdbMovie {

    private String id;
    private String title;
    private String tagline;
    private String overview;
    private String original_title;
    private String poster_path;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
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
