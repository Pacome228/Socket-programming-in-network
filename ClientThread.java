import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;
import java.util.regex.Pattern;



public class ClientThread extends Thread {
    private Socket s;
    private int timeout;
    private int upperbound;
    
    public ClientThread(Socket s, int upperbound, int timeout) {
        this.s = s;
        this.timeout = timeout;
        this.upperbound = upperbound;
    }

@Override
    public void run() {
      try { 
          BufferedReader in;
          PrintWriter out ;
                
                //random number
                Random rand = new Random(); //instance of random class
                //generate random values from 0-20
                int int_random = rand.nextInt(this.upperbound);
                this.s. setSoTimeout(this.timeout);
                while (true) {
                    
                    in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
                    this.s.getOutputStream().flush(); // not sure
                    out = new PrintWriter(this.s.getOutputStream(), true);
                    while (true) {
                        try{
                            String cmd = in.readLine();
                           if (cmd != null && Pattern.matches("(^(TRY) \\d+)|(CHEAT)", cmd)) {
                                if (cmd.equals("CHEAT")) {
                                    out.println(int_random);
                                 } else {
                                     // TRY x
                                     int guess = Integer.parseInt(cmd.substring(4));
                                     if (guess < 0 || guess > 20) out.println("WRONG");
                                     else {
                                        if (guess < int_random) out.println("HIGHER");
                                        else if (guess > int_random) out.println("LOWER");
                                        else {
                                            out.println("CORRECT");
                                            out.flush();
                                            break;
                                        }
                                     }
                            }
                            out.flush();
                        } else {
                            out.println("WRONG");
                            out.flush();
                        }
                           
                        } catch(SocketTimeoutException e) {
                            out.println("Timeout");
                            break;
                        }
                        
                      
                    }
                    s.close();
                
                }
         
                     
      
                        
                
    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }


}
