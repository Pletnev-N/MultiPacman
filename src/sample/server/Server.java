package sample.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server extends Application {

    public static ArrayList<SessionThread> sessionList;
    public static ServerSocket ss;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("server.fxml"));
        primaryStage.setTitle("Pacman Server");
        primaryStage.setScene(new Scene(root,200,150));
        primaryStage.show();

        sessionList = new ArrayList<SessionThread>();
        int num=0;
        int port = 6666;
        try {
            ss = new ServerSocket(port);
            while (true){
                Socket socket = ss.accept();
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);
                String input = in.readUTF();

                if (input.equals("create")){
                    SessionThread session = new SessionThread(num, socket, in.readUTF());
                    session.setDaemon(true);
                    session.start();
                    sessionList.add(num,session);
                    num++;
                    System.out.print("создание игры");
                }

                if (input.equals("find")){
                    String type = in.readUTF();
                    if (type.equals("player")) {
                        for (SessionThread session : sessionList)
                            if (session.playersAmount()<4) {
                                session.users.add(new User(socket,in.readUTF(),type));
                                break;
                            }
                    }
                    if (type.equals("spectator")) {
                        sessionList.get(0).users.add(new User(socket,in.readUTF(),type));
                    }
                    System.out.print("поиск игры");
                }

            }
        } catch(Exception x) { x.printStackTrace(); }
    }

    public static void main(String[] args) {
        launch(args);
    }



    /*public static void main(String[] ar)    {
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket socket = ss.accept();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            while(true) {
                int input;
                input= in.readInt();//получаем с клиента input
                int output=0;
               // вычисляем output в зависимости от input
                out.writeInt(output); // отсылаем клиенту оoutput
                out.flush(); // заставляем поток закончить передачу данных?????
            }
        } catch(Exception x) { x.printStackTrace(); }
    }*/

}