import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<String> listaClientes = new ArrayList<String>();

        // Clientes
        for (int i = 0; i < 20; i++) {
            listaClientes.add("Cliente " + (i + 1));
        }

        Bar bar = new Bar(listaClientes);

        // Camareros

        for (int i = 0; i < 5; i++) {
            String nombreHilo = "Camarero" + (i + 1);

            Thread hilo = new Thread(new HiloCamarero(bar, nombreHilo));

            hilo.setName(nombreHilo);

            hilo.start();
        }

    }
}
