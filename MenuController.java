package Controller;

import Model.CommunicateWithServer;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.HashMap;

public class MenuController {
    //elements
    public Button goToProfile;
    public Button goToTimeline;
    public Button logOut;

    //methods
    public void goToProfile(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("yourProfile");
    }

    public void goToTimeline(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("command","logout");
        map.put("username" , TimeLineController.currentUsername);
        CommunicateWithServer.communicateWithServer(map);
        new PageLoader().load("login");
    }
}
