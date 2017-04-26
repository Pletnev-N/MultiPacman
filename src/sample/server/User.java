package sample.server;


import java.io.*;
import java.net.Socket;

public class User {

    public Socket socket;
    public String name;
    public String type;
    public DataInputStream in;
    public DataOutputStream out;

    public User(Socket soc, String nm, String tp) {
        socket = soc;
        name = nm;
        type = tp;
        try {
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
        } catch(IOException e) { e.printStackTrace(); }
    }

}
