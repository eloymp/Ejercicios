package Controlador;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Criptografia {
    
    //#region HASH
    public static String generarHashMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(input.getBytes());

        // Convertir los bytes a formato hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String generarHashSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(input.getBytes());

        // Convertir los bytes a formato hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    //#endregion

    //#region CIFRADO SIMETRCIO

    //Método que genera una clave AES de 256 bits
    public static SecretKey generarClaveAES() throws Exception {
        // Crea un generador de claves para el algoritmo AES
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // Inicializa el generador de claves con un tamaño de 256 bits (AES-256)
        keyGen.init(256); // Tamaño de la clave: 256 bits
        // Genera y devuelve la clave secreta (SecretKey) que se usará en el proceso de
        // encriptación y desencriptación
        return keyGen.generateKey();
    }

    // Método que encripta un conjunto de datos (byte array) utilizando una clave
    // AES
    public static byte[] encripta(byte[] data, SecretKey key) throws Exception {
        // Crea una instancia de un objeto Cipher para el algoritmo AES en modo ECB con
        // relleno PKCS5
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Inicializa el objeto Cipher para el modo de encriptación (Encrypt Mode)
        // usando la clave proporcionada
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encripta los datos pasados como parámetro (data) y devuelve los datos
        // encriptados como un byte array
        return cipher.doFinal(data);
    }

    // Método que desencripta un conjunto de datos encriptados (byte array)
    // utilizando una clave AES
    public static byte[] desencripta(byte[] encryptedData, SecretKey key) throws Exception {
        // Crea una instancia de un objeto Cipher para el algoritmo AES en modo ECB con
        // relleno PKCS5
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Inicializa el objeto Cipher para el modo de desencriptación (Decrypt Mode)
        // usando la clave proporcionada
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Desencripta los datos encriptados pasados como parámetro (encryptedData) y
        // devuelve los datos desencriptados como un byte array
        return cipher.doFinal(encryptedData);
    }
    //#endregion
   
   //#region CIFRADO ASIMETRICO
    public static KeyPair generadorClaves() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    // Cargar una clave publica desde bytes
    public static PublicKey cargarClavePublica(byte[] keyBytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(("RSA"));
        return keyFactory.generatePublic(spec);
    }

    public static PrivateKey cargarClavePrivada(byte[] keyBytes) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    public static byte[] cifrar(byte[] datos, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(datos);
    }

    public static byte[] descifrar(byte[] datos, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(datos);
    }
    //#endregion
}