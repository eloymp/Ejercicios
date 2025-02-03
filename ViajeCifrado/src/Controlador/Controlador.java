package Controlador;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.SecretKey;

import Modelo.Profesor;
import Vista.Vista;

public class Controlador {
    // Variables para que funcione
    private Vista vista;
    private Profesor profesor1;
    private Profesor profesor2;

    // VariableS RSA
    private KeyPair parejaClaves;
    private PrivateKey privadaRSA;
    private PublicKey publicaRSA;

    private byte[] arrayCifrado;
    private byte[] arrayDescifrado;

    // Variables hash
    private String hashBillete1Salida;
    private String hashBillete2Salida;
    private String hashBillete1Llegada;
    private String hashBillete2Llegada;
    private byte[] mensajeEncriptado;
    private byte[] mensajeDesencriptado;

    // Variables para hablar
    private String mensaje = "";
    private Scanner sc;
    private String eleccion;

    public Controlador(Vista vista, Profesor profesor1, Profesor profesor2) {
        this.vista = vista;
        this.profesor1 = profesor1;
        this.profesor2 = profesor2;
    }

    public void viajar() throws Exception {
        // Generar los hash de los billetes al salir
        hashBillete1Salida = Criptografia.generarHashMD5(profesor1.getBillete());
        hashBillete2Salida = Criptografia.generarHashMD5(profesor2.getBillete());
        vista.mostrarMensaje("Se han generado los hashes de los billetes");

        // Generar la clave privada para el cifrado simetrico.

        if (profesor1.generarClaveAES() == 1) {
            vista.mostrarMensaje("Clave AES generada. ");
        } else {
            vista.mostrarMensaje("No se ha podido generar la clave AES");
        }

        // Generar la pareja de claves para el cifrado asimetrico
        parejaClaves = Criptografia.generadorClaves();
        vista.mostrarMensaje("Pareja de claves RSA creadas");

        // Guardar las claves en variables diferentes
        privadaRSA = parejaClaves.getPrivate();
        publicaRSA = parejaClaves.getPublic();

        // Cifrar con la clave publica RSA la clave privada AES
        arrayCifrado = getMensajeCifradoRSA(profesor1.getKey(), publicaRSA);
        vista.mostrarMensaje("ClavePrivadaAES cifrada.");

        // Descrifrar con la clave privada RSA la clave privada AES
        arrayDescifrado = Criptografia.descifrar(arrayCifrado, privadaRSA);
        vista.mostrarMensaje("ClavePrivadaAES descifrada.");

        // Establecer la secretKey del profesor2 a la secretKey generada por el
        // profesor1
        profesor2.setKey(MetodosEstaticos.byteArrayToSecretKey(arrayDescifrado, "AES"));

        // Comprobar que son iguales
        if (Arrays.equals(profesor1.getKey().getEncoded(), profesor2.getKey().getEncoded())) {
            vista.mostrarMensaje("Las claves AES son iguales.");
        } else {
            vista.mostrarMensaje("Las claves AES NO son iguales.");
        }

        vista.mostrarMensaje(" ");

        // Bucle para poder enviar mensajes
        sc = new Scanner(System.in);

        while (true) {
            // Preguntar qué profesor será el emisor
            vista.mostrarMensaje(
                    "¿Qué profesor quieres que sea el emisor? (1 para Profesor1, 2 para Profesor2 o Salir)");
            eleccion = sc.nextLine();

            if (eleccion.equals("Salir")) {
                break;
            }

            // Verificar la elección y enviar el mensaje
            if (eleccion.equals("1") || eleccion.equals("2")) {
                // Selección del emisor
                vista.mostrarMensaje("Has elegido al Profesor " + eleccion + " como emisor.");

                vista.mostrarMensaje("Escribe el mensaje que quieres enviar:");
                mensaje = sc.nextLine();

                // Cifrado y descifrado según el profesor elegido
                if (eleccion.equals("1")) {
                    // Profesor 1 como emisor
                    mensajeEncriptado = Criptografia.encripta(MetodosEstaticos.stringToByteArray(mensaje),
                            profesor1.getKey());
                    vista.mostrarMensaje("Se ha cifrado el mensaje con la clave del Profesor 1.");
                    mensajeDesencriptado = Criptografia.desencripta(mensajeEncriptado, profesor2.getKey());
                    vista.mostrarMensaje("Se ha descifrado el mensaje con la clave del Profesor 2.");
                } else {
                    // Profesor 2 como emisor
                    mensajeEncriptado = Criptografia.encripta(MetodosEstaticos.stringToByteArray(mensaje),
                            profesor2.getKey());
                    vista.mostrarMensaje("Se ha cifrado el mensaje con la clave del Profesor 2.");
                    mensajeDesencriptado = Criptografia.desencripta(mensajeEncriptado, profesor1.getKey());
                    vista.mostrarMensaje("Se ha descifrado el mensaje con la clave del Profesor 1.");
                }

                // Mostrar el mensaje descifrado
                vista.mostrarMensaje(
                        "El mensaje descrifrado es: " + MetodosEstaticos.byteArrayToString(mensajeDesencriptado));
                vista.mostrarMensaje(" ");

            } else {
                vista.mostrarMensaje("Ese profesor no está disponible.");
            }
        }

        vista.mostrarMensaje(" ");

        // Generar los hash de los billetes al llegar
        hashBillete1Llegada = Criptografia.generarHashMD5(profesor1.getBillete());
        hashBillete2Llegada = Criptografia.generarHashMD5(profesor2.getBillete());

        if (hashBillete1Salida.equals(hashBillete1Llegada)) {
            vista.mostrarMensaje("El billete del primer profesor no ha cambiado. ");
        }
        if (hashBillete2Salida.equals(hashBillete2Llegada)) {
            vista.mostrarMensaje("El billete del segundo profesor no ha cambiado. ");
        }

    }

    public byte[] getMensajeCifradoRSA(SecretKey clavePrivadaAES, PublicKey publicaRSA) throws Exception {
        // return
        // Criptografia.cifrar(MetodosEstaticos.stringToByteArray(clavePrivadaAES.toString()),
        // publicaRSA);
        return Criptografia.cifrar(clavePrivadaAES.getEncoded(), publicaRSA);
        // getEncoded devuelve la clave en su forma binaria real a diferencia del
        // toString marronero

    }

}
