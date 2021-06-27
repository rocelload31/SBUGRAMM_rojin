package Server;

import Mutual.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements Runnable{

    //properties
    Socket socket = null;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;
    HashMap<String,Object> map = null;
    boolean isOnline;

    //constructors
    public ClientHandler (Socket socket) {
        try {
            this.socket = socket;
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //methods
    @Override
    public void run() {
        while (true) {
            try {
                map = (HashMap<String, Object>) objectInputStream.readObject();
                HashMap<String,Object> returnMap = new HashMap<>();
                //in this switch we find which command has been sent and then we call the method related to, in Methods class
                switch ((String) map.get("command")) {
                    case "signUp" :
                        if (Methods.usernameExists(map)) {
                            returnMap.put("alert","This username is already taken. Please choose another one.");
                        } else if (Methods.passwordNotValid(map)) {
                            returnMap.put("alert","Your password should only contain English letters or numbers.");
                        } /*else if (Methods.passwordExists(map)) {
                            returnMap.put("alert","This password is already taken. Please choose another one.");
                        }*/ else if (Methods.passwordLessThan8(map)) {
                            returnMap.put("alert","Your password should have at least 8 characters.");
                        } else
                        Methods.signUp(map);
                        returnMap.put("ok","signUp");
                        break;
                    case "login":
                        if (!Methods.usernameExists(map)) {
                            returnMap.put("alert","This username does not exist.");
                        } else if (Methods.wrongPassword(map)) {
                            returnMap.put("alert","Invalid password. Please try again");
                        } else {
                            Methods.login(map);
                            returnMap.put("ok","login");
                        }
                        break;
                    case "changeProfile" :
                        returnMap.put("newCurrentUser" , Methods.changeProfile(map));
                        break;
                    case "close" :
                        socket.close();
                        objectOutputStream.close();
                        objectInputStream.close();
                        break;
                    case "getAllUsernames" :
                        //System.out.println(ServerMain.users);
                        returnMap.put("usersMap",ServerMain.users);
                        //System.out.println(returnMap.get("usersMap").toString());
                        break;
                    case "addFollower" :
                        Methods.addFollower(map);
                        break;
                    case "unFollow" :
                        Methods.unFollow(map);
                        break;
                    case "addPost" :
                        Methods.addPost(map);
                        break;
                    case "getCurrentUser" :
                        returnMap.put("currentUser" , ServerMain.users.get(map.get("username")) );
                        //System message
                        System.out.println(map.get("username") + " GET INFO " + map.get("username"));
                        System.out.println("message: " + map.get("username") + " ");
                        System.out.println("time: " + Methods.currentTime);
                        break;
                    case "getTimeLinePosts" :
                        returnMap.put("timeLinePosts",Methods.getTimeLinePosts(map));
                        //System message
                        System.out.println(map.get("currentUser") + ": GET POSTS LIST");
                        System.out.println("time: " + Methods.currentTime);
                        break;
                    case "logout" :
                        System.out.println(map.get("username") + ": LOGOUT");
                        System.out.println("time: " + Methods.currentTime);
                        break;
                }
                objectOutputStream.writeObject(returnMap);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
