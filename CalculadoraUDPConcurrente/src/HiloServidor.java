import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HiloServidor extends Thread {

    private Socket socketCliente;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Operacion operacion;

    public HiloServidor(Socket socketCliente) throws Exception {
        this.socketCliente = socketCliente;
        entrada = new ObjectInputStream(socketCliente.getInputStream());
        salida=new ObjectOutputStream(socketCliente.getOutputStream());
    }

    @Override
    public void run() {
        try {
            operacion=(Operacion) entrada.readObject();
            double resultado=calcularOperacion(operacion);
            operacion.setResultado(resultado);

            salida.writeObject(operacion);

            entrada.close();
            salida.close();
            socketCliente.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calcularOperacion(Operacion operacion) {
        switch (operacion.getOperacion()) {
            case 1: return operacion.getNum1() + operacion.getNum2();
            case 2: return operacion.getNum1() - operacion.getNum2();
            case 3: return operacion.getNum1() * operacion.getNum2();
            case 4: 
                if (operacion.getNum2() != 0) {
                    return (double) operacion.getNum1() / operacion.getNum2();
                } else {
                    System.out.println("Error: División por cero.");
                    return Double.NaN;
                }
            default:
                System.out.println("Operación inválida.");
                return Double.NaN;
        }
    }

}
