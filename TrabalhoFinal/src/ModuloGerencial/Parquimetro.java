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
    
    public void setEndereco(String novoEndereco){
        this.endereco = novoEndereco;
    }

    @Override
    public ArrayList getTickets() {
        return tickets;
    }
    
    @Override
    public String toString()
    {
        return "[ ID: " + id + " | Endereço: " + endereco +" ]";
    }
}
