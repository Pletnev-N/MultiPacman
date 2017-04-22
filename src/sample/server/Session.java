package sample.server;

import java.net.Socket;
import java.util.ArrayList;

public class Session {

    public ArrayList<Socket> players;
    public ArrayList<Socket> spectators;
    String status;

    public Session(Socket soc){
        players = new ArrayList<Socket>();
        players.add(soc);
        spectators = new ArrayList<Socket>();
        status="lobby";
    }

}
