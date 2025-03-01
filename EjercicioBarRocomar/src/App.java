public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Bienvenido al bar Rocomar");
        ClaseBar barRocomar = new ClaseBar();

        for (int i = 0; i < 5; i++) {
            String nombreHilo = "Camarero" + (i + 1); // Crear un nombre dinámico para el hilo

            Thread hilo = new Thread(new HiloCamarero(barRocomar, nombreHilo));

            hilo.setName(nombreHilo);

            hilo.start();
        }

        for (int i = 0; i < 20; i++) {
            String nombreHilo = "Cliente" + (i + 1); // Crear un nombre dinámico para el hilo

            Thread hilo = new Thread(new HiloCliente(barRocomar, nombreHilo));

            hilo.setName(nombreHilo);
            hilo.start();
        }
    }
}
