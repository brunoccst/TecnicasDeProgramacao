package ModuloGerencial;

public class Moeda {
    private String nome;
    private double valor;

    public Moeda(String umNome, double umValor)
    {
        nome = umNome;
        valor = umValor;
    }
        
    public double getValor()
    {
        return valor;
    }
    
    public String getNome()
    {
        return nome;
    }
}
