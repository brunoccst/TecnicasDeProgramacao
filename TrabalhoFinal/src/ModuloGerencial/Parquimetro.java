package ModuloGerencial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Parquimetro implements IParquimetro
{
    private int id;
    private String endereco;
    private ArrayList<Ticket> tickets;
    
    public Parquimetro(int id, String endereco)
    {
        this.id = id;
        this.endereco = endereco;
    }
    
    public void addTickets(ArrayList<Ticket> tickets){
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
        for (Ticket t : tickets)
        {
            valor += t.getValor();
        }
        return valor;
    }
    
        
    public double getValorTotal(int ano)
    {
        double valor = 0.0;
        for (Ticket t : tickets)
        {
            if (t.getEmissao().getYear() == ano)
                valor += t.getValor();
        }
        return valor;
    }
    
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }

    @Override
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    
    @Override
    public String toString()
    {
        return "[ ID: " + id + " | Endereço: " + endereco +" ]";
    }
}
