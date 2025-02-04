package Modelo;

import javax.crypto.SecretKey;

import Controlador.Criptografia;

public class Cliente {
    private int id;
    private String nombre;
    private String mensaje;
    private SecretKey pk1;

    public Cliente(int id, String nombre) throws Exception{
        this.id=id;
        this.nombre=nombre;
        this.pk1=Criptografia.generarClaveAES();
    }

    public String getMensaje(){
        return mensaje;
    }

    public void setMensaje(String mensaje){
        this.mensaje=mensaje; 
    }

    public SecretKey getSecretKey(){
        return pk1;
    }
}
