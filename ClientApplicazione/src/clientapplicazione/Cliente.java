/*
Taioli Diego 5B IA
 */
package clientapplicazione;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author informatica
 */
public class Cliente implements Runnable{
    
    String nomeServer;
    int portaServer;
    Socket miosocket;
    BufferedReader tastiera;
    String partenze;
    String arrivi;
    DataOutputStream output;
    BufferedReader input;
    
    public Cliente(String hostname, int portaddress){
        this.nomeServer = hostname;
        this.portaServer = portaddress;
    }
    
    public void comunica (){
        for(;;)  
            try{
                System.out.println("4 ... utente inserisci il tuo nome ");
                partenze = tastiera.readLine();
                //la spedisco al server
                output.writeBytes(partenze+'\n');
                //leggo la risposta dal server
                arrivi = input.readLine();
                System.out.println("7 ... risposta dal server "+'\n'+arrivi);
                
                
                if(partenze.equals("FINE")){
                    System.out.println("8 CLIENT: termina elaborazione e chiude connessione");
                    miosocket.close();
                    break;
                }
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println(" Errore durante la comunicazione col server! ");
                System.exit(1);
            }
    }
    
    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try{
            //input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //mio socket
            miosocket = new Socket(nomeServer, portaServer);
            //associo oggetti al socket
            output = new DataOutputStream(miosocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader (miosocket.getInputStream()));
            
            
        }
        catch(UnknownHostException e){
            System.err.println(" Host sconosciuto "); }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(" Errore durante la connessione! ");
            System.exit(1);
        }
        return miosocket;
    }

    @Override
    public void run() {
        connetti();
        comunica();
    }
}
