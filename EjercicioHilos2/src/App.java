//2. Sincronización Estática:

//Crea una clase Banco con métodos estáticos sincronizados depositar y retirar.
//Simula varias cuentas bancarias y transacciones concurrentes.

public class App {
    public static void main(String[] args) throws Exception {

        Banco banco1 = new Banco(1000, 1); // 900
        Banco banco2 = new Banco(2000, 2); // 1700

        Thread hilo1 = new Thread(new HiloBanco(100, 200, banco1));
        Thread hilo2 = new Thread(new HiloBanco(0, 300, banco2));

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Banco1= " + banco1.getSaldoPorPosicion(1));
        System.out.println("Banco2= " + banco1.getSaldoPorPosicion(2));

    }
}
