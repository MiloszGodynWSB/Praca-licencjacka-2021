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

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void increaseAction(int action) {
        this.action += action;
    }

    public void increaseAdventure(int adventure) {
        this.adventure += adventure;
    }

    public void increaseAnimation(int animation) {
        this.animation += animation;
    }

    public void increaseComedy(int comedy) {
        this.comedy += comedy;
    }

    public void increaseCrime(int crime) {
        this.crime += crime;
    }

    public void increaseDocumentary(int documentary) {
        this.documentary += documentary;
    }

    public void increaseDrama(int drama) {
        this.drama += drama;
    }

    public void increaseFamily(int family) {
        this.family += family;
    }

    public void increaseFantasy(int fantasy) {
        this.fantasy += fantasy;
    }

    public void increaseHistory(int history) {
        this.history += history;
    }

    public void increaseHorror(int horror) {
        this.horror += horror;
    }

    public void increaseKids(int kids) {
        this.kids += kids;
    }

    public void increaseMusic(int music) {
        this.music += music;
    }

    public void increaseMystery(int mystery) {
        this.mystery += mystery;
    }

    public void increaseNews(int news) {
        this.news += news;
    }

    public void increaseRomance(int romance) {
        this.romance += romance;
    }

    public void increaseScienceFiction(int scienceFiction) {
        this.scienceFiction += scienceFiction;
    }

    public void increaseReality(int reality) {
        this.reality += reality;
    }

    public void increaseSoap(int soap) {
        this.soap += soap;
    }

    public void increaseThriller(int thriller) {
        this.thriller += thriller;
    }

    public void increaseTalk(int talk) {
        this.talk += talk;
    }

    public void increaseTvMovie(int tvMovie) {
        this.tvMovie += tvMovie;
    }

    public void increaseWar(int war) {
        this.war += war;
    }

    public void increaseWestern(int western) {
        this.western += western;
    }


    public long getUserID() {
        return userID;
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
}
