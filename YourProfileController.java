package Controller;

import Model.CommunicateWithServer;
import Model.PageLoader;
import Mutual.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;

public class YourProfileController {

    //elements
    public Text nameText;
    public Text countryText;
    public Text cityText;
    public Text followersText;
    public Text usernameText;
    public Text followingsText;
    public Text dateOfBirthText;
    public Text familyNameText;
    public ListView myPostsList;
    public Button menuButton;
    public TextField getName;
    public TextField getCountry;
    public TextField getDateOfBirth;
    public TextField getFamilyName;
    public TextField getCity;
    public Button changeProfile;
    public Button saveChanges;
    //other properties
    User currentUser;

    //methods
    @FXML
    public void initialize() {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object>ansMap = new HashMap<>();
        map.put("command","getCurrentUser");
        map.put("username",TimeLineController.currentUsername);
        ansMap = CommunicateWithServer.communicateWithServer(map);
        currentUser = (User) ansMap.get("currentUser");
        nameText.setText(currentUser.name);
        familyNameText.setText(currentUser.familyName);
        dateOfBirthText.setText(currentUser.birthDate);
        followersText.setText(Integer.toString(currentUser.numOfFollowers));
        followingsText.setText(Integer.toString(currentUser.numOFFollowings));
        usernameText.setText(currentUser.username);
        cityText.setText(currentUser.city);
        countryText.setText(currentUser.country);
        myPostsList.setItems(FXCollections.observableArrayList(currentUser.posts));
        myPostsList.setCellFactory(myPostsList -> new PostItem());
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("menu");
    }

    public void getName(ActionEvent actionEvent) {
    }

    public void getCountry(ActionEvent actionEvent) {
    }

    public void getDateofBirth(ActionEvent actionEvent) {
    }

    public void getFamilyName(ActionEvent actionEvent) {
    }

    public void getCity(ActionEvent actionEvent) {
    }

    public void changeProfile(ActionEvent actionEvent) {
        changeProfile.setVisible(false);
        saveChanges.setVisible(true);
        getCity.setVisible(true);
        getCountry.setVisible(true);
        getDateOfBirth.setVisible(true);
        getName.setVisible(true);
        getFamilyName.setVisible(true);
    }

    public void saveChanges(ActionEvent actionEvent) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = new HashMap<>();
        map.put("command" , "changeProfile");
        map.put("username" , TimeLineController.currentUsername);
        map.put("name" , getName.getText());
        map.put("familyName" , getFamilyName.getText());
        map.put("city" , getCity.getText());
        map.put("country" , getCountry.getText());
        map.put("dateOfBirth" , getDateOfBirth.getText());
        ansMap = CommunicateWithServer.communicateWithServer(map);
        changeProfile.setVisible(false);
        saveChanges.setVisible(true);
        getCity.setVisible(false);
        getCountry.setVisible(false);
        getDateOfBirth.setVisible(false);
        getName.setVisible(false);
        getFamilyName.setVisible(false);
        new PageLoader().load("yourProfile");
        currentUser = (User) ansMap.get("newCurrentUser");
        System.out.println(currentUser.username);
    }
}
