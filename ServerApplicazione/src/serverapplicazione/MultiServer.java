/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplicazione;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author informatica
 */
public class MultiServer {
    public void avvio(){
        try{
            ServerSocket server = new ServerSocket(6789);
            for(;;)
            {
                System.out.println("1 Server in attesa... ");
                Socket socket = server.accept();
                System.out.println("3 Server socket " +socket);
                ServerThread task = new ServerThread(socket);
                Thread processo1 = new Thread(task);
                processo1.start();
                Thread processo2 = new Thread(task);
                processo2.start();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(" Errore durante l'istanza del server ");
            System.exit(1);
        }
    }
}
