package Movie;

import java.io.Serializable;

public class Movie implements Serializable{

    private String title;
    private String imdbId;
    private String year;
    private String type;
    private String poster;

    public Movie() {
    }

    public Movie(String title, String imdbId, String year, String type, String poster) {
        this.title = title;
        this.imdbId = imdbId;
        this.year = year;
        this.type = type;
        this.poster = poster;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }




}
