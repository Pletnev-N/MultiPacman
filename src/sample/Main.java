package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(new Scene(root, /*Game.CELL_SIZE*Game.WIDTH*/600, /*Game.CELL_SIZE*Game.HEIGHT*/400));
        primaryStage.show();
    }





}
