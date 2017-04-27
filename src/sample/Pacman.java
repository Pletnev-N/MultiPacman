package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pacman {

    public int X,Y;
    public int directionX,directionY;
    public int newDirectionX,newDirectionY;
    public double speed;
    public String playerName;
    public int score;
    public Image pic;


    public Pacman(int i,int j, int dirX, int dirY, String name, String imageName){
        X=j*Game.CELL_SIZE+Game.CELL_SIZE/2;
        Y=i*Game.CELL_SIZE+Game.CELL_SIZE/2;
        newDirectionX=directionX=dirX;
        newDirectionY=directionY=dirY;
        speed=1.0;
        score=0;
        playerName = name;
        pic=new Image(imageName);
    }

    public void move(Map map){
        if ( (X%Game.CELL_SIZE==Game.CELL_SIZE/2)&&(Y%Game.CELL_SIZE==Game.CELL_SIZE/2) ){
            if (map.massive[Y/Game.CELL_SIZE][X/Game.CELL_SIZE]==map.POINT){
                score++;
                map.massive[Y/Game.CELL_SIZE][X/Game.CELL_SIZE]=map.EMPTY;
            }
            if (map.massive[(Y+newDirectionY*Game.CELL_SIZE)/Game.CELL_SIZE][(X+newDirectionX*Game.CELL_SIZE)/Game.CELL_SIZE]!=map.WALL){
                directionX=newDirectionX;
                directionY=newDirectionY;
                X += directionX * speed;
                Y += directionY * speed;
            }
            else if (map.massive[(Y+directionY*Game.CELL_SIZE)/Game.CELL_SIZE][(X+directionX*Game.CELL_SIZE)/Game.CELL_SIZE]!=map.WALL){
                X += directionX * speed;
                Y += directionY * speed;
            }
        }
        else{
            X += directionX * speed;
            Y += directionY * speed;
        }
    }

    public void paint(GraphicsContext gc){
        gc.drawImage(pic, X-Game.CELL_SIZE/2, Y-Game.CELL_SIZE/2);
    }

}
