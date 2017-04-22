package sample.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class SessionThread extends Thread{

    public int sessionNum;
    public ArrayList<Socket> players;
    public ArrayList<Socket> spectators;
    public String status;

    public SessionThread(int num, Socket soc){
        sessionNum=num;
        players = new ArrayList<Socket>();
        players.add(soc);
        spectators = new ArrayList<Socket>();
        status="lobby";
    }

    public void run() {

        Thread waitForPlayers = new Thread(){
            @Override
            public void run() {
                try {
                    Socket socket = Server.ss.accept();
                    InputStream sin = socket.getInputStream();
                    OutputStream sout = socket.getOutputStream();
                    DataInputStream in = new DataInputStream(sin);
                    DataOutputStream out = new DataOutputStream(sout);
                    String input = in.readUTF();
                    if (input=="player"){
                        if (players.size()==4) status="ready";
                        else players.add(socket);
                    }
                    if (input=="spectator"){
                        spectators.add(socket);
                    }
                } catch(Exception x) { x.printStackTrace(); }
            }
        };

        Thread waitForStart = new Thread(){
            @Override
            public void run() {
            }
        };

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
