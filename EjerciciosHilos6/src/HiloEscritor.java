public class HiloEscritor implements Runnable{
    private LecturaEscritura le;
    String contenido;
    String nomHilo;

    public HiloEscritor (LecturaEscritura lecturaEscritura,String contenido, String nomHilo){
        this.le=lecturaEscritura;
        this.contenido=contenido;
        this.nomHilo=nomHilo;
    }

    @Override
    public void run (){
        le.escribirVar(contenido, nomHilo);
    }
}
