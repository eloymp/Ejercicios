public class HiloBanco implements Runnable{
    
    Banco banco;
    private double depositar=0;
    private double retirar=0;
    int id = 0;

    public HiloBanco(double incrementar, double decrementar, Banco banco){
        this.depositar=incrementar;
        this.retirar=decrementar;
        this.banco=banco;
        id = banco.getId();
    }

    @Override
    public void run (){
            banco.depositar(depositar, id);
            banco.retirar(retirar, id);
    }
}
