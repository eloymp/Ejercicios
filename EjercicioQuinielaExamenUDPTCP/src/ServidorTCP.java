import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) throws Exception {

        int puertoServidor = 6000;
        boolean conectado = true;
        String quinielaCorrecta = "111111111111111";
        String hashQuinielaCorrecta;

        ServerSocket socketServidor = new ServerSocket(puertoServidor);
        System.out.println("Escuchando en el puerto: " + socketServidor.getLocalPort());

        while (conectado) {
            Socket socketCliente = socketServidor.accept();

            ObjectInputStream entrada = new ObjectInputStream(socketCliente.getInputStream());
            ObjectOutputStream salida = new ObjectOutputStream(socketCliente.getOutputStream());

            salida.writeObject("Te has conectado al servidor TCP");

            Quiniela quiniela = (Quiniela) entrada.readObject();

            hashQuinielaCorrecta = GenerarHash.generarHashMD5(quinielaCorrecta);

            if (hashQuinielaCorrecta.equals(quiniela.getHash())) {
                salida.writeObject("La quiniela que has mandado es correcta");
            } else {
                salida.writeObject("La quiniela que has mandado es incorrecta");
            }

            entrada.close();
            salida.close();
            socketCliente.close();

        }
        socketServidor.close();

    }
}