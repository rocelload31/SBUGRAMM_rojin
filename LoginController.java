package Controller;

import Model.ClientMethods;
import Model.CommunicateWithServer;
import Model.PageLoader;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashMap;

public class LoginController {

    //elements
    @FXML
    public Label wrongPasswordLabel;
    public Button loginButton;
    public TextField usernameField;
    public TextField textPasswordField;
    public ImageView logoImage;
    public PasswordField bulletPasswordFiled;
    public CheckBox passwordFieldCheckBox;
    public Button signUPButton;

    //methods
    //initialize method
    @FXML
    public void initialize() { //used for transition in logo
        TranslateTransition transition = new TranslateTransition(Duration.millis(2400),logoImage);
        transition.setToY(260);
        transition.playFromStart();
    }

    //login button method
    public void login(ActionEvent actionEvent) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        HashMap<String,Object> ansMap = new HashMap<>();
        map.put("command","login");
        map.put("username",usernameField.getText());
        if (textPasswordField.isVisible()) {
            map.put("password",textPasswordField.getText());
        }
        else {
            map.put("password",bulletPasswordFiled.getText());
        }
        ansMap = CommunicateWithServer.communicateWithServer(map);
        if (ansMap.containsKey("alert")) {
            System.out.println("alert must be shown");
            ClientMethods.showAlert((String) ansMap.get("alert") , Alert.AlertType.ERROR);
        } else {
            TimeLineController.setCurrentUsername(usernameField.getText());
            ClientMethods.showAlert("Welcome " + usernameField.getText() , Alert.AlertType.CONFIRMATION);
            new PageLoader().load("timeLine");
        }
    }

    //sign up button method
    public void signUp (ActionEvent actionEvent) throws IOException {
        new PageLoader().load("signUp");
    }
    //showing or not showing the password
    public void showPassword (ActionEvent actionEvent) {
        if (!textPasswordField.isVisible()) {
            textPasswordField.setVisible(true);
            bulletPasswordFiled.setVisible(false);
            textPasswordField.setText(bulletPasswordFiled.getText());
        }
        else {
            textPasswordField.setVisible(false);
            bulletPasswordFiled.setVisible(true);
            bulletPasswordFiled.setText(textPasswordField.getText());
        }
    }
}
