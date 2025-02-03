package Controlador;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MetodosEstaticos {
    public static char[][] stringToCharArray(String tableroString) {
        String[] filas = tableroString.split("\n"); // Divide por saltos de línea
        char[][] tablero = new char[filas.length][];

        for (int i = 0; i < filas.length; i++) {
            tablero[i] = filas[i].toCharArray(); // Convierte cada línea en un array de chars
        }
        return tablero;
    }
        //Metodo general para pasar Strings a array de bytes
    public static byte[] stringToByteArray(String input) {
        return input.getBytes(StandardCharsets.UTF_8);
    }
    // Convertir int a byte[] (4 bytes)
    public static byte[] intToByteArray(int value) {
        return ByteBuffer.allocate(4).putInt(value).array();
    }
    //Convertir boolean a byte[] (true = 1, false = 0)
    public static byte[] booleanToByteArray(boolean value) {
        return new byte[]{(byte) (value ? 1 : 0)};
    }
    //GENERICO PARA CONVERTIR UN CHAR[][] A STRING
    public static String charArrayToString(char[][] tablero) {
        StringBuilder sb = new StringBuilder();
        for (char[] fila : tablero) {
            sb.append(new String(fila)).append("\n"); // Agrega cada fila con salto de línea
        }
        return sb.toString().trim(); // Evita el último salto de línea
    }
    public static String byteArrayToString(byte[] byteArray) {
        return new String(byteArray); // Convierte los bytes en String
    }

    public static SecretKey byteArrayToSecretKey(byte[] keyBytes, String algorithm) {
        return new SecretKeySpec(keyBytes, algorithm);
    }
}
