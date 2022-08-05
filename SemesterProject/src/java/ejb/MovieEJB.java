package ejb;

import entity.Movie;
import entity.MovieTime;
import entity.Theater;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MovieEJB {

    @PersistenceContext(unitName = "SemesterProjectPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    //call the namedQuery in Theater to return a list of all theaters that have the passed in zipCode
    public List<Theater> findTheatersByZip(String zip) {
        return em.createNamedQuery("Theater.findByTheaterZip", Theater.class)
                .setParameter("theaterZip", zip)
                .getResultList();
    }
    
    //call the namedQuery in MovieTime to return a list of movies that have the passed in theater ID
    public List<Movie> findMovieNames(Long id) {
        return em.createNamedQuery("MovieTime.findMovies", Movie.class)
                .setParameter(1, id)
                .getResultList();
    }
    
    //call the namedQuery in MovieTime to return a list of movieTimes that have the passed in theater ID AND movie ID
    public List<MovieTime> findMovieTimes(Long t_id,Long m_id) {
        return em.createNamedQuery("MovieTime.findTimes", MovieTime.class)
                .setParameter(1, t_id)
                .setParameter(2, m_id)
                .getResultList();
    }

}