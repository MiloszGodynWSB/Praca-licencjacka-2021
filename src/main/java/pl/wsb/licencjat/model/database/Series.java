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
    public long id;
    public int action;
    public int adventure;
    public int animation;
    public int comedy;
    public int crime;
    public int documentary;
    public int drama;
    public int family;
    public int fantasy;
    public int history;
    public int horror;
    public int kids;
    public int music;
    public int mystery;
    public int news;
    public int romance;
    public int scienceFiction;
    public int reality;
    public int soap;
    public int thriller;
    public int talk;
    public int tvMovie;
    public int war;
    public int western;

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
