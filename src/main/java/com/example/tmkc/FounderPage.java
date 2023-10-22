package com.example.tmkc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FounderPage {
    @FXML
    public void setLogout(ActionEvent event){
        scenechanger.changeScene(event,"LoginPage.fxml","Login");
    }
    public void setAbout(ActionEvent event){scenechanger.changeScene(event,"AboutPage.fxml","About Us");}
    public void setContact(ActionEvent event){scenechanger.changeScene(event,"FounderPage.fxml","Contact Us");}
    public void setHome(ActionEvent event){scenechanger.changeScene(event,"Home.fxml","ClothAid");}
}
