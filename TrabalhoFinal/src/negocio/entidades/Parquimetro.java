package negocio.entidades;

import negocio.interfaces.IParquimetro;
import java.time.LocalDateTime;
import java.util.ArrayList;
import negocio.interfaces.ITicket;

public class Parquimetro implements IParquimetro
{
    private int id;
    private String endereco;
    private ArrayList<ITicket> tickets;
    
    public Parquimetro(int id, String endereco)
    {
        this.id = id;
        this.endereco = endereco;
    }
    
    public void addTickets(ArrayList<ITicket> tickets){
        this.tickets = tickets;
    }
    
    public int getId(){
        return id;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public double getValorTotal()
    {
        double valor = 0.0;
        for (ITicket t : tickets)
        {
            valor += t.getValor();
        }
        return valor;
    }
    
        
    public double getValorTotal(int ano)
    {
        double valor = 0.0;
        for (ITicket t : tickets)
        {
            if (t.getEmissao().getYear() == ano)
                valor += t.getValor();
        }
        return valor;
    }
    
    public double getValorTotal(LocalDateTime inicio, LocalDateTime fim)
    {
        double valor = 0.0;
        for (ITicket t : tickets)
        {
            if ((t.getEmissao().isAfter(inicio) || t.getEmissao().isEqual(inicio))
                    && (t.getEmissao().isBefore(fim) || t.getEmissao().isEqual(fim)))
                valor += t.getValor();
        }
        return valor;
    }
    
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }

    @Override
    public ArrayList<ITicket> getTickets() {
        return tickets;
    }
    
    @Override
    public String toString()
    {
        return "[ ID: " + id + " | Endereço: " + endereco +" ]";
    }
}
