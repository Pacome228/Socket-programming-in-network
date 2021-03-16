import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GuessingClient {
    public static void main(String[] args) {
        if (args.length == 3) {
            String ipserv, iptest, porttest;
            ipserv = args[0];
            iptest = args[1];
            porttest = args[2];
            try {
                Socket socket = new Socket(iptest, Integer.parseInt(porttest));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
                while (true) {
                    out.println(ipserv + " 2206");
                    out.flush();
                    String response = in.readLine();
                    if (response.equals("OK")) {
                        break;
                    }
                   
                }

                socket.close();

            } catch (IOException ex) {
                Logger.getLogger(GuessingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.err.println("Invalid argument");
        }
    }
}
