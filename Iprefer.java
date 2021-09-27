/*
 * @author Andrei Freund
 * avfreund@wisc.edu
 * cs-login: andrei
 */
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
}