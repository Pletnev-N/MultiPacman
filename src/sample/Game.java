package sample;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Game extends Thread {

    public static final int CELL_SIZE=31;
    public static final int WIDTH=15;
    public static final int HEIGHT=11;

    private Canvas canvas;
    private Pacman pacman;
    private Map map;

    public Game(Canvas canv, Map map_, Pacman pacman_) {
        canvas=canv;
        map=map_;
        pacman=pacman_;
    }

    public void run() {
        while (true) {
            Controller.graphics.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
            calculateScene();
            drawFrame(Controller.graphics);
            try { Thread.sleep(18); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    private void drawFrame(GraphicsContext gc) {
        map.paint(gc);
        pacman.paint(gc);
    }

    private void calculateScene() {
        pacman.move(map);
    }


}
