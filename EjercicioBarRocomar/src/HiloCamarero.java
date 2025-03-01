public class HiloCamarero implements Runnable {
    String nombreHilo;
    ClaseBar barRocomar;

    public HiloCamarero(ClaseBar barRocobar, String nombreHilo) {
        this.barRocomar = barRocobar;
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        barRocomar.hayCamareros(1, nombreHilo);
    }
}
