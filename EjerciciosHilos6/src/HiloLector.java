public class HiloLector implements Runnable{
    private LecturaEscritura le;
    String nomHilo;

    public HiloLector (LecturaEscritura lecturaEscritura, String nomHilo){
        this.le=lecturaEscritura;
        this.nomHilo=nomHilo;
    }

    @Override
    public void run (){
        le.leerVar(nomHilo);
    }
}
