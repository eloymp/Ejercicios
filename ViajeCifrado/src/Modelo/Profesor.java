package Modelo;

import javax.crypto.SecretKey;

import Controlador.Criptografia;

public class Profesor {
    private int id;
    private String nombre;
    private SecretKey privKey1;
    private Billete billete;

    public Profesor(int id, String nombre, Billete billete){
        this.id=id;
        this.nombre=nombre;
        this.billete=billete;
    }

    public int generarClaveAES() throws Exception{
        try {
            this.privKey1=Criptografia.generarClaveAES();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String toString(){
        return "Id: "+id+" "+" Nombre: "+nombre;
    }

    public SecretKey getKey (){
        return privKey1;
    }

    public void setKey (SecretKey sk){
        this.privKey1=sk;
    }

    public String getBillete (){
        return billete.toString();
    }



}
