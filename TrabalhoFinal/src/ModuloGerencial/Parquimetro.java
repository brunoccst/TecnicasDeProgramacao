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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getTikets(LocalDateTime doDia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getTickets(LocalDateTime doDia, LocalDateTime ateDia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
