import java.util.concurrent.Semaphore;

public class Restaurante {

    Semaphore palillos = new Semaphore(5);

    public void comer(String nombre) {
        try {

            if (cogerPalillos(nombre)) {
                System.out.println("El " + nombre + " esta comiendo.");
                Thread.sleep(3000);
                dejarPalillos(nombre);
            } else {
                System.out.println("No hay palillos suficientes para el " + nombre);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean cogerPalillos(String nombre) {

        if (palillos.tryAcquire(2)) {
            System.out.println("El " + nombre + " ha cogido dos palillos");
            return true;
        } else {
            return false;
        }
    }

    public void dejarPalillos(String nombre) {

        palillos.release(2);
        System.out.println("El " + nombre + " ha dejado los palillos.");

    }

}
