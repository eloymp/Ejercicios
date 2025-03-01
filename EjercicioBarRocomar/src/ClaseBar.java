import java.util.concurrent.Semaphore;

//Este codigo funciona perfectamente

public class ClaseBar {
    int numCamareros = 0;
    int numeroCocaColas = 5;
    Semaphore grifoCerveza = new Semaphore(3);
    Semaphore camareros = new Semaphore(0);

    public synchronized void hayCamareros(int num, String nombreHilo) { // Aqui he añadido el synchronized
        numCamareros += num; // Esto no lo uso para nada
        camareros.release();
    }

    public void pedir(int num, String nombreHilo) throws Exception {
        int producto = num;

        while (!camareros.tryAcquire()) { // Comprobamos si hay camareros disponibles
            System.out.println(nombreHilo + " no puede ser atendido. Esperando...");
            Thread.sleep(1000); // Espera antes de volver a intentar
        }

        // Si hay un camarero disponible, atender al cliente
        switch (producto) {
            case 0:
                pedirCocaCola(nombreHilo);
                break;
            case 1:
                if (grifoCerveza.tryAcquire()) {
                    System.out.println("Al cliente " + nombreHilo + " se le ha servido una cerveza");
                    grifoCerveza.release();
                } else {
                    System.out.println("No hay ningun grifo disponible");
                }
                break;
        }

        camareros.release(); // Liberamos el camarero
    }

    public synchronized void pedirCocaCola(String nombreHilo) {
        try {
            if (numeroCocaColas > 0) {
                System.out.println("Un camarero le ha servido una CocaCola al " + nombreHilo);
                numeroCocaColas--;
                System.out.println("CocaColas servidas: " + numeroCocaColas + "/5 ");

            } else {
                System.out.println("Nos hemos quedado sin CocaColas, hay que ir a por mas. ");
                numeroCocaColas = 5;
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Error ");
        }
    }
}
