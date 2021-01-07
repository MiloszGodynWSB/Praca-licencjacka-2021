package pl.wsb.licencjat.model.database;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ignoredmovies")
@NoArgsConstructor
@Setter
public class IgnoredMovies {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long userID;
    private long movieID;

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setMovieID(long movieID) {
        this.movieID = movieID;
    }
}
