package ModuloGerencial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Parquimetro implements IParquimetro
{
    private int id;
    private String endereco;
    
    public Parquimetro(int id, String endereco)
    {
        this.id = id;
        this.endereco = endereco;
    }
    
    public int getId(){
        return id;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }

    @Override
    public ArrayList getTickets() {
        return TicketFacade.getTickets(this, null, null);
    }

    @Override
    public ArrayList getTikets(LocalDateTime doDia) {
        return TicketFacade.getTickets(this, doDia, null);
    }

    @Override
    public ArrayList getTickets(LocalDateTime doDia, LocalDateTime ateDia) {
        return TicketFacade.getTickets(this, doDia, ateDia);
    }

    @Override
    public void addTicket(Ticket ticket) {
        TicketFacade.addTicket(ticket);
    }
    
    @Override
    public String toString()
    {
        return "[ ID: " + id + " | Endereço: " + endereco +" ]";
    }
}
