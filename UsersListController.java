package Controller;

import Model.CommunicateWithServer;
import Model.PageLoader;
import Mutual.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UsersListController {

    //elements
    public ListView usersList;
    public Button backToTimeLine;

    //methods
    @FXML
    public void initialize() {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = new HashMap<>();
        ConcurrentHashMap<String, User> usersMap = new ConcurrentHashMap<>();
        map.put("command","getAllUsernames");
        ansMap = CommunicateWithServer.communicateWithServer(map);
        //System.out.println(ansMap.get("usersMap").toString());
        usersMap = (ConcurrentHashMap<String, User>) ansMap.get("usersMap");
        //System.out.println(usersMap);
        Set<String> usernames = usersMap.keySet();
        for (String u : usernames) {
            usersList.getItems().add(u);
        }

    }

    /*public void addToFollowers(MouseEvent mouseEvent) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("command","addFollower");
        String wantedFollow = usersList.getSelectionModel().getSelectedItems().toString();
        map.put("currentUsername",TimeLineController.currentUsername);
        map.put("wantedFollow", wantedFollow);
        System.out.println(usersList.getSelectionModel().getSelectedItems());
        CommunicateWithServer.communicateWithServer(map);
    }*/

    public void goBackToTimeLine(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void goToProfile(MouseEvent mouseEvent) throws IOException {
        OtherProfileController.wantedUsername = usersList.getSelectionModel().getSelectedItems().toString();
        new PageLoader().load("otherProfile");
    }
}
