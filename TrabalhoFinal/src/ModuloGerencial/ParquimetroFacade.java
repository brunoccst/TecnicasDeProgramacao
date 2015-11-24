/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import persistencia.ParquimetroDAO;
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
}
