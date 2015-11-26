/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidades;

import negocio.facades.ConfiguracoesFacade;
import negocio.interfaces.ICartao;
import negocio.interfaces.IParquimetro;
import java.time.Duration;
import java.time.LocalDateTime;
import negocio.interfaces.ITicket;


/**
 *
 * @author feliperiffel
 */
public class Ticket implements ITicket {
    private IParquimetro parquimetro;
    private int serial;
    private LocalDateTime emissao;
    private LocalDateTime validade;
    private ICartao cartao;

    public Ticket(IParquimetro parquimetro, int serial, LocalDateTime emissao, LocalDateTime validade)
    {
        this(parquimetro, serial, emissao, validade, null);
    }
    
    public Ticket(IParquimetro parquimetro, int serial, LocalDateTime emissao, LocalDateTime validade, ICartao cartao){
        this.parquimetro = parquimetro;
        this.serial = serial;
        this.emissao = emissao;
        this.validade = validade;
        this.cartao = cartao;
    }
    
    @Override
    public IParquimetro getParquimetro(){
        return parquimetro;
    }
    
    @Override
    public int getSerial(){
        return serial;
    }
    
    @Override
    public LocalDateTime getEmissao(){
        return emissao;
    }
    
    @Override
    public LocalDateTime getValidade(){
        return validade;
    }
    
    public ICartao getCartao(){
        return cartao;
    }
    
    @Override
    public double getValor()
    {
        Configuracoes conf = ConfiguracoesFacade.getConfiguracoes();
        Duration dif = Duration.between(validade, emissao);
        int minutes = (int) (dif.getSeconds() / 60);
        double valor = conf.getValorInicial();
        minutes -= conf.getValorInicial();
        
        while(minutes > 0){
            minutes -= conf.getIncremento();
            valor += conf.getTarifaIncremento();
        }
        
        return valor;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[Ticket ");
        sb.append(serial);
        sb.append(" | R$");
        sb.append(getValor());
        sb.append(" | ");
        sb.append(emissao.getDayOfMonth() + "/" + emissao.getMonthValue() + "/" + emissao.getYear());
        sb.append(" até ");
        sb.append(validade.getDayOfMonth() + "/" + validade.getMonthValue() + "/" + validade.getYear());
        sb.append("]");
        return sb.toString();
    }
}
