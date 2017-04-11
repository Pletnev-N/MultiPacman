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

import javax.xml.soap.Text;
import java.awt.font.TextLayout;
import java.io.IOException;


public class MenuController {
   // @FXML
   // private TextLayout startgame;
    @FXML
    private AnchorPane pane;
    @FXML public void startgame(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        //stage.close();
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =  FXMLLoader.load(getClass().getResource("sample.fxml"));
        //stage = new Stage();
       // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.setTitle("Pacman");
        stage.setScene(new Scene(root, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
        stage.show();
    }

    public void exitgame(MouseEvent mouseEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        //stage.close();
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =  FXMLLoader.load(getClass().getResource("menu.fxml"));
        //stage = new Stage();
        // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.setTitle("Pacman");
        stage.setScene(new Scene(root, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
        stage.show();
    }

    public void creategame(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        //stage.close();
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =  FXMLLoader.load(getClass().getResource("creategame.fxml"));
        //stage = new Stage();
        // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.setTitle("Pacman");
        stage.setScene(new Scene(root, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
        stage.show();
    }

    public void findgame(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        //stage.close();
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root =  FXMLLoader.load(getClass().getResource("findgame.fxml"));
        //stage = new Stage();
        // stage.initModality(Modality.APPLICATION_MODAL);
        //stage.setTitle("Pacman");
        stage.setScene(new Scene(root, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
        stage.show();
    }
}
