package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.*;

public class Map {

    public final int EMPTY=0;
    public final int POINT=1;
    public final int WALL=2;

    public volatile int pointsNum = 69;
    public int massive[][];
    private Image emptyPic;
    private Image pointPic;
    private Image wallPic;

    public Map(){
        massive=new int[Game.HEIGHT][Game.WIDTH];
        emptyPic=new Image("empty.png");
        pointPic=new Image("point.png");
        wallPic=new Image("wall.png");
        try {getMasFromFile("map.txt",massive);}
        catch (IOException e){System.out.print(e.getMessage());}
    }

    private void getMasFromFile(String fileName, int mas[][]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        for(int i=0;i<Game.HEIGHT;i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                mas[i][j] = reader.read() - 48;
                System.out.print(mas[i][j]);
            }
            reader.read(); reader.read(); System.out.print("\n");
        }
        reader.close();
    }

    public void paint(GraphicsContext gc){
        for(int i=0;i<Game.HEIGHT;i++)
            for(int j=0;j<Game.WIDTH;j++)
            {
                if (massive[i][j]==EMPTY) gc.drawImage(emptyPic, j*Game.CELL_SIZE, i*Game.CELL_SIZE);
                if (massive[i][j]==POINT) gc.drawImage(pointPic, j*Game.CELL_SIZE, i*Game.CELL_SIZE);
                if (massive[i][j]==WALL) gc.drawImage(wallPic, j*Game.CELL_SIZE, i*Game.CELL_SIZE);
            }
    }

    public int startPosI(int num) {
        int result = 0;
        if ((num==0)||(num==1)) result = 1;
        if ((num==2)||(num==3)) result = 9;
        return result;
    }

    public int startPosJ(int num) {
        int result = 0;
        if ((num==0)||(num==3)) result = 1;
        if ((num==1)||(num==2)) result = 13;
        return result;
    }

    public int startDirX(int num) {
        int result = 0;
        if (num==0) result = 1;
        if ((num==1)||(num==3)) result = 0;
        if (num==2) result = -1;
        return result;
    }

    public int startDirY(int num) {
        int result = 0;
        if (num==1) result = 1;
        if ((num==0)||(num==2)) result = 0;
        if (num==3) result = -1;
        return result;
    }
}
