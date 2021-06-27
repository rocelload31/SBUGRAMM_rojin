package Mutual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Post implements Serializable {

    //properties
    long numOfLikes;
    long numOfRetweets;
    private String writer;
    private String title;
    private String description;
    private Date date;
    private String publisher;
    public Vector<String> comments = new Vector<>();

    //methods
    //getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getWriter() {
        return writer;
    }

    public long getNumOfLikes(){ return numOfLikes; }

    public long getNumOfRetweets() { return numOfRetweets; }

    public Date getDate() { return date; }

    public String getPublisher() {
        return publisher;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setNumOfLikes(long numOfLikes) { this.numOfLikes = numOfLikes; }

    public void setNumOfRetweets(long numOfRetweets) { this.numOfRetweets = numOfRetweets; }

    public void setDate(Date date) { this.date = date; }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    //equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title);
    }

    //hashcode
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    //to string
    @Override
    public String toString() {
        return title;
    }
}
