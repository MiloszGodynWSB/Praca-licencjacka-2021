package pl.wsb.licencjat.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moviesprofiles")
public class MoviesProfiles {

    @Id
    @Column(name = "userID")
    private long userID;
    private int Action;
    private int Adventure;
    private int Animation;
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
    private int Thriller;
    private int War;
    private int Western;

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void increaseAction(int action) {
        Action += action;
    }

    public void increaseAdventure(int adventure) {
        Adventure += adventure;
    }

    public void increaseAnimation(int animation) {
        Animation += animation;
    }

    public void increaseComedy(int comedy) {
        Comedy += comedy;
    }

    public void increaseCrime(int crime) {
        Crime += crime;
    }

    public void increaseDocumentary(int documentary) {
        Documentary += documentary;
    }

    public void increaseDrama(int drama) {
        Drama += drama;
    }

    public void increaseFamily(int family) {
        Family += family;
    }

    public void increaseFantasy(int fantasy) {
        Fantasy += fantasy;
    }

    public void increaseHistory(int history) {
        History += history;
    }

    public void increaseHorror(int horror) {
        Horror += horror;
    }

    public void increaseMusic(int music) {
        Music += music;
    }

    public void increaseMystery(int mystery) {
        Mystery += mystery;
    }

    public void increaseRomance(int romance) {
        Romance += romance;
    }

    public void increaseScienceFiction(int scienceFiction) {
        ScienceFiction += scienceFiction;
    }

    public void increaseTvMovie(int tvMovie) {
        TvMovie += tvMovie;
    }

    public void increaseThriller(int thriller) {
        Thriller += thriller;
    }

    public void increaseWar(int war) {
        War += war;
    }

    public void increaseWestern(int western) {
        Western += western;
    }

    public long getUserID() {
        return userID;
    }

    public int getAction() {
        return Action;
    }

    public int getAdventure() {
        return Adventure;
    }

    public int getAnimation() {
        return Animation;
    }

    public int getComedy() {
        return Comedy;
    }

    public int getCrime() {
        return Crime;
    }

    public int getDocumentary() {
        return Documentary;
    }

    public int getDrama() {
        return Drama;
    }

    public int getFamily() {
        return Family;
    }

    public int getFantasy() {
        return Fantasy;
    }

    public int getHistory() {
        return History;
    }

    public int getHorror() {
        return Horror;
    }

    public int getMusic() {
        return Music;
    }

    public int getMystery() {
        return Mystery;
    }

    public int getRomance() {
        return Romance;
    }

    public int getScienceFiction() {
        return ScienceFiction;
    }

    public int getTvMovie() {
        return TvMovie;
    }

    public int getThriller() {
        return Thriller;
    }

    public int getWar() {
        return War;
    }

    public int getWestern() {
        return Western;
    }

    public String toString() {
        return "Movie{" +
                "movieID=" + userID +
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
                ", thriller=" + Thriller +
                ", war=" + War +
                ", western=" + Western +
                '}';
    }
}
