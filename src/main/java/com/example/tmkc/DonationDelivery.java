package com.example.tmkc;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DonationDelivery implements Initializable {

    @FXML
    private Button cancel_btn;
    @FXML
    private Button submit_btn;
    @FXML
    private TextField add;
    @FXML
    private TextField cloth_type;
    @FXML
    private ComboBox<String> money_mode;
    @FXML
    private DatePicker datePicker;
    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){

        money_mode.setItems(FXCollections.observableArrayList("Cash at Pickup","UPI at Pickup"));

    }
    @FXML
    public void setCancel(ActionEvent event){

        scenechanger.changeScene(event,"Home.fxml","Home");
    }
    public void setAbout(ActionEvent event){scenechanger.changeScene(event,"AboutPage.fxml","About Us");}
    public void setContact(ActionEvent event){scenechanger.changeScene(event,"FounderPage.fxml","Contact Us");}
    public void setHome(ActionEvent event){scenechanger.changeScene(event,"Home.fxml","ClothAid");}

    @FXML
    public void setSubmit(ActionEvent event){

            String clothType = cloth_type.getText();
            String donationDate = datePicker.getValue().toString();
            String transactionMethod = money_mode.getValue();
            String addressCheck = add.getText();

            // Process the donation information (you can print it or store it in a database)
            System.out.println("Cloth Type: " + clothType);
            System.out.println("Date to Donate: " + donationDate);
            System.out.println("Transaction Method: " + transactionMethod);
              dataBase.setCloth_Type(event,HomePage.lol,clothType,donationDate,transactionMethod,addressCheck);

            getEmail(HomePage.getUsername,clothType,donationDate,addressCheck,transactionMethod);
            scenechanger.changeScene(event,"Home.fxml","Home");


    }
    @FXML
    private void setLogout(ActionEvent event){

            scenechanger.changeScene(event,"LoginPage.fxml","Login");

    }
    public static void getEmail(String username,String cloth, String date, String add, String method) {
        Connection connection = null;
        PreparedStatement preparedStatementname = null;
        ResultSet resultSetname = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            preparedStatementname = ((java.sql.Connection) connection).prepareStatement("SELECT email FROM user_acc WHERE username = '" + username + "'");
            resultSetname = preparedStatementname.executeQuery();
            while (resultSetname.next()) {
                String email = resultSetname.getNString("email");
                EmailSend.sendEmail(email,"Hey "+HomePage.lol+", You ordered a donation to collect "+cloth+" on  "+date+" to address ' "+add+" '. You have preferred "+method+" for donation. Thank You! Please reply to this mail for feedback! ");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}






