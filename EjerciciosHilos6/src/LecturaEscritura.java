import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LecturaEscritura {
    private String variable;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Semaphore semaforo1 = new Semaphore(0);

    public void leerVar(String hilo) {

        try {
            System.out.println(hilo + " esta esperando a leer la variable");
            semaforo1.acquire();
            lock.readLock().lock();
            System.out.println(hilo + " esta leyendo la variable " + this.variable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
            System.out.println(hilo + " ha dejado de leer la variable");
            semaforo1.release();
        }
    }

    public void escribirVar(String contenido, String hilo) {
        System.out.println(hilo + " esta esperando a escribir la variable");
        lock.writeLock().lock();
        try {
            System.out.println(hilo + " esta escribiendo la variable.");
            this.variable = contenido;
            System.out.println(hilo + " ha escrito la variable con " + this.variable);

        } finally {
            lock.writeLock().unlock();
            System.out.println(hilo + " ha dejado de escribir la variable");
            semaforo1.release();
        }
    }

}