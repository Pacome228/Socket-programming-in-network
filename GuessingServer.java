import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GuessingServer extends Thread {
   // private int nbClient;

    
    public static void main(String[] args){
        
        ServerSocket ss;
        try {
            ss = new ServerSocket(2206);
            while (true) {
                try {
                    Socket s = ss.accept();
                    s.setTcpNoDelay(true);
                    //s. setSoTimeout(5000);
                     new ClientThread(s, 21, 5000).start();
                } catch (IOException e) {
                    System.out.println("I/O error: " + e);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GuessingServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
