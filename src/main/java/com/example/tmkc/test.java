package com.example.tmkc;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class test extends Application{




        private Stage primaryStage;

        @Override
        public void start(Stage primaryStage) {
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Scene Transition Animation");

            Button switchButton = new Button("Switch Scene");
            switchButton.setOnAction(event -> switchScene());

            StackPane root = new StackPane();
            root.getChildren().add(switchButton);

            Scene scene = new Scene(root, 300, 250);
            primaryStage.setScene(scene);

            primaryStage.show();
        }

        private void switchScene() {
            StackPane newRoot = new StackPane();
            Button backButton = new Button("Go Back");
            backButton.setOnAction(event -> switchScene());
            newRoot.getChildren().add(backButton);

            Scene newScene = new Scene(newRoot, 300, 250);

            // Create a fade transition for the scene transition
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), newRoot);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);

            // Set the new scene after the fade transition is finished
            fadeTransition.setOnFinished(event -> primaryStage.setScene(newScene));

            fadeTransition.play();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


