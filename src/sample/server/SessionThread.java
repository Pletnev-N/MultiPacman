package sample.server;

import java.io.*;
import java.net.*;
import java.nio.channels.ClosedByInterruptException;
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
                waitForPlayersRun();
            }
        };

        Thread waitForStart = new Thread(){
            @Override
            public void run() {
                waitForStartRun(waitForPlayers);
            }
        };

        waitForPlayers.setDaemon(true);
        waitForStart.setDaemon(true);
        waitForPlayers.start();
        waitForStart.start();
        try {
            waitForStart.join();
        } catch(InterruptedException e) {}


    }


    private void waitForPlayersRun() {
        try {
            try {
                Socket socket = Server.ss.accept();
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);
                String input = in.readUTF();
                if (input.equals("player")) {
                    if (players.size() == 4) status = "ready";
                    else players.add(socket);
                }
                if (input.equals("spectator")) {
                    spectators.add(socket);
                }
            } catch (ClosedByInterruptException e){ System.out.print("interrupted"); }
        } catch(IOException x) { x.printStackTrace(); }
    }

    private void waitForStartRun(Thread waitForPlayers) {
        try {
            InputStream sin = players.get(0).getInputStream();
            OutputStream sout = players.get(0).getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            in.readUTF();
            waitForPlayers.interrupt();
            for (Socket player: players){
                DataOutputStream plOut = new DataOutputStream(player.getOutputStream());
                plOut.writeUTF("start");
            }
            for (Socket spectator: spectators){
                DataOutputStream spOut = new DataOutputStream(spectator.getOutputStream());
                spOut.writeUTF("start");
            }
            status = "game";
        } catch(IOException x) { x.printStackTrace(); }
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
