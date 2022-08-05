package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name="MovieTime.findMovies",query = "select distinct m.* from MOVIE as m inner join MOVIETIMES as b on m.MOVIEID = b.MT_MOVIEID where b.MT_THEATERID = ?",resultClass=Movie.class),
    @NamedNativeQuery(name = "MovieTime.findTimes", query = "select * from MovieTimes where MovieTimes.MT_THEATERID = ? and MOVIETIMES.MT_MOVIEID = ?",resultClass = MovieTime.class)
})
public class MovieTime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long MT_ID;
    @NotNull
    private int MT_theaterID;
    @NotNull
    private int MT_movieID;
    @NotNull
    @Size(min=7,max=8)
    private String MT_showtime;
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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMT_theaterID() {
        return MT_theaterID;
    }

    public void setMT_theaterID(int MT_theaterID) {
        this.MT_theaterID = MT_theaterID;
    }

    public int getMT_movieID() {
        return MT_movieID;
    }

    public void setMT_movieID(int MT_movieID) {
        this.MT_movieID = MT_movieID;
    }

    public String getMT_showtime() {
        return MT_showtime;
    }

    public void setMT_showtime(String MT_showtime) {
        this.MT_showtime = MT_showtime;
    }

    public Long getId() {
        return MT_ID;
    }

    public void setId(Long id) {
        this.MT_ID = id;
    }
    
    
    //generated code


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (MT_ID != null ? MT_ID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the MT_ID fields are not set
        if (!(object instanceof MovieTime)) {
            return false;
        }
        MovieTime other = (MovieTime) object;
        if ((this.MT_ID == null && other.MT_ID != null) || (this.MT_ID != null && !this.MT_ID.equals(other.MT_ID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MovieTime[ id=" + MT_ID + " ]";
    }
    
}
