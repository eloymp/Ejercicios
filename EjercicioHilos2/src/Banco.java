import java.util.ArrayList;

public class Banco {

    // private static double saldo=0;
    static ArrayList<Double> lista = new ArrayList<>();
    private int idBanco = 0;

    public Banco(double inicial, int id) {
        lista.add(inicial);
        this.idBanco = id;
    }

    public static synchronized void depositar(double numero, int id) {
        double saldo = lista.get(id - 1);
        lista.set(id - 1, saldo + numero);
    }

    public static synchronized void retirar(double numero, int id) {
        int idm = id - 1;
        if (numero > lista.get(idm)) {
            System.out.println("Estas retirando mas dinero del que tienes fucking mileurista. ");
        } else {
            double saldo = lista.get(idm);
            lista.set((idm), saldo - numero);
        }
    }

    public double getSaldoPorPosicion(int index) {
        if (index - 1 >= 0 && index - 1 < lista.size()) {
            return lista.get(index - 1);
        } else {
            System.out.println("Índice fuera de rango.");
            return 0;
        }
    }

    public int getId() {
        return idBanco;
    }
}