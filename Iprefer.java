/*
 * @author Andrei Freund
 * avfreund@wisc.edu
 * cs-login: andrei
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
public class Iprefer{
    public static void main(String args[]){
        if(args.length == 0){
            invalid();
        }
        switch (args[0]){
            case "-c":
                if(args.length != 7) invalid();
                if(validatePort(args[4]) && validateHost(args[2]) && validateTime(args[6])){
                    // do client mode stuff :p
                    System.out.println("Client Mode");
                }
                break;
            case "-s":
                if(args.length != 3) invalid();
                if(validatePort(args[2])){
                    //do server stuff
                    System.out.println("Server Mode");
                }
                break;
            default:
                invalid();
        }
    }
    private static boolean validateHost(String host){
        return true;
    }
    /* 
     * validatePort
     * Ensures that the port argument is an integer between 65535 and 1024
     *
     */
    private static boolean validatePort(String port){
        try{
            int p = Integer.parseInt(port);
            if(p > 65535 || p < 1024) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            System.out.println("Error: port number must be in the range 1024 to 65535");
            System.exit(1);
        }
        return true;
    }
    /*
     * validateTime
     * Ensures that the time is a positive integer
     *
     */
    private static boolean validateTime(String time){
        try{
            int t = Integer.parseInt(time);
            if(t < 0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException e){
            System.out.println("Error: time must be a positive integer representing seconds");
            System.exit(1);
        }
        return true;
    }
    private static void invalid(){
            System.out.println("Error: invalid arguments");
            System.exit(1);
    }

    /*
     * Client Class
     * Represents a client 
     */
    private class Client{
        private int port;
        private String ip;
        private Client(String ip, int port, int time){
            this.ip = ip;
            this.port = port;
            run(time);
        }
        private void run(int time){
            Socket s;
            String msg = new String(new byte[1000], StandardCharsets.UTF_8); // 1000 byte 0 filled message
            long sent = 0;
            try {
                s = new Socket(ip,port);
                PrintWriter p = new PrintWriter(s.getOutputStream(),true);
                long stop = (long) time * 1000 + System.currentTimeMillis();
                while (System.currentTimeMillis() < stop){
                    p.println(msg);
                    sent++;
                }
                p.close();
                s.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
				System.exit(1);
            }
            double r = (sent * 8) / 1000.0 / time;
			System.out.printf("sent=" + sent + " KB rate=%.3f Mbps\n", r);
            
        }
    }
}