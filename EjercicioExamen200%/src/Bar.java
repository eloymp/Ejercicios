import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {

    public ArrayList<String> listaClientes;
    int numCocacolas = 5;
    Semaphore grifos = new Semaphore(3);
    private ReentrantLock lock = new ReentrantLock();

    public Bar(ArrayList<String> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public int getSizeClientes() {
        return listaClientes.size();
    }

    public String bebidaAleatoria() {
        Random random = new Random();
        int num = random.nextInt(0, 2);

        if (num == 1) {
            return "CocaCola";
        } else {
            return "Cerveza";
        }
    }

    public void repornerCocaCola() throws Exception {
        if (numCocacolas > 0) {
            numCocacolas--;
        } else {
            numCocacolas = 5;
            Thread.sleep(2000);

        }
    }

    public int getNumCocaColas() {
        return numCocacolas;
    }

    public String getClientes() {
        return listaClientes.get(0);
    }

    public void borrarCliente() {
        listaClientes.remove(0);
    }

    // synchronized
    public void atender(String nombreCamarero) throws Exception {
        lock.lock();
        if (getSizeClientes() > 0) {
            String cliente = getClientes();
            String bebida = bebidaAleatoria();

            System.out.println("El " + cliente + " ha pedido una " + bebida);

            if (bebida.equals("CocaCola")) {
                if (getNumCocaColas() == 0) {
                    System.out.println(nombreCamarero + " ve que no quedan CocaColas y va a por mas");
                    repornerCocaCola();
                    System.out.println(nombreCamarero + " ha repuesto " + getNumCocaColas() + " CocaColas");
                }
                System.out.println(nombreCamarero + " esta sirviendo una CocaCola al " + cliente);
                repornerCocaCola();
                System.out.println("Quedan " + getNumCocaColas() + "/5");
                borrarCliente();
            }
            if (bebida.equals("Cerveza")) {
                System.out.println(nombreCamarero + " va a servir una Cerveza al " + cliente);
                grifos.acquire();
                System.out.println(nombreCamarero + " ha servido una Cerveza al " + cliente);
                grifos.release();
                borrarCliente();
            }
            if (lock.isHeldByCurrentThread()) { // se utiliza para verificar si el hilo actual es el que tiene el
                                                // ReentrantLock bloqueado.
                lock.unlock();
            }
            Thread.sleep(1000);

        } else {
            System.out.println("Se han atendido a todos los clientes");
            lock.unlock();
        }
    }
}