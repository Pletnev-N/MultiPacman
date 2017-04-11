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

    public Pacman pacman1;
    public Map map;



    @FXML
    public void initialize() {
        graphics = canvas.getGraphicsContext2D();
        pacman1=new Pacman(1,1,1,0,1.0);
        map=new Map();
        Game game = new Game(canvas,map,pacman1);
        game.setDaemon(true);
        game.start();
    }


    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode()==KeyCode.LEFT){
            //if ( (map.massive[pacman1.Y/Game.CELL_SIZE][(pacman1.X-1)/Game.CELL_SIZE]!=map.WALL)&&
                    //(map.massive[(pacman1.Y+Game.CELL_SIZE-1)/Game.CELL_SIZE][(pacman1.X-1)/Game.CELL_SIZE]!=map.WALL) ) {
                pacman1.newDirectionX = -1;
                pacman1.newDirectionY = 0;
            //}
        }
        if (keyEvent.getCode()==KeyCode.RIGHT){
           // if ( (map.massive[pacman1.Y/Game.CELL_SIZE][(pacman1.X+Game.CELL_SIZE)/Game.CELL_SIZE]!=map.WALL)&&
                    //(map.massive[(pacman1.Y+Game.CELL_SIZE-1)/Game.CELL_SIZE][(pacman1.X+Game.CELL_SIZE)/Game.CELL_SIZE]!=map.WALL) ) {
                pacman1.newDirectionX = 1;
                pacman1.newDirectionY = 0;
           // }
        }
        if (keyEvent.getCode()==KeyCode.UP){
           // if ( (map.massive[(pacman1.Y-1)/Game.CELL_SIZE][pacman1.X/Game.CELL_SIZE]!=map.WALL)&&
                    //(map.massive[(pacman1.Y-1)/Game.CELL_SIZE][(pacman1.X+Game.CELL_SIZE-1)/Game.CELL_SIZE]!=map.WALL) ) {
                pacman1.newDirectionX = 0;
                pacman1.newDirectionY = -1;
            //}
        }
        if (keyEvent.getCode()==KeyCode.DOWN){
           // if ( (map.massive[(pacman1.Y+Game.CELL_SIZE)/Game.CELL_SIZE][pacman1.X/Game.CELL_SIZE]!=map.WALL)&&
                   // (map.massive[(pacman1.Y+Game.CELL_SIZE)/Game.CELL_SIZE][(pacman1.X+Game.CELL_SIZE-1)/Game.CELL_SIZE]!=map.WALL) ) {
                pacman1.newDirectionX = 0;
                pacman1.newDirectionY = 1;
            //}
        }
    }

}
