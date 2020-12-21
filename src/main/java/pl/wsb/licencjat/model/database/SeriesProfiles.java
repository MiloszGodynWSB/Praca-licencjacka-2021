package pl.wsb.licencjat.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seriesprofiles")
public class SeriesProfiles {

    @Id
    @Column(name = "userID")
    public long userID;
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
}
