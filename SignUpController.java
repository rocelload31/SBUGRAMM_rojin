package Controller;

import Model.*;
import Mutual.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.util.HashMap;

public class SignUpController {
    //elements
    public ImageView profile;
    public TextField name;
    public TextField familyName;
    public TextField phoneNumber;
    public TextField dateOfBirth;
    public TextField country;
    public TextField city;
    public TextField username;
    public TextField password;
    public Button signUp;
    public Button addProfilePhoto;

    //methods
    public void addProfilePhoto(MouseEvent mouseEvent) {
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = null;
        map.put("command","signUp");
        map.put("name",name.getText());
        map.put("familyName",familyName.getText());
        map.put("phoneNumber",phoneNumber.getText());
        map.put("dateOfBirth",dateOfBirth.getText());
        map.put("country",country.getText());
        map.put("city",city.getText());
        map.put("username",username.getText());
        map.put("password",password.getText());
        ansMap = CommunicateWithServer.communicateWithServer(map);
        if (ansMap.containsKey("alert")) {
            System.out.println("alert must be shown");
            ClientMethods.showAlert((String) ansMap.get("alert") , Alert.AlertType.ERROR);
        } else if (ansMap.containsKey("ok")){
            System.out.println("Signed up successfully");
            TimeLineController.setCurrentUsername(username.getText());
            ClientMethods.showAlert("Welcome " + username.getText() , Alert.AlertType.CONFIRMATION);
            new PageLoader().load("timeLine");
        }
    }

    public void login(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("login");
    }
}
