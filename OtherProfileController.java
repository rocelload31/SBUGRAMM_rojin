package Controller;

import Model.*;
import Model.PageLoader;
import Mutual.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.HashMap;

public class OtherProfileController {
    public ImageView profilePhotoButton;
    public Label nameTxt;
    public Label familyNameTxt;
    public Label dateOfBirthTxt;
    public Label countryTxt;
    public Label cityTxt;
    public Label followersTxt;
    public Label followingsTxt;
    public Label usernameTxt;
    public Text nameText;
    public Text countryText;
    public Text cityText;
    public Text followersText;
    public Text usernameText;
    public Text followingsText;
    public Text dateOfBirthText;
    public Text familyNameText;
    public Button menuButton;
    public Button unfollowButton;
    public Button followButton;
    public static String wantedUsername;
    public User wantedProfile;
    public ListView otherPostsList;

    //methods

    @FXML
    public void initialize() {
        wantedUsername = wantedUsername.substring(1,wantedUsername.length()-1);
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object>ansMap = new HashMap<>();
        map.put("command","getCurrentUser");
        map.put("username",wantedUsername);
        ansMap = CommunicateWithServer.communicateWithServer(map);
        wantedProfile = (User) ansMap.get("currentUser");
        nameText.setText(wantedProfile.name);
        familyNameText.setText(wantedProfile.familyName);
        dateOfBirthText.setText(wantedProfile.birthDate);
        followersText.setText(Integer.toString(wantedProfile.numOfFollowers));
        followingsText.setText(Integer.toString(wantedProfile.numOFFollowings));
        usernameText.setText(wantedProfile.username);
        cityText.setText(wantedProfile.city);
        countryText.setText(wantedProfile.country);
        otherPostsList.setItems(FXCollections.observableArrayList(wantedProfile.posts));
        otherPostsList.setCellFactory(otherPostsList -> new PostItem());
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("menu");
    }

    public void follow(ActionEvent actionEvent) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("command","addFollower");
        String wantedFollow = wantedUsername;
        map.put("currentUsername",TimeLineController.currentUsername);
        map.put("wantedFollow", wantedFollow);
        CommunicateWithServer.communicateWithServer(map);
        unfollowButton.setVisible(true);
        followButton.setVisible(false);
    }

    public void unfollow(ActionEvent actionEvent) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("command","unFollow");
        String wantedFollow = wantedUsername;
        map.put("currentUsername",TimeLineController.currentUsername);
        map.put("wantedUnFollow", wantedFollow);
        CommunicateWithServer.communicateWithServer(map);
        unfollowButton.setVisible(false);
        followButton.setVisible(true);
    }
}
