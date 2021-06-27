package Server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import Mutual.*;

public class Methods {
    static LocalDateTime currentTime = LocalDateTime.now().now();

    //sign up
    static void signUp (HashMap<String, Object> map) {
        User newUser = new User((String) map.get("name"),(String)map.get("familyName"),(String)map.get("phoneNumber"),(String)map.get("dateOfBirth"),(String)map.get("country"),(String)map.get("city"),(String)map.get("username"),(String)map.get("password"));
        ServerMain.users.put((String) map.get("username"),newUser);
        DataBaseHandler.getMyDBHandler().updateDataBase();
        //System message
        System.out.println(map.get("username").toString() + " REGISTER " );
        System.out.println("time: " + currentTime);
    }

    //checking the username
    static boolean usernameExists (HashMap<String, Object> map) { //returns true if the username already exists
        String wantedUsername = (String) map.get("username");
        for (int i=0 ; i<ServerMain.users.size() ; i++) {
            if (ServerMain.users.containsKey(wantedUsername)) {
                return true;
            }
        }
        return false;
    }

    static boolean passwordLessThan8 (HashMap<String, Object> map) { //returns true if the password is less than 8 characters.
        String wantedPassword = (String) map.get("password");
        if (wantedPassword.length()<8) {
            return true;
        }
        return false;
    }
    static boolean passwordNotValid (HashMap<String, Object> map) { //returns true if the password has characters other than english letters or numbers
        String wantedPassword = (String) map.get("password");
        char[] passwordChars = wantedPassword.toCharArray();
        for (char c : passwordChars) {
            if (!((c>='a' && c<='z')||(c>='A' && c<='Z')||(c>='1' && c<='9'))) {
                return true;
            }
        }
        return false;
    }
    static boolean wrongPassword (HashMap<String, Object> map) { //returns true if the password is wrong
        if (!(ServerMain.users.get(map.get("username")).password.equals(map.get("password")))) {
            return true;
        }
        return false;
    }
    //login
    static void login (HashMap<String, Object> map) {
        //System message
        System.out.println(map.get("username").toString() + " LOGIN" );
        System.out.println("time: " + currentTime);
    }

    //following a user
    static void addFollower (HashMap<String, Object> map) {
        //getting the wanted username from map
        String wantedUsername = map.get("wantedFollow").toString();
        //getting the current username from map
        String currentUsername = (String) map.get("currentUsername");
        //adding the wanted username to the current usernames following list
        ServerMain.users.get(currentUsername).followings.put(wantedUsername,ServerMain.users.get(wantedUsername));
        ServerMain.users.get(currentUsername).numOFFollowings = ServerMain.users.get(currentUsername).followings.size();
        System.out.println("currents followings: " + ServerMain.users.get(currentUsername).numOFFollowings);
        //adding the currentUsername to the wanted usernames followers list
        ServerMain.users.get(wantedUsername).followers.put(currentUsername,ServerMain.users.get(currentUsername));
        ServerMain.users.get(wantedUsername).numOfFollowers = ServerMain.users.get(wantedUsername).followers.size();
        System.out.println("wanteds followers: " + ServerMain.users.get(wantedUsername).numOfFollowers);
        //updating the dataBase
        DataBaseHandler.getMyDBHandler().updateDataBase();
        //System message
        System.out.println(currentUsername + ": FOLLOWED");
        System.out.println("MESSAGE: " + wantedUsername);
        System.out.println("time: " + currentTime);
    }

    static void unFollow (HashMap<String, Object> map) {
        //getting the wanted username from map
        String wantedUsername = map.get("wantedUnFollow").toString();
        //getting the current username from map
        String currentUsername = (String) map.get("currentUsername");
        //removing the wantedUsername from the current usernames following list
        ServerMain.users.get(currentUsername).followings.remove(wantedUsername);
        ServerMain.users.get(currentUsername).numOFFollowings = ServerMain.users.get(currentUsername).followings.size();
        System.out.println("currents followings: " + ServerMain.users.get(currentUsername).numOFFollowings);
        //removing the current username from the wanted usernames followers list;
        ServerMain.users.get(wantedUsername).followers.remove(currentUsername);
        ServerMain.users.get(wantedUsername).numOfFollowers = ServerMain.users.get(wantedUsername).followers.size();
        System.out.println("wanteds followers: " + ServerMain.users.get(wantedUsername).numOfFollowers);
        //updating the dataBase
        DataBaseHandler.getMyDBHandler().updateDataBase();
        //System message
        System.out.println(map.get("currentUsername") + ": UNFOLLOWED");
        System.out.println("MESSAGE: " + map.get("wantedUnFollow"));
        System.out.println("time: " + currentTime);
    }

    //adding a post to the current username post list
    static void addPost (HashMap<String, Object> map) {
        String currentUsername = (String) map.get("username");
        Post currentPost = (Post) map.get("post");
        System.out.println(currentPost + "current");
        ServerMain.users.get(currentUsername).posts.add(currentPost);
        DataBaseHandler.getMyDBHandler().updateDataBase();
        //System message
        System.out.println(currentUsername + ": PUBLISH");
        System.out.println("message: " + currentPost.getTitle() + " " + currentPost.getPublisher());
        System.out.println("time: " + currentTime);
    }

    static void like (HashMap<String, Object> map) {
        //System message
        System.out.println(map.get("username") + ": LIKE");
        System.out.println("message: " + map.get("publisher") + " " + map.get("title"));
        System.out.println("time: " + currentTime);
    }

    static void repost (HashMap<String, Object> map) {
        //System message
        System.out.println(map.get("username") + ": REPOST");
        System.out.println("message: " + map.get("publisher") + " " + map.get("title"));
        System.out.println("time: " + currentTime);
    }

    static User changeProfile (HashMap<String, Object> map) {
        String targetUsername = (String) map.get("username");
        ServerMain.users.get(targetUsername).name = (String) map.get("name");
        ServerMain.users.get(targetUsername).familyName = (String) map.get("familyName");
        ServerMain.users.get(targetUsername).city = (String) map.get("city");
        ServerMain.users.get(targetUsername).country = (String) map.get("country");
        ServerMain.users.get(targetUsername).birthDate = (String) map.get("dateOfBirth");
        DataBaseHandler.getMyDBHandler().updateDataBase();
        //System message
        System.out.println(map.get("username") + ": UPDATE INFO");
        //System.out.println("message: " + map.get("newProfileAddress"));
        System.out.println("time: " + currentTime);
        return ServerMain.users.get(targetUsername);
    }

    static void comment (HashMap<String, Object> map) {
        //System message
        System.out.println(map.get("username") + ": COMMENT");
        System.out.println("message: " + map.get("title"));
        System.out.println("time: " + currentTime);
    }

    static ArrayList<Post> getTimeLinePosts (HashMap<String, Object> map) {
        String currentUser = (String) map.get("currentUser");
        ArrayList<Post> timeLinePosts = new ArrayList<>();
        Collection<User> followersCollection = ServerMain.users.get(currentUser).followers.values();
        for (User u : followersCollection) {
            for (Post p : u.posts) {
                timeLinePosts.add(p);
            }
        }
        for (Post p : ServerMain.users.get(currentUser).posts) {
            timeLinePosts.add(p);
        }
        return timeLinePosts;
    }
}
