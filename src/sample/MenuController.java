package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.awt.TextField;
import java.io.IOException;


public class MenuController {


    public javafx.scene.control.TextField nameField;
    @FXML
    private AnchorPane pane;

    @FXML public void startgame(MouseEvent mouseEvent) throws IOException {
        Client.out.writeUTF("start");
        Client.in.readUTF();
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("game.fxml"));
        stage.setScene(new Scene( root, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
        stage.show();
    }

    public void exitgame(MouseEvent mouseEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setScene(new Scene(root, /*Game.CELL_SIZE*Game.WIDTH*/600, /*Game.CELL_SIZE*Game.HEIGHT*/400));
        stage.show();
    }

    public void creategame(MouseEvent mouseEvent) throws IOException {
        Client.connectToServer();
        Client.out.writeUTF("create");
        Client.out.writeUTF(GameController.myName);
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("creategame.fxml"));
        stage.setScene(new Scene(root, /*Game.CELL_SIZE*Game.WIDTH*/600, /*Game.CELL_SIZE*Game.HEIGHT*/400));
        stage.show();
    }

    public void findgame(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("player-spectator.fxml"));
        stage.setScene(new Scene(root, /*Game.CELL_SIZE*Game.WIDTH*/600, /*Game.CELL_SIZE*Game.HEIGHT*/400));
        stage.show();
    }

    public void setPlayerType(MouseEvent mouseEvent) {
        setType("player");
    }

    public void setSpectatorType(MouseEvent mouseEvent) {
        setType("spectator");
    }

    private void setType(String type) {
        Client.connectToServer();
        try {
            Client.out.writeUTF("find");
            GameController.myType = type;
            Client.out.writeUTF(GameController.myType);
            Client.out.writeUTF(GameController.myName);

            Stage stage = (Stage) pane.getScene().getWindow();
            Parent root =  FXMLLoader.load(getClass().getResource("findgame.fxml"));
            stage.setScene(new Scene(root,600,400));
            stage.show();

            Client.in.readUTF();
            //Stage gameStage = (Stage) pane.getScene().getWindow();
            Parent gameRoot =  FXMLLoader.load(getClass().getResource("game.fxml"));
            stage.setScene(new Scene( gameRoot, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
            stage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void menu(MouseEvent mouseEvent) throws IOException {
        GameController.myName = nameField.getText();
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root =  FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage.setScene(new Scene(root, /*Game.CELL_SIZE*Game.WIDTH*/600, /*Game.CELL_SIZE*Game.HEIGHT*/400));
        stage.show();
    }
}
