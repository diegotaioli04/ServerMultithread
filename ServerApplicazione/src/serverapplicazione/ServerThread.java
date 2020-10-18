/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplicazione;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author informatica
 */
public class ServerThread implements Runnable{
    ServerSocket server = null;
    Socket client = null;
    String Ricevuta = null;
    String Risposta = null;
    BufferedReader inputClient;
    DataOutputStream outputClient;
    
    public ServerThread(Socket socket){
        this.client = socket;
    }
    
    @Override
    public synchronized void run(){
        try{
            comunica();
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    private void comunica() throws Exception {
        inputClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputClient = new DataOutputStream(client.getOutputStream());
        for(;;){
            Ricevuta = inputClient.readLine();
            if(Ricevuta == null || Ricevuta.equals("FINE")){
                outputClient.writeBytes(Ricevuta+" (=> server in chiusura... " +'\n');
                System.out.println("Echo sul server in chiusura :" + Ricevuta);
                break;
            }
            else{
                outputClient.writeBytes(Ricevuta + " (ricevuta e trasmessa) " + '\n');
                System.out.println("6 Echo sul server: " + Ricevuta);
            }     
        }
        outputClient.close();
        inputClient.close();
        System.out.println("9 Chiusura socket: " + client);
    }
}
