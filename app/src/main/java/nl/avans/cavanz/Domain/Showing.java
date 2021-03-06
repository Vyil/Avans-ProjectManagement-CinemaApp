package nl.avans.cavanz.Domain;

import java.io.Serializable;

/**
 * Created by Sander on 3/29/2018.
 */

public class Showing implements Serializable{
    private final String TAG = getClass().getSimpleName();

    private int showID;
    private Movie movie;
    private String startingTime;
    private String endingTime;
    private String date;
    private Hall hall;


    public Showing() {
    }

    public Showing(Movie movie, String startingTime, String endingTime, String date, Hall hall) {
        this.movie = movie;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.date = date;
        this.hall = hall;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }




}
