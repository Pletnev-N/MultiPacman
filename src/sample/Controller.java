package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.canvas.Canvas;


public class Controller {


    @FXML
    private AnchorPane pane;
    @FXML
    public Canvas canvas;
    public static GraphicsContext graphics;

    public Pacman myPacman;
    public Map map;



    @FXML
    public void initialize() {
        graphics = canvas.getGraphicsContext2D();
        myPacman=new Pacman(1,1,1,0,1.0);
        map=new Map();
        Game game = new Game(canvas,map,myPacman);
        game.setDaemon(true);
        game.start();
    }


    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()==KeyCode.LEFT){
            myPacman.newDirectionX = -1;
            myPacman.newDirectionY = 0;
        }
        if (keyEvent.getCode()==KeyCode.RIGHT){
            myPacman.newDirectionX = 1;
            myPacman.newDirectionY = 0;
        }
        if (keyEvent.getCode()==KeyCode.UP){
            myPacman.newDirectionX = 0;
            myPacman.newDirectionY = -1;
        }
        if (keyEvent.getCode()==KeyCode.DOWN){
            myPacman.newDirectionX = 0;
            myPacman.newDirectionY = 1;
        }
    }

}
