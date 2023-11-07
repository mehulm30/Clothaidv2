package com.example.tmkc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    public static String getUsername;
    public static String lol;
    @FXML
    private Label wel_label;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       getName(getUsername);
        wel_label.setText("Welcome "+lol+"!");
    }
    @FXML
    public void setLogout(ActionEvent event){
        scenechanger.changeScene(event,"LoginPage.fxml","Login");
    }
    public void setAbout(ActionEvent event){scenechanger.changeScene(event,"AboutPage.fxml","About Us");}
    public void setContact(ActionEvent event){scenechanger.changeScene(event,"FounderPage.fxml","Contact Us");}
    public void setHome(ActionEvent event){scenechanger.changeScene(event,"Home.fxml","ClothAid");}

    public  void setBook(ActionEvent event){
        scenechanger.changeScene(event,"Donation.fxml","Donation");
    }
    public static void getName(String username){
        Connection connection = null;
        PreparedStatement preparedStatementname = null;
        ResultSet resultSetname = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            preparedStatementname = ((java.sql.Connection) connection).prepareStatement("SELECT name FROM user_acc WHERE username = '"+username+"'");
            resultSetname = preparedStatementname.executeQuery();
            while(resultSetname.next()){

                String name = resultSetname.getNString("name");
                lol=name;

            }

        }
        catch (SQLException e){

            e.printStackTrace();

        }

    }

}

