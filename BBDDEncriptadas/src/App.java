import Controlador.Controlador;
import Modelo.BD;
import Modelo.Cliente;
import Vista.Vista;

public class App {
    public static void main(String[] args) throws Exception {
        Vista vista = new Vista();
        Cliente cliente1= new Cliente(1, "Pedrito");
        BD bd = new BD("192.168.0.1");
        Controlador controlador= new Controlador(vista, cliente1, bd);

        controlador.Conexion();
    }
}
