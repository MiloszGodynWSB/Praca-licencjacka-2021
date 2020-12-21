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
    private long userID;
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
}
