import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int puertoServidorUDP = 5555;

        Quiniela quiniela = new Quiniela();
        quiniela.rellenarQuiniela();

        // UDP
        // CONECTAMOS CON UDP
        DatagramSocket socketUDP = new DatagramSocket();

        // Serializamos la quiniela para enviarla por bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream salidaUDP = new ObjectOutputStream(baos);
        salidaUDP.writeObject(quiniela);
        byte[] quinielaBytes = baos.toByteArray();

        // Enviamos el objeto serializado al servidorUDP
        DatagramPacket paqueteEnvio = new DatagramPacket(quinielaBytes, quinielaBytes.length,
                InetAddress.getByName(host), puertoServidorUDP);
        socketUDP.send(paqueteEnvio);
        System.out.println("Enviada Quiniela al Servidor UDP...");

        // Recibimos la respuesta del Servidor UDP
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
        socketUDP.receive(paqueteRecibido);

        // Deserializamos la respuesta porque llega como un array de bytes y necesitamos
        // el objeto
        ObjectInputStream entradaUDP = new ObjectInputStream(new ByteArrayInputStream(paqueteRecibido.getData()));
        quiniela = (Quiniela) entradaUDP.readObject();

        System.out.println("Quiniela recibida con hash: " + quiniela.getHash());
        System.out.println(quiniela.toString());

        // TCP
        int puertoServidorTCP = 6000;

        Socket socketClienteTCP = new Socket(host, puertoServidorTCP);

        // Se envia el objeto
        ObjectOutputStream operacionSalidaTCP = new ObjectOutputStream(socketClienteTCP.getOutputStream());
        operacionSalidaTCP.writeObject(quiniela);

        // Se recibe el objeto
        ObjectInputStream operacionEntradaTCP = new ObjectInputStream(socketClienteTCP.getInputStream());

        System.out.println(operacionEntradaTCP.readObject());

        System.out.println(operacionEntradaTCP.readObject());

        socketClienteTCP.close();
        operacionEntradaTCP.close();
        operacionSalidaTCP.close();

    }

    // Método para serializar el objeto
    public static byte[] serialize(Object obj) throws Exception {
        java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
        return byteArrayOutputStream.toByteArray();
    }

    // Método para deserializar el objeto
    public static Object deserialize(byte[] byteArray) throws Exception {
        java.io.ByteArrayInputStream byteArrayInputStream = new java.io.ByteArrayInputStream(byteArray);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        }
    }
}
