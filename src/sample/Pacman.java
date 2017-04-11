package sample;


import javafx.scene.image.Image;

public class Pacman {

    public int X,Y;
    public int directionX,directionY;
    public double speed;
    public int playerNum;
    public Image pic;


    public Pacman(int i,int j, int dirX, int dirY, double sp){
        X=j*Game.CELL_SIZE;
        Y=i*Game.CELL_SIZE;
        directionX=dirX;
        directionY=dirY;
        speed=sp;
        pic=new Image("pacman1.png");
    }

    public void move(Map map){
        int centerj=X+Game.CELL_SIZE/2;
        int centeri=Y+Game.CELL_SIZE/2;
        if (directionX!=0){
            if ((map.massive[(Y+Game.CELL_SIZE-1)/Game.CELL_SIZE][(centerj+directionX*(Game.CELL_SIZE/2+1))/Game.CELL_SIZE]!=map.WALL)
                    && (map.massive[Y/Game.CELL_SIZE][(centerj+directionX*(Game.CELL_SIZE/2+1))/Game.CELL_SIZE]!=map.WALL))
                X += directionX * speed;
        }
        if (directionY!=0){
            if ((map.massive[(centeri+directionY*(Game.CELL_SIZE/2+1))/Game.CELL_SIZE][X/Game.CELL_SIZE]!=map.WALL)
                    && (map.massive[(centeri+directionY*(Game.CELL_SIZE/2+1))/Game.CELL_SIZE][(X+Game.CELL_SIZE-1)/Game.CELL_SIZE]!=map.WALL))
                Y += directionY * speed;
        }
        /*int centeri,centerj;
        centerj=(int)X+Game.CELL_SIZE/2;
        centeri=(int)Y+Game.CELL_SIZE/2;
        if (map.massive[(centeri+directionY*(Game.CELL_SIZE/2))/Game.CELL_SIZE][(centerj+directionX*(Game.CELL_SIZE/2))/Game.CELL_SIZE]!=map.WALL) {
            X += directionX * speed;
            Y += directionY * speed;
        }*/
    }

}
