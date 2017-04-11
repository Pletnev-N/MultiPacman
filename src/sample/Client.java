package sample;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] ar) {
        int serverPort = 6666; // порт серва
        String address = "127.0.0.1"; // локальный ип
        try {
          //  InetAddress ipAddress = InetAddress.getByName(address); // адресс
          //  Socket socket = new Socket(ipAddress, serverPort); // новый сокет

          //  InputStream sin = socket.getInputStream();
          //  OutputStream sout = socket.getOutputStream();

           // DataInputStream in = new DataInputStream(sin);
           // DataOutputStream out = new DataOutputStream(sout);
            while (true){
                //тело клиента
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
