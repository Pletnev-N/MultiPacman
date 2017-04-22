package sample.server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread{

    public int sessionNum;

    public ServerThread(int num){
        sessionNum=num;
    }

    public void run() {
        try {
            Socket socket = Server.ss.accept();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            if (in.readUTF()=="player"){
                if (Server.sessionList.get(sessionNum).players.size()==4) Server.sessionList.get(sessionNum).status="ready";
                else Server.sessionList.get(sessionNum).players.add(socket);
            }
            if (in.readUTF()=="spectator"){
                Server.sessionList.get(sessionNum).spectators.add(socket);
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
