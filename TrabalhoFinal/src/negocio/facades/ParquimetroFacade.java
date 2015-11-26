/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.facades;
import negocio.entidades.Parquimetro;
import java.time.LocalDateTime;
import java.util.ArrayList;
import negocio.interfaces.ITicket;
import persistencia.dao.ParquimetroDAO;
/**
 *
 * @author feliperiffel
 */
public class ParquimetroFacade {
    public static Parquimetro criaParquimetro(String endereco){
        Parquimetro p = ParquimetroDAO.creteNewParquimetro(endereco);
        p.addTickets(TicketFacade.getTickets(p, null, null));
        return p;
    }
    public static void updateParquimetro(Parquimetro parquimetro){
        
    }
    public static Parquimetro getParquimetro(int id){
        Parquimetro p = ParquimetroDAO.getParquimetro(id);
        p.addTickets(TicketFacade.getTickets(p, null, null));
        return p;
    }
    public static ArrayList<Parquimetro> getParquimetros(){
        ArrayList<Parquimetro> ps = ParquimetroDAO.getParquimetro();
        for(Parquimetro p : ps){
            p.addTickets(TicketFacade.getTickets(p, null, null));
        }
        return ps;
    }
    
    public static double getValorTotalGeral(LocalDateTime inicio, LocalDateTime fim, ArrayList<Parquimetro> parquimetros)
    {
        double total = 0.0;
        for (Parquimetro p : parquimetros)
        {
            for (ITicket t : p.getTickets())
                if ((t.getCartao() == null || !t.getCartao().isResidente())
                        && t.getEmissao().getMonthValue() >= inicio.getMonthValue()
                        && t.getValidade().getMonthValue() <= fim.getMonthValue())
                    total += t.getValor();
        }
        return total;
    }
    
    public static double getValorTotalIsento(LocalDateTime inicio, LocalDateTime fim, ArrayList<Parquimetro> parquimetros)
    {
        double total = 0.0;
        for (Parquimetro p : parquimetros)
        {
            for (ITicket t : p.getTickets())
                if ((t.getCartao() != null && t.getCartao().isResidente())
                        && t.getEmissao().getMonthValue() >= inicio.getMonthValue()
                        && t.getValidade().getMonthValue() <= fim.getMonthValue())
                    total += t.getValor();
        }
        return total;
    }
}
