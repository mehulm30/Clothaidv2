package com.example.tmkc;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class scenechanger {
//    static StackPane stackPane;//

    public static void changeScene(ActionEvent event, String fxmlFile, String Title)
    {
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(scenechanger.class.getResource(fxmlFile));
            root = loader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(Title);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1500), root);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            Parent finalRoot = root;
            fadeTransition.getOnFinished();
            fadeTransition.play();
            stage.setScene(new Scene(root, 850,550));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // Set the new scene after the fade-in transition is finished;
        // stage.setScene(new Scene(root, 850,550));

    }
//
}
