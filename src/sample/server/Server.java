package sample.server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    public static ArrayList<Lobby> lobbyList;

    public static void main(String[] ar){
        lobbyList = new ArrayList<Lobby>();
        int port = 6666;
        try {
            ServerSocket ss = new ServerSocket(port);
            while (true){
                Socket socket = ss.accept();
                ServerThread sThread = new ServerThread(socket);
                sThread.start();
            }
        } catch(Exception x) { x.printStackTrace(); }

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