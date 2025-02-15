
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int puertoServidor = 5555;
        int num1;
        int num2;
        int operador;
        Operacion operacion;
        Scanner sc = new Scanner(System.in);

        Socket socketCliente = new Socket(host, puertoServidor);

        System.out.println("Escribe el primer numero");
        num1 = sc.nextInt();
        System.out.println("Escribe el segundo numero");
        num2 = sc.nextInt();
        System.out.println("Escribe la operacion que quieres hacer (1:suma, 2:resta, 3:multiplicacion, 4:division)");
        operador = sc.nextInt();

        operacion = new Operacion(num1, num2, operador);

        // Se envia el objeto
        ObjectOutputStream operacionSalida = new ObjectOutputStream(socketCliente.getOutputStream());
        operacionSalida.writeObject(operacion);

        // Se recibe un objeto
        ObjectInputStream operacionEntrada = new ObjectInputStream(socketCliente.getInputStream());
        operacion = (Operacion) operacionEntrada.readObject();

        System.out.println("Resultado: " + operacion.getResultado());

        operacionSalida.close();
        operacionEntrada.close();
        socketCliente.close();
        sc.close();
    }
}