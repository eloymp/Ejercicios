public class HiloCamarero implements Runnable{
    Bar bar;
    String nombreCamarero;

    public HiloCamarero (Bar bar, String nombreCamarero){
        this.bar=bar;
        this.nombreCamarero=nombreCamarero;
    }

    @Override
    public void run(){
        while (bar.getSizeClientes()>0) {
            try {
                bar.atender(nombreCamarero);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
