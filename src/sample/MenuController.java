package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class MenuController {
    @FXML private ImageView startgame;
    @FXML public void startgame(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) startgame.getScene().getWindow();
        //stage.close();
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =  FXMLLoader.load(getClass().getResource("sample.fxml"));
        //stage = new Stage();
       // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.setTitle("Pacman");
        stage.setScene(new Scene(root, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
        stage.show();
    }
}
