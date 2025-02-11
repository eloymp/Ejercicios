
public class HiloFilosofo implements Runnable {
    Restaurante restaurante;
    String nombre;

    public HiloFilosofo(Restaurante restaurante, String nombre) {
        this.restaurante = restaurante;
        this.nombre=nombre;
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++)
            restaurante.comer(this.nombre);
    }
}