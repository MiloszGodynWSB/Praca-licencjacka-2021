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
    public long id;
    public int Action;
    public int Adventure;
    public int Animation;
    public int Comedy;
    public int Crime;
    public int Documentary;
    public int Drama;
    public int Family;
    public int Fantasy;
    public int History;
    public int Horror;
    public int Music;
    public int Mystery;
    public int Romance;
    public int ScienceFiction;
    public int TvMovie;
    public int thriller;
    public int War;
    public int Western;

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
