/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.facades;
import java.sql.SQLIntegrityConstraintViolationException;
import negocio.entidades.Ticket;
import negocio.entidades.Parquimetro;
import java.time.LocalDateTime;
import persistencia.dao.TicketDAO;

import java.util.ArrayList;
import negocio.interfaces.ITicket;

/**
 *
 * @author feliperiffel
 */
public class TicketFacade {
    public static ArrayList<ITicket> getTickets(Parquimetro parquimetro, LocalDateTime deDia, LocalDateTime ateDia){
        return TicketDAO.getTickets(parquimetro, deDia, deDia);
    }
    public static void addTicket(Ticket ticket) throws SQLIntegrityConstraintViolationException{
        TicketDAO.creteNewTicket(ticket.getParquimetro().getId(), ticket.getSerial(), ticket.getEmissao(), ticket.getValidade(), ticket.getCartao() != null ? ticket.getCartao().getID() : null);
    }
    public static int getlastSerial(Parquimetro parquimetro){
        return TicketDAO.lastSerial(parquimetro);
    }
}
