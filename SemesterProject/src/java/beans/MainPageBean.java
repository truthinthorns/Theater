package beans;

import ejb.MovieEJB;
import entity.Movie;
import entity.MovieTime;
import entity.Theater;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "mainPageBean")
@SessionScoped
public class MainPageBean implements Serializable {

    @EJB
    private MovieEJB movieEJB;
    private String zipCode;
    private Theater theater;
    private Movie movie;
    private MovieTime time;
    private ArrayList<String> imageSources;
    private int ticketCount;
    private int price;
    private boolean disablePurchase;

    public MainPageBean() {
        
        //imageSources stores the link to the picture for use at any point. makes it easier than having that link each time.
        imageSources = new ArrayList<>();
        imageSources.add("https://www.awn.com/sites/default/files/styles/original/public/image/attached/1057409-pofdigitalkeyartonline864x1080instagram-1280.jpg?itok=vGiQPgbL");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BMTJmNGJmYTgtYjAxNy00YmMzLTk2YTYtMGIzMmUwNDMyMTY1XkEyXkFqcGdeQXVyMTEyMjM2NDc2._V1_FMjpg_UX1000_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BYmMxZWRiMTgtZjM0Ny00NDQxLWIxYWQtZDdlNDNkOTEzYTdlXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BZDQyODUwM2MtNzA0YS00ZjdmLTgzMjItZWRjN2YyYWE5ZTNjXkEyXkFqcGdeQXVyMTI2MzY1MjM1._V1_FMjpg_UX1000_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BYzMzNTJjYmMtZTkxNS00MjI4LWI3YmQtOTQ4MDZjZDJlZjQyXkEyXkFqcGdeQXVyNjc0NzQzNTM@._V1_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BOWQwOTA1ZDQtNzk3Yi00ZmVmLWFiZGYtNjdjNThiYjJhNzRjXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_FMjpg_UX1000_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BYTg2Zjk0ZTctM2ZmMi00MDRmLWJjOGYtNWM0YjBmZTBjMjRkXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_FMjpg_UX1000_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BOWVmNTBiYTUtZWQ3Yi00ZDlhLTgyYjUtNzBhZjM3YjRiNGRkXkEyXkFqcGdeQXVyNzYyOTM1ODI@._V1_FMjpg_UX1000_.jpg");
        imageSources.add("https://m.media-amazon.com/images/M/MV5BOTBjMjA4NmYtN2RjMi00YWZlLTliYTktOTIwMmNkYjYxYmE1XkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg");
        ticketCount = 0;
        disablePurchase = true;
    }

    //------------------------------------
    //getters and setters
    
    public boolean isDisablePurchase() {
        return disablePurchase;
    }

    public void setDisablePurchase(boolean disablePurchase) {
        this.disablePurchase = disablePurchase;
    }
    
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public MovieTime getTime() {
        return time;
    }

    public void setTime(MovieTime time) {
        this.time = time;
    }

    public ArrayList<String> getImageSources() {
        return imageSources;
    }

    public void setImageSources(ArrayList<String> imageSources) {
        this.imageSources = imageSources;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    //end getters and setters
    //--------------------------------------------
    
    //get the movies playing at the selected theater
    public List<Movie> getMovieList() {
        if (theater == null) {
            return null;
        } else {
            return movieEJB.findMovieNames(theater.getTheaterId());
        }
    }

    //from the passed in movie and stored theater, get all of the movie times for that movie and theater pair
    public List<MovieTime> getMovieTimesList(Movie m) {
        List<MovieTime> list;
        if (theater == null) {
            return null;
        } else {
            list = movieEJB.findMovieTimes(theater.getTheaterId(), m.getMovieId());
            return list;
        }
    }

    //return a list of theaters for the stored zipCode
    public List<Theater> getTheaterList() {
        return movieEJB.findTheatersByZip(zipCode);
    }
    
    //since each ticket costs $10, once the ticketCount is changed, update the price variable to reflect the update
    public void changePrice() {
        this.price = this.ticketCount * 10;
    }

    //if ticketCount is not 10, add one to the ticketCount and change the price variable, changing what the user sees on the page. also, enable (by setting         disablePurchase to false) the 
    //confirm purchase button on the page
    public void increaseTicketCount() {
        if (this.ticketCount != 10) {
            this.ticketCount++;
            changePrice();
        }
        setDisablePurchase(false);
    }

    //if ticketCount is not already at 0 (no negatives), decrease the ticketCount variable by 1 and change the price variable, changing what the user sees          on the page
    //after decreasing, if ticketCount==0, set disablePurchase to true, disabling the confirm purchase button on the page.
    //else if the ticket count is 0 already, set disablePurchase to true, disabling the confirm purchase button on the page. this might be useless.
    public void decreaseTicketCount() {
        if (this.ticketCount != 0) {
            this.ticketCount--;
            if(this.ticketCount==0)
                setDisablePurchase(true);
            changePrice();
        }
        else if(this.ticketCount == 0)
            setDisablePurchase(true);
    }


    //Page navigation
    //set the theater member variable and go the the movies page
    public String showMovies(Theater theater) {
        this.theater = theater;
        return "Movies.xhtml";
    }

    //set the movie and time member variables and go the the movies page
    public String showPurchaseScreen(Movie movie, MovieTime time) {
        this.price = 0;
        this.ticketCount = 0;
        this.disablePurchase = true;
        this.movie = movie;
        this.time = time;
        return "PurchaseTicket.xhtml";
    }

    //set the zipCode member variable and go to the Theaters page
    public String showTheaters(String zip) {
        this.zipCode = zip;
        return "Theaters.xhtml";
    }

    //go to the credits page
    public String showCredits() {
        return "Credits.xhtml";
    }

    //go to the confirmation page
    public String makePurchase() {
        return "ConfirmationPage.xhtml";
    }
}
