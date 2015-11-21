package ModuloGerencial;

import java.util.Calendar;

public class Ticket {
    private Parquimetro parquimetro;
    private int nroSerial;
    private Calendar emissao;
    private Calendar validade;
    private int tempo;
    
    public Ticket(Parquimetro umParquimetro, int umNroSerial, int umTempo, Calendar umaEmissao)
    {
        parquimetro = umParquimetro;
        nroSerial = umNroSerial;
        tempo = umTempo;
        emissao = umaEmissao;
        validade = (Calendar) emissao.clone();
        validade.add(Calendar.MINUTE, umTempo);
    }
    
    public Parquimetro getParquimetro()
    {
        return parquimetro;
    }
    
    public int getNroSerial()
    {
        return nroSerial;
    }
    
    public Calendar getEmissao()
    {
        return emissao;
    }
    
    public Calendar getValidade()
    {
        return validade;
    }
    
    public int getTempo()
    {
        return tempo;
    }
    
    public void incrementaTempo()
    {
        tempo += 10;
        validade.add(Calendar.MINUTE, 10);
    }
    
    public double getTotalAPagar()
    {
        return (tempo * 0.25) / 10.0;
    }
}
