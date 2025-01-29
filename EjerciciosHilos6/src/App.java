public class App {
    public static void main(String[] args) throws Exception {
        LecturaEscritura lecturaEscritura = new LecturaEscritura();

        for (int i = 0; i < 4; i++) {
            String nombreHiloEscritor = "hiloEscritor" + (i + 1); // Crear un nombre dinámico para el hilo
            String nombreHiloLector = "hiloLector" + (i + 1);

            Thread hilo = new Thread(new HiloEscritor(lecturaEscritura, "Caracoles!! "+i ,nombreHiloEscritor));
            Thread hilo2 = new Thread(new HiloLector(lecturaEscritura, nombreHiloLector));

            hilo.setName(nombreHiloEscritor);
            hilo2.setName(nombreHiloLector);

            hilo.start();
            hilo2.start();

            try {
                hilo.join();
                hilo2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           
        }
    }
}
