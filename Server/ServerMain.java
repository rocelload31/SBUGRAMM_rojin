package Server;

import Mutual.User;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMain {
    //properties
    public static ConcurrentHashMap<String,User> users = null;
    public static final int PORT = 2222;
    public static ServerSocket serverSocket = null;
    public static String currentUsername;

    public static void main (String[] args) {
        DataBaseHandler.getMyDBHandler().loadDataBase();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
