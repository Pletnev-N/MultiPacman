package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.canvas.Canvas;

import java.io.IOException;


public class GameController {


    @FXML
    private AnchorPane pane;
    @FXML
    public Canvas canvas;
    public static GraphicsContext graphics;

    public static Pacman[] pacmans;
    public int pacmansNum;
    public Pacman myPacman;
    public static String myName="player1";
    public static String myType="player";

    public Map map;



    @FXML
    public void initialize() {
        graphics = canvas.getGraphicsContext2D();
        map=new Map();
        try {
            pacmans = new Pacman[pacmansNum = Client.in.readInt()];
            for (int i=0; i<pacmansNum; i++) {
                pacmans[i] = new Pacman(map.startPosI(i),map.startPosJ(i),map.startDirX(i),map.startDirY(i),Client.in.readUTF());
                if (pacmans[i].playerName.equals(myName)) myPacman = pacmans[i];
            }
        } catch (IOException e) { e.printStackTrace(); }

        Thread serverListener = new Thread(){
            @Override
            public void run() {
                serverListenerRun();
            }
        };
        serverListener.setDaemon(true);
        serverListener.start();

        Game game = new Game(canvas,map);
        game.setDaemon(true);
        game.start();
    }


    public void keyPressed(KeyEvent keyEvent) throws IOException {
        if (myType.equals("player")) {
            if (keyEvent.getCode() == KeyCode.LEFT) {
                Client.out.writeUTF("left");
                myPacman.newDirectionX = -1;
                myPacman.newDirectionY = 0;
            }
            if (keyEvent.getCode() == KeyCode.RIGHT) {
                Client.out.writeUTF("right");
                myPacman.newDirectionX = 1;
                myPacman.newDirectionY = 0;
            }
            if (keyEvent.getCode() == KeyCode.UP) {
                Client.out.writeUTF("up");
                myPacman.newDirectionX = 0;
                myPacman.newDirectionY = -1;
            }
            if (keyEvent.getCode() == KeyCode.DOWN) {
                Client.out.writeUTF("down");
                myPacman.newDirectionX = 0;
                myPacman.newDirectionY = 1;
            }
        }
    }

    private void serverListenerRun() {
        int num = 0;
        String input = "";
        while (true) {
            try {
                num = Client.in.readInt();
                input = Client.in.readUTF();
            } catch (IOException e) { e.printStackTrace(); }

            if (pacmans[num]!=myPacman)
            switch(input) {
                case "left":
                    pacmans[num].newDirectionX = -1;
                    pacmans[num].newDirectionY = 0;
                    break;
                case "right":
                    pacmans[num].newDirectionX = 1;
                    pacmans[num].newDirectionY = 0;
                    break;
                case "up":
                    pacmans[num].newDirectionX = 0;
                    pacmans[num].newDirectionY = -1;
                    break;
                case "down":
                    pacmans[num].newDirectionX = 0;
                    pacmans[num].newDirectionY = 1;
                    break;
            }
        }
    }

}
