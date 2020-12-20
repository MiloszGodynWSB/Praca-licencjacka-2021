package pl.wsb.licencjat.model.database;

import pl.wsb.licencjat.model.Media;
import pl.wsb.licencjat.model.enumerations.MediaType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "series")
public class Series extends Media {

    @Id
    @Column(name = "movieID")
    private long id;
    private int action;
    private int adventure;
    private int animation;
    private int comedy;
    private int crime;
    private int documentary;
    private int drama;
    private int family;
    private int fantasy;
    private int history;
    private int horror;
    private int kids;
    private int music;
    private int mystery;
    private int news;
    private int romance;
    private int scienceFiction;
    private int reality;
    private int soap;
    private int thriller;
    private int talk;
    private int tvMovie;
    private int war;
    private int western;

    public Series() {
        this.mediaType = MediaType.SERIES;
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", action=" + action +
                ", adventure=" + adventure +
                ", animation=" + animation +
                ", comedy=" + comedy +
                ", crime=" + crime +
                ", documentary=" + documentary +
                ", drama=" + drama +
                ", family=" + family +
                ", fantasy=" + fantasy +
                ", history=" + history +
                ", horror=" + horror +
                ", kids=" + kids +
                ", music=" + music +
                ", mystery=" + mystery +
                ", news=" + news +
                ", romance=" + romance +
                ", scienceFiction=" + scienceFiction +
                ", reality=" + reality +
                ", soap=" + soap +
                ", thriller=" + thriller +
                ", talk=" + talk +
                ", tvMovie=" + tvMovie +
                ", war=" + war +
                ", western=" + western +
                ", mediaType=" + mediaType +
                '}';
    }
}