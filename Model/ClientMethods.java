package Model;

import javafx.scene.control.Alert;

public class ClientMethods {
    public static void showAlert (String message , Alert.AlertType alertType) {
        System.out.println("alert");
        Alert alert = new Alert(alertType , message);
        alert.show();
    }
}
