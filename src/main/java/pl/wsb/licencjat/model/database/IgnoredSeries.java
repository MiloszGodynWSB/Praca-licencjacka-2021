package pl.wsb.licencjat.model.database;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ignoredseries")
@NoArgsConstructor
@Setter
public class IgnoredSeries {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long userID;
    private long movieID;

    public void setuserID(long userID) {
        this.userID = userID;
    }

    public void setMovieID(long movieID) {
        this.movieID = movieID;
    }
}
