package sample.server;

import java.io.*;
import java.net.*;
import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;


public class SessionThread extends Thread{

    public int sessionNum;
    public ArrayList<User> users;
    public User[] players;
    public PlayerListener[] listeners;
    public int playersNum;
    public String status;

    public SessionThread(int num, Socket soc, String creatorName){
        sessionNum=num;
        users = new ArrayList<User>();
        users.add(new User(soc,creatorName,"player"));
        status="lobby";
    }

    public void run() {

        /*Thread waitForPlayers = new Thread(){
            @Override
            public void run() {
                waitForPlayersRun();
            }
        };*/

        /*Thread waitForStart = new Thread(){
            @Override
            public void run() {
                waitForStartRun();
            }
        };

        waitForStart.setDaemon(true);
        waitForStart.start();
        try {
            waitForStart.join();
        } catch(InterruptedException e) {}
        */

        try {
            users.get(0).in.readUTF();
            for (User user: users){
                user.out.writeUTF("start");
            }
            status = "game";
        } catch(IOException x) { x.printStackTrace(); }

        players = new User[playersNum = playersAmount()];
        int i = 0;
        for (User user: users) {
            if (user.type.equals("player")) {
                players[i] = user;
                i++;
            }
        }
        try {
            for (User user: users) user.out.writeInt(playersNum);
            for (i=0; i<playersNum; i++) {
                for (User user : users) user.out.writeUTF(players[i].name);
            }
        } catch (IOException e) { e.printStackTrace(); }

        //началась сама игра
        listeners = new PlayerListener[playersNum];
        for (i=0; i<playersNum; i++) {
            listeners[i] = new PlayerListener(players[i],i,users);
            listeners[i].setDaemon(true);
            listeners[i].start();
        }

    }


    /*private void waitForPlayersRun() {
        try {
            try {
                Socket socket = Server.ss.accept();
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);
                String input = in.readUTF();
                if (input.equals("player")) {
                    if (playersAmount() != 4) {
                        users.add(new User(socket,in.readUTF(),input));
                    }
                }
                else users.add(new User(socket,in.readUTF(),input));
            } catch (ClosedByInterruptException e){ System.out.print("interrupted"); }
        } catch(IOException x) { x.printStackTrace(); }
    }*/

    private void waitForStartRun(/*Thread waitForPlayers*/) {
        try {
            users.get(0).in.readUTF();
            //waitForPlayers.interrupt();
            for (User user: users){
                user.out.writeUTF("start");
            }
            status = "game";
        } catch(IOException x) { x.printStackTrace(); }
    }

    public int playersAmount() {
        int result = 0;
        for (User user: users) {
            if (user.type.equals("player")) result++;
        }
        return result;
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
