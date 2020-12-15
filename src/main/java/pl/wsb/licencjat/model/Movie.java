package pl.wsb.licencjat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @Column(name = "movieID")
    private long id;
    private String imdbID;
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

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + id +
                ", imdbID='" + imdbID + '\'' +
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
                '}';
    }
}
