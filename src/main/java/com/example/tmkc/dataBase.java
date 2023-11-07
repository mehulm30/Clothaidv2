package com.example.tmkc;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;

public class dataBase {
    private String Cloth_Type;

    public static String user = null;
    public static String mail = null;
    public String pass;


    //
    public static void loginUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            preparedStatement = ((java.sql.Connection) connection).prepareStatement("SELECT password FROM user_acc WHERE username = '" +username+"'");
            resultSet = preparedStatement.executeQuery();


            if (!resultSet.isBeforeFirst()) {
                System.out.println("User is not found in database");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getNString("password");
                    if (retrievedPassword.equals(password)) {
                        connection = null;
                        HomePage.getUsername = username;
                        scenechanger.changeScene(event, "Home.fxml", "ClothAid");

                    } else {
                        System.out.println("Incorrect Password");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invalid Username or password");
                        alert.show();
                    }
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
            if (preparedStatement != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void setCloth_Type(ActionEvent event, String username, String cloth_Type,String date,String method , String address) {
        Connection connection=null;
        PreparedStatement psDonate = null;
        ResultSet resultDonate = null;
        try{
            int id =1;
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            String insertQuery = "INSERT INTO clothdb (id,username, cloth_type,capital,book_date,address) VALUES (?, ?, ?, ?, ?, ?);";
            psDonate = ((Connection) connection).prepareStatement(insertQuery);
            psDonate.setInt(1, id);
            psDonate.setString(2, username);
            psDonate.setString(3, cloth_Type);
            psDonate.setString(4, method);
            psDonate.setString(5, date);
            psDonate.setString(6, address);
            psDonate.executeUpdate();
            id=id+1;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}