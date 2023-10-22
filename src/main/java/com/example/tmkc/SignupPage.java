package com.example.tmkc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignupPage implements Initializable {

    @FXML
    private TextField txt_user;
    @FXML
    private TextField txt_fname;
    @FXML
    private TextField txt_address;
    @FXML
    private PasswordField txt_pass;
    @FXML
    private TextField txt_email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }
    @FXML
    public void setBt_signup(ActionEvent event){
        if(!txt_user.getText().isBlank() && !txt_pass.getText().isBlank()&&!txt_fname.getText().isBlank()&&!txt_email.getText().isBlank())
        {

            signUpUser(event,txt_user.getText(),txt_pass.getText(),txt_fname.getText(),txt_address.getText(),txt_email.getText());

        }
        else{

            System.out.println("Please fill all the details!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all the required info to sign up!");
            alert.show();

        }
    }
    @FXML
    public void setLoginPage(ActionEvent event){
        scenechanger.changeScene(event,"LoginPage.fxml","Login");
    }
    public  void signUpUser(ActionEvent event, String username, String password, String name, String address, String email) {
        Connection connection = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            psCheckUserExists = ((java.sql.Connection) connection).prepareStatement("SELECT * FROM user_acc WHERE username = ? ;");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User Already Exists !!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                try{
                    signInsert(event,username,password,name,address,email);
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void signInsert(ActionEvent event,String username,String password,String name,String address,String email) {
        Connection connection =null;
        PreparedStatement psCheckUserExists =null;
        ResultSet resultSet =null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            String insertQuery = "INSERT INTO user_acc (username, password,email, name,address) VALUES (?, ?, ?, ?, ?);";
            psCheckUserExists = ((Connection) connection).prepareStatement(insertQuery);
            psCheckUserExists.setString(1, username);
            psCheckUserExists.setString(2, password);
            psCheckUserExists.setString(3, email);
            psCheckUserExists.setString(4, name);
            psCheckUserExists.setString(5, address);
            psCheckUserExists.executeUpdate();
                dataBase.user = username;
                dataBase.mail = email;
                HomePage.getUsername = username;
                scenechanger.changeScene(event, "Home.fxml", "Home");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

