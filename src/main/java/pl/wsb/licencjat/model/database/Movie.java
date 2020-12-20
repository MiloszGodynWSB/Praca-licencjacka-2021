package pl.wsb.licencjat.model.database;

import pl.wsb.licencjat.model.Media;
import pl.wsb.licencjat.model.enumerations.MediaType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie extends Media {

    @Id
    @Column(name = "movieID")
    private long id;
    private int Action;
    private int Adventure;
    private int Comedy;
    private int Crime;
    private int Documentary;
    private int Drama;
    private int Family;
    private int Fantasy;
    private int History;
    private int Horror;
    private int Music;
    private int Mystery;
    private int Romance;
    private int ScienceFiction;
    private int TvMovie;
    private int thriller;
    private int War;
    private int Western;

    public Movie() {
        this.mediaType = MediaType.MOVIE;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + id +
                ", action=" + Action +
                ", adventure=" + Adventure +
                ", comedy=" + Comedy +
                ", crime=" + Crime +
                ", documentary=" + Documentary +
                ", drama=" + Drama +
                ", family=" + Family +
                ", fantasy=" + Fantasy +
                ", history=" + History +
                ", horror=" + Horror +
                ", music=" + Music +
                ", mystery=" + Mystery +
                ", romance=" + Romance +
                ", scienceFiction=" + ScienceFiction +
                ", tvMovie=" + TvMovie +
                ", thriller=" + thriller +
                ", war=" + War +
                ", western=" + Western +
                ", mediaType=" + mediaType +
                '}';
    }
}
