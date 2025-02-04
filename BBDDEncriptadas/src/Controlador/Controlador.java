package Controlador;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.SecretKey;

import Modelo.BD;
import Modelo.Cliente;
import Vista.Vista;

public class Controlador {
    Vista vista;
    Cliente cliente1;
    BD bd;

    // Variables RSA
    private KeyPair parejaClaves;
    private PrivateKey clavePrivada;
    private PublicKey clavePublica;

    private byte[] secretKeyCifrada;

    // Mensajes
    private Scanner sc;
    private String mensaje = "";
    private byte[] mensajeCifradoAES;
    private int index;

    // Hash
    private ArrayList<String> hashes;
    private String hash2;

    public Controlador(Vista vista, Cliente cliente1, BD bd) {
        this.vista = vista;
        this.cliente1 = cliente1;
        this.bd = bd;
    }

    public void Conexion() throws Exception {
        // Generar pareja de claves
        parejaClaves = Criptografia.generadorClaves();
        clavePrivada = parejaClaves.getPrivate();
        clavePublica = parejaClaves.getPublic();

        bd.setPrivateKey(clavePrivada);

        // Cifrar claveAES
        secretKeyCifrada = getMensajeCifradoRSA(cliente1.getSecretKey(), clavePublica);

        bd.desencriptarSecretKey(secretKeyCifrada);

        sc = new Scanner(System.in);

        hashes = new ArrayList<>();
        while (true) {
            vista.mostrarMensaje("Escribe que opcion quieres elegir.");
            vista.mostrarMensaje("1. Enviar a la BD un mensaje.");
            vista.mostrarMensaje("2. Recuperar mensaje de la BD.");
            vista.mostrarMensaje("3. SALIR");
            vista.mostrarMensaje(" ");

            mensaje = sc.nextLine();

            if (mensaje.equals("1")) {

                vista.mostrarMensaje("Has elegido la opcion mandar mensaje ");
                vista.mostrarMensaje("Escribe el mensaje que quieres guardar a la BD. ");
                mensaje = sc.nextLine();
                mensajeCifradoAES = Criptografia.encripta(MetodosEstaticos.stringToByteArray(mensaje),
                        cliente1.getSecretKey());
                bd.setMensaje(mensajeCifradoAES);
                hashes.add(Criptografia.generarHashMD5(mensaje));
                vista.mostrarMensaje("Mensaje guardado");

                vista.mostrarMensaje(" ");

            } else if (mensaje.equals("2")) {
                vista.mostrarMensaje("Que posicion de la lista quieres recuperar. ");
                index = sc.nextInt();
                vista.mostrarMensaje("Mensaje de la bd ya descifrado: " + bd.desencriptarMensaje(index));
                hash2 = Criptografia.generarHashMD5(bd.desencriptarMensaje(index));
                if (hashes.get(index).equals(hash2)) {
                    vista.mostrarMensaje("Los hashes son iguales.");
                } else {
                    vista.mostrarMensaje("Los hashes no son iguales.");
                }
                sc.nextLine();

                vista.mostrarMensaje(" ");

            } else if (mensaje.equals("3")) {
                vista.mostrarMensaje("!Adioooss¡");
                break;
            } else {
                vista.mostrarMensaje("Opcion incorrecta");

            }
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