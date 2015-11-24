/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;
import java.time.LocalDateTime;
import persistencia.TicketDAO;

import java.util.ArrayList;

/**
 *
 * @author feliperiffel
 */
public class TicketFacade {
    public static ArrayList<Ticket> getTickets(Parquimetro parquimetro, LocalDateTime deDia, LocalDateTime ateDia){
        return TicketDAO.getTickets(parquimetro, deDia, deDia);
    }
    public static void addTicket(Ticket ticket){
        TicketDAO.creteNewTicket(ticket.getParquimetro().getId(), ticket.getSerial(), ticket.getEmissao(), ticket.getValidade(), ticket.getCartao() != null ? ticket.getCartao().getID() : null);
    }
}
