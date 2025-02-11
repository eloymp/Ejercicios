public class App {
    public static void main(String[] args) throws Exception {

        Restaurante restaurante = new Restaurante();

        for (int i = 0; i < 5; i++) {
            String nombreHilo = "chino" + (i + 1); // Crear un nombre dinámico para el hilo

            Thread hilo = new Thread(new HiloFilosofo(restaurante, nombreHilo));

            hilo.setName(nombreHilo);

            hilo.start();
        }
    }
}
