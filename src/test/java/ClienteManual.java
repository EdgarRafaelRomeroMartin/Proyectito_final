import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteManual {
    public static void main(String[] args) throws IOException {
        Scanner Amongus= new Scanner(System.in);
        System.out.println("Introduce el nombre del usario");
        String nombre =Amongus.nextLine();

        String hostName = null;
        int portNumber = 7777;
        if (args.length != 2) {
            System.err.println(
                    "Usage: java EchoClient <host name> <port number>");
            hostName = "localhost";
            portNumber = 7777;
            // System.exit(1);
        } else {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        if (args.length != 2) {
            System.err.println(
                    "Usage: CONNECT <"+nombre+">");
            hostName = "localhost";
            portNumber = 7777;

        } else {
            hostName = args[0];
            portNumber = Integer.parseInt(args[1]);
        }

        try {
            Socket echoSocket = new Socket(hostName, portNumber);

            PrintWriter out
                    = new PrintWriter( echoSocket.getOutputStream() , true);

            BufferedReader in
                    = new BufferedReader(
                    new InputStreamReader( echoSocket.getInputStream() ));

            BufferedReader teclado
                    = new BufferedReader(
                    new InputStreamReader( System.in ) );

            String userInput;
            while ((userInput = teclado.readLine()) != null) {
                if( userInput.trim().equals(".") ) {
                    out.println(userInput);
                    break;
                }   else {
                    out.println( userInput ) ;
                }  if (args.length != 2) {
                    System.out.println("Introduce tu mensajito UwU");
                    System.err.println(
                            "Usage:SEND #<"+in.readLine()+">@<"+nombre+">");
                    hostName = "localhost";
                    portNumber = 7777;

                } else {
                    hostName = args[0];
                    portNumber = Integer.parseInt(args[1]);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }
    }
}
