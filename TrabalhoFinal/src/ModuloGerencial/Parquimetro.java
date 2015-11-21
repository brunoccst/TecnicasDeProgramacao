package ModuloGerencial;

import java.util.ArrayList;
import java.util.Calendar;

public class Parquimetro {
    private int id;
    private Moeda[] moedas;
    private int nroTicket; //TODO: Deletar depois! Utilizar o numero gerado pelo banco.
    
    /*
    * @param int umId Numero do ID do parquimetro
    * @param int qtdUmCtv Quantidade de moedas de um centavo
    * @param int qtdCincoCtv Quantidade de moedas de cinco centavos
    * @param int qtdDezCtv Quantidade de moedas de dez centavos
    * @param int qtdVinteCincoCtv, Quantidade de moedas de vinte e cinco centavos
    * @param int qtdCinqCtv Quantidade de moedas de cinquenta centavos
    * @param int qtdUmReal Quantidade de moedas de um real
    */
    public Parquimetro(int umId, int qtdUmCtv, int qtdCincoCtv, int qtdDezCtv, int qtdVinteCincoCtv, int qtdCinqCtv, int qtdUmReal)
    {
        id = umId;
        nroTicket = 0; //TODO: Deletar depois! Utilizar o numero gerado pelo banco.
        moedas = new Moeda[] { new Moeda(TipoMoeda.UmCentavo, 1, qtdUmCtv), 
            new Moeda(TipoMoeda.CincoCentavos, 5, qtdCincoCtv), 
            new Moeda(TipoMoeda.DezCentavos, 10, qtdDezCtv), 
            new Moeda(TipoMoeda.VinteCincoCentavos, 25, qtdVinteCincoCtv), 
            new Moeda(TipoMoeda.CinquentaCentavos, 50, qtdCinqCtv), 
            new Moeda(TipoMoeda.UmReal, 100, qtdUmReal) };
    }
    
    public ArrayList<Moeda> EmiteTicket(TipoPagamento pagamento, int tempo) throws Exception
    {
        Ticket novoTicket = new Ticket(this, nroTicket, tempo, Calendar.getInstance());
        ArrayList<Moeda> troco = verificaTroco(novoTicket);
        if (troco == null) return null;
        nroTicket++;
        return troco;
    }
    
    //Verifica o troco para moedas! Para outros tipos devemos retornar uma lista vazia sempre.
    private ArrayList<Moeda> verificaTroco(Ticket umTicket) 
    {
        ArrayList<Moeda> troco = new ArrayList<Moeda>();
        double totalAPagar = umTicket.getTotalAPagar();
        int i = 0;
        while (totalAPagar != 0)
        {
            int qtdMoedas = (int) (totalAPagar / moedas[i].getValor());
            if (qtdMoedas > moedas[i].getQuantidade()) return null;
            else if (qtdMoedas != 0)
            {
                Moeda atual = moedas[i];
                troco.add(new Moeda(atual.getTipo(), atual.getValor(), qtdMoedas));
                totalAPagar = totalAPagar % atual.getValor();
            }
            i++;
        }
        return troco;
    }
}
