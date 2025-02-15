import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        int puertoServidor = 5555;
        boolean conectado = true;

        ServerSocket socketServidor = new ServerSocket(puertoServidor);

        while (conectado) {
            Socket socketCliente = socketServidor.accept();

            HiloServidor conexionCliente = new HiloServidor(socketCliente);

            conexionCliente.start();
        }

        socketServidor.close();
    }
}
