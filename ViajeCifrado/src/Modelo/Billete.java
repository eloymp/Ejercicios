package Modelo;

public class Billete {
    private int id;
    private String origen;
    private String destino;

    public Billete(int id,  String origen, String destino){
        this.id=id;
        this.origen=origen;
        this.destino=destino;
    }

    public String toString (){
        return "Id: "+id+" Origen: "+origen+" Destino: "+destino;
    }
}
