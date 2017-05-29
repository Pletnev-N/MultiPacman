package sample.server;


import java.io.IOException;
import java.util.ArrayList;

public class PlayerListener extends Thread {

    private ArrayList<User> users;
    private User player;
    private int num;

    public PlayerListener(User player_, int num_, ArrayList<User> users_) {
        users = users_;
        player = player_;
        num = num_;
    }

    public void run() {
        try {
            String input = "";
            while (true) {
                input = player.in.readUTF();
                for (User user: users) {
                    user.out.writeInt(num);
                    user.out.writeUTF(input);
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

}
