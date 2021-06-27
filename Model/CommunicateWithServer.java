package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class CommunicateWithServer {

    //properties
    private static final int PORT = 2222;
    static Socket socket;
    static ObjectInputStream objectInputStream = null;
    static ObjectOutputStream objectOutputStream = null;
    static boolean connected;

    //methods
    public static boolean connect () {
        if (socket != null) {
            return false;
        }
        try {
            socket = new Socket("localhost",PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            connected = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean disconnect () {
        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
            connected = false;
            objectOutputStream = null;
            objectInputStream = null;
            socket = null;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        objectOutputStream = null;
        objectInputStream = null;
        socket = null;
        return false;
    }

    public static HashMap<String,Object> communicateWithServer(HashMap<String,Object> map) {
        HashMap<String,Object> ansMap = null;
        try {
            objectOutputStream.writeObject(map);
            objectOutputStream.flush();
            ansMap = (HashMap<String, Object>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ansMap;
    }
}
