package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.awt.TextField;
import java.io.IOException;


public class MenuController {


    public javafx.scene.control.TextField nameField;
    public javafx.scene.control.TextField passwordField;
    public Text textLabel;
    @FXML
    private AnchorPane pane;
    private String password;

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
        ShowScene("menu.fxml");
    }

    public void creategame(MouseEvent mouseEvent) throws IOException {
        Client.connectToServer();
        Client.out.writeUTF("create");
        Client.out.writeUTF(GameController.myName);
        ShowScene("creategame.fxml");
    }

    public void findgame(MouseEvent mouseEvent) throws IOException {
        ShowScene("player-spectator.fxml");
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

            Client.in.readUTF();
            Stage stage = (Stage) pane.getScene().getWindow();
            Parent gameRoot =  FXMLLoader.load(getClass().getResource("game.fxml"));
            stage.setScene(new Scene( gameRoot, Game.CELL_SIZE*Game.WIDTH, Game.CELL_SIZE*Game.HEIGHT));
            stage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void menu(MouseEvent mouseEvent) throws IOException {
        GameController.myName = nameField.getText();
        password = passwordField.getText();
        Client.connectToServer();
        Client.out.writeUTF("check");
        Client.out.writeUTF(GameController.myName);
        Client.out.writeUTF(password);
        String answer = Client.in.readUTF();
        if (answer.equals("incorrect")) {
            Client.out.writeUTF("close");
            Client.socket.close();
            textLabel.setText("incorrect password");
        }
        else
            if (answer.equals("ok")) {
                Client.out.writeUTF("close");
                Client.socket.close();
                ShowScene("menu.fxml");
            }
        else
                ShowScene("newUser.fxml");
    }

    private void ShowScene(String name) {
        Stage stage = (Stage) pane.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(name));
            stage.setScene(new Scene(root, /*Game.CELL_SIZE*Game.WIDTH*/600, /*Game.CELL_SIZE*Game.HEIGHT*/400));
        } catch (IOException e) { e.printStackTrace(); }
        stage.show();
    }

    public void backToEnterName(MouseEvent mouseEvent) throws IOException {
        Client.out.writeUTF("close");
        Client.socket.close();
        ShowScene("nameEnter.fxml");
    }

    public void createNewUser(MouseEvent mouseEvent) throws IOException {
        Client.out.writeUTF("new");
        //Client.out.writeUTF(GameController.myName);
        //Client.out.writeUTF(password);
        Client.socket.close();
        ShowScene("menu.fxml");
    }
}
