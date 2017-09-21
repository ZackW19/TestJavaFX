package pl.zwa.testprojekt.models.dao;

import javafx.scene.control.Alert;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    //obsługa okieniek z alertami
    public static void createSimpleDialog(String name, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(name);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.show();
    }
    //metoda szyfrująca hasło - String do tablicy bajtów - byte[]
    public static String shaHash(String message){
        try {
            MessageDigest sha2 = MessageDigest.getInstance("SHA-256");
            byte[] bytesOfMessage = message.getBytes();
            byte[] byteOfCryptoMessage = sha2.digest(bytesOfMessage);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < byteOfCryptoMessage.length; i++){
                //0xFF bajt w formacie 16-kowym
                stringBuilder.append(Integer.toHexString(0xFF & byteOfCryptoMessage[i]));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
