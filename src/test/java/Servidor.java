import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Servidor {
    public static final int PORT = 7777;
    private static final Logger LOG = Logger.getLogger(Servidor.class.getName());

    public static void main(String[] args) throws IOException {

        FileHandler fileXml = new FileHandler("Logging.xml");
        LOG.addHandler(fileXml);

        int portNumber = 0;
        if (args.length == 0) {
            System.err.println("Usage: java EchoServer <port number>");
            //System.exit(1);
            portNumber = PORT;
        } else {
            portNumber = Integer.parseInt(args[0]);
        }


        try {
            ServerSocket serverSocket
                    = new ServerSocket(portNumber);

            System.out.println("Esperando a cliente...");
            while (true) {

                Socket clientSocket = serverSocket.accept();

                PrintWriter out
                        = new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.trim().equals(".")) {
                        // out.println(inputLine);
                        break;
                    } else {
                        out.println(inputLine);
                        if (args.length == 0) {
                            System.err.println("Usage: LIST");
                            //System.exit(1);
                            portNumber = PORT;
                        } else {
                            portNumber = Integer.parseInt(args[0]);
                        }
                    }
                }
                clientSocket.close();

            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
