package Modelo;

import java.security.PrivateKey;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import Controlador.Criptografia;
import Controlador.MetodosEstaticos;

public class BD {
    private String ip;
    private ArrayList<byte[]> bdEncriptada;
    private SecretKey secretKey;
    private PrivateKey privateKey;

    public BD(String ip) {
        this.ip = ip;
        bdEncriptada = new ArrayList<byte[]>();
    }

    public byte[] getArray(int indice) {
        return bdEncriptada.get(indice);
    }

    public void setMensaje(byte[] mensaje) {
        bdEncriptada.add(mensaje);
    }

    public void setPrivateKey(PrivateKey pk) {
        this.privateKey = pk;
    }

    public void desencriptarSecretKey(byte[] secretKeyCifrada) throws Exception {
        secretKey = MetodosEstaticos.byteArrayToSecretKey(Criptografia.descifrar(secretKeyCifrada, privateKey), "AES");
    }

    public String desencriptarMensaje(int index) throws Exception {
        return MetodosEstaticos.byteArrayToString(Criptografia.desencripta(bdEncriptada.get(index), secretKey));
    }
}
