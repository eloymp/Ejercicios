import Controlador.Controlador;
import Modelo.Billete;
import Modelo.Profesor;
import Vista.Vista;

public class App {
    public static void main(String[] args) throws Exception {
        Vista vista = new Vista();
        Billete billete1 = new Billete(1, "Cuenca", "San sebastian");
        Billete billete2 = new Billete(2, "Cuenca", "San sebastian");

        Profesor profesor1 = new Profesor(1, "Pedro", billete1);
        Profesor profesor2 = new Profesor(2, "Carlos", billete2);
        Controlador controlador = new Controlador(vista, profesor1, profesor2);

        controlador.viajar();
        

    }
}
