package Server;

import Mutual.User;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataBaseHandler {

    //properties
    private static DataBaseHandler myDBHandler = new DataBaseHandler();
    //public static final String FILE_PREFIX = "./db/";
    public static final String USERS_FILE = "UsersDataBase";// + FILE_PREFIX;

    //constructors
    private DataBaseHandler(){}

    //methods
    public static DataBaseHandler getMyDBHandler () {
        return myDBHandler;
    }

    public synchronized void loadDataBase () {
        try {
            FileInputStream fileInputStream = new FileInputStream(DataBaseHandler.USERS_FILE);
            ObjectInputStream fileToObject = new ObjectInputStream(fileInputStream);
            ServerMain.users = new ConcurrentHashMap<String, User>(((ConcurrentHashMap<String,User>) fileToObject.readObject()));
            System.out.println(ServerMain.users);
            fileToObject.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            ServerMain.users = new ConcurrentHashMap<String,User>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized void updateDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(USERS_FILE);
            ObjectOutputStream objectToFile = new ObjectOutputStream(fileOutputStream);
            objectToFile.writeObject(ServerMain.users);
            objectToFile.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
