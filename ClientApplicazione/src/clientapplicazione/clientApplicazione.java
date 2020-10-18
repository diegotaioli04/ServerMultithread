/*
Taioli Diego 5B IA
 */
package clientapplicazione;

/**
 *
 * @author informatica
 */
public class clientApplicazione {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String nomeServer = "localhost";
        int portaServer = 6789;
        
        Cliente clinica = new Cliente(nomeServer, portaServer);
        Thread p1 = new Thread(clinica);
        Thread p2 = new Thread(clinica);
        p1.start();
        p2.start();
    }
    
}
