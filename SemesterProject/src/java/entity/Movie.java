package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity

@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findByName", query = "SELECT m FROM Movie m WHERE m.movieName = :movieName"),
    @NamedQuery(name = "Movie.findByDesc", query = "SELECT m FROM Movie m WHERE m.movieDesc =:movieDesc"),
    @NamedQuery(name = "Movie.findByRating", query = "SELECT m FROM Movie m WHERE m.movieRating = :movieRating")})

public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieID;
    @NotNull
    @Size(min=1,max=50)
    private String movieName;
    @NotNull
    @Size(min=1,max=1000)
    private String movieDesc;
    @NotNull
    @Size(min=1,max=5)
    private String movieRating;

    //getters and setters
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDesc() {
        return movieDesc;
    }

    public void setMovieDesc(String movieDesc) {
        this.movieDesc = movieDesc;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public Long getMovieId() {
        return movieID;
    }

    public void setMovieId(Long id) {
        this.movieID = id;
    }
    
    
    //generated code

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieID != null ? movieID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the movieID fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieID == null && other.movieID != null) || (this.movieID != null && !this.movieID.equals(other.movieID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Movie[ id=" + movieID + " ]";
    }
    
}
