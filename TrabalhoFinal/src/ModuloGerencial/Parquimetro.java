package ModuloGerencial;

import java.util.ArrayList;
import java.util.Calendar;

public class Parquimetro {
    private final int tarifa = 25;
    private final int incrementoDeTempo = 10;
    
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
        moedas = new Moeda[] { new Moeda("Um centavo", 1), 
            new Moeda("Cinco Centavos", 5), 
            new Moeda("Dez Centavos", 10), 
            new Moeda("Vinte e Cinco Centavos", 25), 
            new Moeda("Cinquenta centavos", 50), 
            new Moeda("Um real", 100) };
    }
//    
//    public ArrayList<Moeda> EmiteTicket(TipoPagamento pagamento, ArrayList<Moeda> moedas)
//    {
//        int tempo = geraTempo(moedas);
//        Ticket novoTicket = new Ticket(this, nroTicket, tempo, Calendar.getInstance());
//        ArrayList<Moeda> troco = verificaTroco(novoTicket);
//        if (troco == null) return null;
//        nroTicket++;
//        return troco;
//    }
    
//    private int geraTempo(ArrayList<Moeda> pagamento)
//    {
//        int total = 0;
//        for (Moeda m : pagamento)
//        {
//            total = m.getValor();
//        }
//        return (total / tarifa) * incrementoDeTempo;
//    }
    
    //Verifica o troco para moedas! Para outros tipos devemos retornar uma lista vazia sempre.
//    private ArrayList<Moeda> verificaTroco(Ticket umTicket) 
//    {
//        ArrayList<Moeda> troco = new ArrayList<Moeda>();
//        double totalAPagar = umTicket.getTotalAPagar();
//        int i = 0;
//        while (totalAPagar != 0)
//        {
//            int qtdMoedas = (int) (totalAPagar / moedas[i].getValor());
//            if (qtdMoedas > moedas[i].getQuantidade()) return null;
//            else if (qtdMoedas != 0)
//            {
//                Moeda atual = moedas[i];
//                troco.add(new Moeda(atual.getValor(), qtdMoedas));
//                totalAPagar = totalAPagar % atual.getValor();
//            }
//            i++;
//        }
//        return troco;
//    }
}
