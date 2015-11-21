package ModuloGerencial;

public class Moeda {
    private TipoMoeda tipo;
    private int valor;
    private int quantidade;
    
    public Moeda(TipoMoeda umTipo, int umValor, int umaQuantidade)
    {
        tipo = umTipo;
        valor = umValor;
        quantidade = umaQuantidade;
    }
    
    public TipoMoeda getTipo()
    {
        return tipo;
    }
    
    public int getValor()
    {
        return valor;
    }
    
    public int getQuantidade()
    {
        return quantidade;
    }
}
