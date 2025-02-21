import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        int puertoServidor = 5555;
        boolean conectado = true;
        byte[] buffer = new byte[1024];

        DatagramSocket socketUDP = new DatagramSocket(puertoServidor);
        System.out.println("Puerto UDP escuchando por el puerto 5555");

        while (conectado) {
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(paqueteRecibido);

            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new ByteArrayInputStream(paqueteRecibido.getData()));
            Quiniela quiniela = (Quiniela) objectInputStream.readObject();

            String lista[] = quiniela.getLista();

            for (int i = 0; i < 15; i++) {
                if (!lista[i].equals("1") || !lista[i].equals("2") || !lista[i].equals("X")) {
                    break;
                }
            }

            quiniela.setHash(GenerarHash.generarHashMD5(quiniela.getListaString()));

            // Serializamos el objeto Quiniela para enviarlo otra vez
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream salidaCliente = new ObjectOutputStream(byteArrayOutputStream);
            salidaCliente.writeObject(quiniela);
            byte[] datosRespuesta = byteArrayOutputStream.toByteArray();

            // Enviamos el objeto serializado
            DatagramPacket paqueteRespuesta = new DatagramPacket(
                    datosRespuesta, datosRespuesta.length,
                    paqueteRecibido.getAddress(), paqueteRecibido.getPort());
            socketUDP.send(paqueteRespuesta);
        }

    }
}
