import java.util.Random;

public class HiloCliente implements Runnable {
    String nombreHilo;
    ClaseBar barRocomar;
    Random random;

    public HiloCliente(ClaseBar barRocobar, String nombreHilo){
        this.barRocomar= barRocobar;
        this.nombreHilo=nombreHilo;
        random = new Random();  
    }

    @Override
    public void run () {
        try {
            barRocomar.pedir(random.nextInt(2), nombreHilo);
        } catch (Exception e) {
        }

    }
}
