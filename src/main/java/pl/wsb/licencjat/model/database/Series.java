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
    @Column(name = "seriesID")
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

    public long getId() {
        return id;
    }

    public int getAction() {
        return action;
    }

    public int getAdventure() {
        return adventure;
    }

    public int getAnimation() {
        return animation;
    }

    public int getComedy() {
        return comedy;
    }

    public int getCrime() {
        return crime;
    }

    public int getDocumentary() {
        return documentary;
    }

    public int getDrama() {
        return drama;
    }

    public int getFamily() {
        return family;
    }

    public int getFantasy() {
        return fantasy;
    }

    public int getHistory() {
        return history;
    }

    public int getHorror() {
        return horror;
    }

    public int getKids() {
        return kids;
    }

    public int getMusic() {
        return music;
    }

    public int getMystery() {
        return mystery;
    }

    public int getNews() {
        return news;
    }

    public int getRomance() {
        return romance;
    }

    public int getScienceFiction() {
        return scienceFiction;
    }

    public int getReality() {
        return reality;
    }

    public int getSoap() {
        return soap;
    }

    public int getThriller() {
        return thriller;
    }

    public int getTalk() {
        return talk;
    }

    public int getTvMovie() {
        return tvMovie;
    }

    public int getWar() {
        return war;
    }

    public int getWestern() {
        return western;
    }

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
