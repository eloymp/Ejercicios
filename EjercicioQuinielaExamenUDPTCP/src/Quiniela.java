import java.io.Serializable;
import java.util.Random;

public class Quiniela implements Serializable {

    private static final long serialVersionUID = 299L; // Corregido
    private String lista[];
    private String hash = "";

    public Quiniela() {
        lista = new String[15];
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String [] getLista(){
        return this.lista;
    }

    public String getListaString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            sb.append(lista[i]);
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Quiniela: [");
        for (int i = 0; i < 15; i++) {
            sb.append(lista[i]);
            if (i != 14) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void rellenarQuiniela() {
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int ran = random.nextInt(3);
            switch (ran) {
                case 0:
                    this.lista[i] = "1";
                    break;
                case 1:
                    this.lista[i] = "2";
                    break;
                case 2:
                    this.lista[i] = "X";
                    break;
            }
        }
    }
}
