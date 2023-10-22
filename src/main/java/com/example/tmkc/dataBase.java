package com.example.tmkc;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;

public class dataBase {
    String lol;
    public static void signUpUser(ActionEvent event, String username, String password, String name, String address) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            psCheckUserExists = ((java.sql.Connection) connection).prepareStatement("SELECT * FROM user_acc WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("User Already Exists !!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                psInsert = ((java.sql.Connection) connection).prepareStatement("INSERT INTO user_acc VALUES('" + username + "','" + name + "','" + password + "','" + address + "');");
                psInsert.executeUpdate();
//                connection=null;
                HomePage.getUsername=username;
                scenechanger.changeScene(event, "Home.fxml", "Home");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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
            if (psInsert != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//
    public static void loginUser(ActionEvent event, String username, String password) {
        Connection connection = null;
//        HomePage home =null;
        PreparedStatement preparedStatement = null;
        PreparedStatement setprepareName = null;
        ResultSet resultSetName = null;
//        Connection conSetName = null;
        ResultSet resultSet = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clothaid", "root", "");
            preparedStatement = ((java.sql.Connection) connection).prepareStatement("SELECT password FROM user_acc WHERE username = '"+username+"'");
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
                        connection=null;
                        HomePage.getUsername=username;
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
        }
        finally {
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
}
