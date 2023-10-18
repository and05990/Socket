import java.io.*;
import java.net.*;

public class Client2 {
    String nomeServer = "10.4.54.7";
    int portaServer =9012;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;


    DataOutputStream outVersoServer;
    BufferedReader inDalServer;

    public Socket connetti () {
        System.out.println("Client: sono in esecuzione");
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer,portaServer);
            outVersoServer =new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (UnknownHostException e){
            System.out.println("Host sconosciuto");
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return mioSocket;
    }

    public void comunica(){
        try {
            System.out.println("...inserisci la stringa da trasmettere al server"+'\n');
            stringaUtente=tastiera.readLine();

            System.out.println("... invio della stringa al server ...");
            outVersoServer.writeBytes(stringaUtente + '\n');
            stringaRicevutaDalServer = inDalServer.readLine();

            System.out.println("...risposta dal server... " + '\n' + stringaRicevutaDalServer);
            System.out.println("CLIENT: termine elaborazione");
            mioSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }
    }

    public static void main (String args[]){
        Client2 cliente = new Client2();
        cliente.connetti();
        cliente.comunica();
    }

}
