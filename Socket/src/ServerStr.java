import java.io.*;
import java.net.*;
import java.util.*;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi(){
        try {
            System.out.println( "Il server è in esecuzione....");
            server = new ServerSocket(6789);
            client = server.accept();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream((client.getOutputStream()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
       }
    public void comunica() {
        try {
            System.out.println("Benvenuto client, scrivi una frase e la trasformi in maiuscolo. Attendo ...");
            stringaRicevuta = inDalClient.readLine();
            System.out.println(" ricevuta la stringa " + stringaRicevuta);
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println(" Invio la stringa al cliente ");
            outVersoClient.writeBytes(stringaModificata + '\n');
            System.out.println("Server: fine elaborazione  - bye");
            client.close();
        } catch (Exception e) {
            System.out.println("Errori nella procedura comunica");
        }
    }
     public static void main (String args[]) {
        ServerStr servente = new ServerStr();
        servente.attendi();
        servente.comunica();

    }
}



