package Mutual;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class User implements Serializable {

    //properties
    public Vector<Post> posts = new Vector<>();
    public String name;
    public String familyName;
    public int numOfFollowers;
    public int numOFFollowings;
    public String profilePath;
    public String country;
    public String city;
    public String username;
    public String password;
    public String birthDate;
    public String phoneNumber;
    public ConcurrentHashMap<String,User> followings = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String,User> followers = new ConcurrentHashMap<>();
    //constructors
    public User (String name, String familyName, String phoneNumber, String birthDate, String country, String city, String username, String password) {
        this.name = name;
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.country = country;
        this.city= city;
        this.username = username;
        this.password = password;
    }

    public User (String username) {
        this.username = username;
    }

    //methods
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
