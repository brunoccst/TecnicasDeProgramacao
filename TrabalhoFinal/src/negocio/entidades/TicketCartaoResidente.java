package negocio.entidades;

import java.time.LocalDateTime;
import negocio.facades.ConfiguracoesFacade;
import negocio.interfaces.ICartao;
import negocio.interfaces.IParquimetro;
import negocio.interfaces.ITicket;

/**
 *
 * @author Bruno
 */
public class TicketCartaoResidente implements ITicket {
    private ITicket ticket;
    
    public TicketCartaoResidente(ITicket ticket)
    {
        this.ticket = ticket;
    }
    
    @Override
    public double getValor() {
        Configuracoes conf = ConfiguracoesFacade.getConfiguracoes();
        return conf.getValorInicial();
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(ticket.toString());
        sb.append("(Valor isento)");
        return sb.toString();
    }

    @Override
    public LocalDateTime getEmissao() {
        return ticket.getEmissao();
    }

    @Override
    public LocalDateTime getValidade() {
        return ticket.getValidade();
    }

    @Override
    public IParquimetro getParquimetro() {
        return ticket.getParquimetro();
    }

    @Override
    public int getSerial() {
        return ticket.getSerial();
    }

    @Override
    public ICartao getCartao() {
        return ticket.getCartao();
    }
    
}
