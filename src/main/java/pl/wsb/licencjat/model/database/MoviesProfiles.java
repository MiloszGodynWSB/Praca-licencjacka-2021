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
    private int thriller;
    private int War;
    private int Western;

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
        return thriller;
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
                ", thriller=" + thriller +
                ", war=" + War +
                ", western=" + Western +
                '}';
    }
}
