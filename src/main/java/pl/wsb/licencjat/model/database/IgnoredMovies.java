package pl.wsb.licencjat.model.database;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ignoredmovies")
@NoArgsConstructor
@Setter
public class IgnoredMovies {
    @Id
    @Column(name = "userID")
    private int id;
    private int movieID;
}
