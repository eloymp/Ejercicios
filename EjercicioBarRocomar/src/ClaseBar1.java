import java.util.concurrent.Semaphore;


//Este es el codigo que yo habria hecho en el examen quitando el syncronized, el metodo pedir tiene las lineas que borre no se por que
//y el synchronized en el exmaen lo intente con un reentrantLock pero me salia raro y lo quite

public class ClaseBar1 {
    int numCamareros = 0;
    int numeroCocaColas = 5;
    Semaphore grifoCerveza = new Semaphore(3);
    Semaphore camareros = new Semaphore(0);

    public synchronized void hayCamareros(int num, String nombreHilo) { // Aqui he añadido el synchronized
        numCamareros += num; // Esto no lo uso para nada
        camareros.release();
        int permisosDisponibles = camareros.availablePermits();
        System.out.println(permisosDisponibles);
    }

    public void pedir(int num, String nombreHilo) {
        int permisosDisponibles = camareros.availablePermits();
        System.out.println(permisosDisponibles);

        int producto = num;

        switch (producto) {
            case 0:
                // System.out.println("El cliente " + nombreHilo + " ha pedido una CocaCola
                // fresquita.");
                if (camareros.tryAcquire()) {
                    System.out.println("El cliente " + nombreHilo + " va a ser atendido");
                    pedirCocaCola(nombreHilo);
                    camareros.release();
                } else {
                    System.out.println("No hay ningun camarero disponible");
                }
                break;

            case 1:
                // System.out.println("El cliente " + nombreHilo + " ha pedido una Cervezuqui
                // fresquita.");
                if (camareros.tryAcquire()) {
                    System.out.println("El cliente " + nombreHilo + " va a ser atendido");

                    if (grifoCerveza.tryAcquire()) {
                        System.out.println("Al cliente " + nombreHilo + " se le ha servido una cerveza");
                        grifoCerveza.release();
                    } else {
                        System.out.println("No hay ningun grifo disponible");
                    }
                    camareros.release();
                } else {
                    System.out.println("No hay ningun camarero disponible");
                }
                break;
        }
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