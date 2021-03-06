package pl.zwa.testprojekt.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.zwa.testprojekt.models.UserSession;
import pl.zwa.testprojekt.models.dao.UserDao;
import pl.zwa.testprojekt.models.dao.Utils;
import pl.zwa.testprojekt.models.dao.impl.UserDaoImpl;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    TextField textLogin, textLoginR;

    @FXML
    Label labelLoginName, labelLoginText1, labelLoginText2;

    @FXML
    PasswordField textPassword, textPasswordR, textRepeatPasswordR;

    @FXML
    Button buttonLogin, buttonRegister;

    private UserSession userSession = UserSession.getInstance();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonLogin.setOnMouseClicked(e -> tryLogin());  //lambda
        buttonRegister.setOnMouseClicked(e -> tryRegister());
        labelLoginName.setVisible(false);
        labelLoginText1.setVisible(false);
        labelLoginText2.setVisible(false);
    }


    //sprawdzenie logowania
    private boolean checkLoginData(){
        String login = textLogin.getText();
        String password = textPassword.getText();

        if(login.isEmpty() || password.isEmpty()){
            Utils.createSimpleDialog("Logowanie","", "Pola nie mogą być puste!");
            return false;
        }
            return true;
    }

    //sprawdzenie danych przy rejestracji
    private boolean checkRegisterData() {
        String login = textLoginR.getText();
        String password = textPasswordR.getText();
        String passwordR = textRepeatPasswordR.getText();

        if (login.isEmpty() || password.isEmpty()) {
            Utils.createSimpleDialog("Rejestracja", "", "Pola nie mogą być puste!");
            return false;
        }
        if (login.length() < 3 || password.length() < 5) {
            Utils.createSimpleDialog("Rejestracja", "Login min. 3 znaki, pass min. 5 znaków", "Dane logowania za krótkie!");
            return false;
        }
        if (!password.equals(passwordR)) {
            Utils.createSimpleDialog("Rejestracja", "", "Hasła nie są zgodne!");
            return false;
        }
        return true;
    }

    //Zadanie - rejestracja!!!

    private void tryLogin() {
        String login = textLogin.getText();
        String password = textPassword.getText();


        if(!checkLoginData()){
            return;
        }

        if (userDao.login(login, password)){

            //user session przechowuje informacje w całym systemie (naszym), o tym że user jest zalogowany
            userSession.setUsername(login);
            userSession.setLoggedIn(true);
            //komunikat
             //  Utils.createSimpleDialog("Logowanie", "", "Zalogowano poprawnie!");

            //nowy stage - okienko otwierane po poprawnym zalogowaniu
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
                Stage stageRoot = (Stage) buttonLogin.getScene().getWindow();
                stageRoot.setScene(new Scene(root, 600, 400));

            } catch (IOException e) {
                e.printStackTrace();
            }

            labelLoginName.setVisible(false);
            labelLoginText1.setVisible(false);
            labelLoginText2.setVisible(false);
        }else{
            Utils.createSimpleDialog("Logowanie","", "Błąd logowania!");
            labelLoginName.setVisible(true);
            labelLoginText1.setVisible(true);
            labelLoginText2.setVisible(true);
            labelLoginName.setText(login);

        }
    }


    private void tryRegister() {

        String login = textLoginR.getText();
        String password = textPasswordR.getText();
    

        if(!checkRegisterData()){
            return;
        }

        if (!userDao.register(login, password)){  
            Utils.createSimpleDialog("Rejestracja", "", "Dodano poprawnie!");
        }else{
            Utils.createSimpleDialog("Rejestracja","", "Login jest już zajęty!");

        }
    }
}
