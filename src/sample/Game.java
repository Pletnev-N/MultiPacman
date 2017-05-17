package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Thread {

    public static final int CELL_SIZE=31;
    public static final int WIDTH=15;
    public static final int HEIGHT=11;

    private Canvas canvas;
    private Map map;

    public Game(Canvas canv, Map map_) {
        canvas=canv;
        map=map_;
    }

    public void run() {
        while (true) {
            GameController.graphics.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            calculateScene();
            drawFrame(GameController.graphics);
            try { Thread.sleep(28); }
            catch (InterruptedException e) { e.printStackTrace(); }
            if (map.pointsNum==0)
                break;
        }
    }

    private void drawFrame(GraphicsContext gc) {
        map.paint(gc);
        for (Pacman pacman: GameController.pacmans) {
            pacman.paint(gc);
        }
    }

    private void calculateScene() {
        for (Pacman pacman: GameController.pacmans) {
            pacman.move(map);
        }
    }


}
