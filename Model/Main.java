package Model;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    //properties
    static String currentUsername;

    //methods
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("login");
    }

    @Override
    public void init() throws IOException {
        System.out.println("program opened");
    }

    @Override
    public void stop() {
        /*HashMap<String,Object> map = new HashMap<>();
        map.put("command","close");
        CommunicateWithServer.disconnect();
        CommunicateWithServer.communicateWithServer(map);*/
        System.out.println("program closed");
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        CommunicateWithServer.connect();
        launch(args);
    }

}
