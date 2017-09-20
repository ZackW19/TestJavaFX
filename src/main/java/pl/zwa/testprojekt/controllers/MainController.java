package pl.zwa.testprojekt.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button; //ważne aby z tego pobierać
import javafx.scene.input.MouseEvent;
import pl.zwa.testprojekt.models.MysqlConnector;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {  //zainicjuje się po uruchomieniu okienka

    @FXML
    Button buttonHello;

    public void initialize(URL location, ResourceBundle resources) {
        //metoda obsługi przycisku nr 1 - programistyczna
        //może być lambda bo jest jedna metoda!
        ///buttonHello.setOnMouseClicked(s -> System.out.println("Hello!"));
        MysqlConnector.getInstance();  //test połączenia do bazy
    }

    //metoda obsługi przycisku nr 2 - ze SceneBuilder (Code - zdarzenie, nazwa metody i tworzymy ją tutaj
   /*
        public void onButtonHelloClicked(MouseEvent event) {
        if (event.isAltDown()) //klik możliwy z klawiszem ALT
        {
            System.out.println("Hello!!!, Kliknięć: " + event.getClickCount());
        }
    }
    */
}



