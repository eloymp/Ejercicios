import java.io.Serializable;

public class Operacion implements Serializable {
    int num1;
    int num2;
    int operacion;
    double resultado;

    public Operacion(int num1, int num2, int operacion) {
        this.num1 = num1;
        this.num2 = num2;
        this.operacion = operacion;
    }

    public Operacion() {

    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getOperacion() {
        return operacion;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

}
