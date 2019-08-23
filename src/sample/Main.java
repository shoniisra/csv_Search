package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Button button = new Button("Choose");
        Label chosen = new Label();
        button.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(primaryStage);
            if (file != null) {
                String fileAsString = file.toString();

                chosen.setText("Chosen: " + fileAsString);
            } else {
                chosen.setText(null);
            }
        });
        VBox layout = new VBox(10, button, chosen);
        layout.setMinWidth(400);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));



        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Csv Tool");
        primaryStage.setScene(new Scene(root, 425, 650));
        //primaryStage.setScene(new Scene(layout));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
