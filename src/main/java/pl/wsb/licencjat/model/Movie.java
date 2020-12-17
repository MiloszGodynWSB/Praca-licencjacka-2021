package pl.wsb.licencjat.model;

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
    private int action;
    private int adventure;
    private int comedy;
    private int crime;
    private int documentary;
    private int drama;
    private int family;
    private int fantasy;
    private int history;
    private int horror;
    private int music;
    private int mystery;
    private int romance;
    private int scienceFiction;
    private int tvMovie;
    private int thriller;
    private int war;
    private int western;

    public Movie() {
        this.mediaType = MediaType.MOVIE;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + id +
                ", action=" + action +
                ", adventure=" + adventure +
                ", comedy=" + comedy +
                ", crime=" + crime +
                ", documentary=" + documentary +
                ", drama=" + drama +
                ", family=" + family +
                ", fantasy=" + fantasy +
                ", history=" + history +
                ", horror=" + horror +
                ", music=" + music +
                ", mystery=" + mystery +
                ", romance=" + romance +
                ", scienceFiction=" + scienceFiction +
                ", tvMovie=" + tvMovie +
                ", thriller=" + thriller +
                ", war=" + war +
                ", western=" + western +
                ", mediaType=" + mediaType +
                '}';
    }
}
