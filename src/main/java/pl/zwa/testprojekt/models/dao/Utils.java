package pl.zwa.testprojekt.models.dao;

import javafx.scene.control.Alert;
//obsługa okieniek z alertami
public class Utils {
    public static void createSimpleDialog(String name, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(name);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
}
