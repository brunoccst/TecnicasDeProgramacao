/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;

import java.time.LocalDateTime;


/**
 *
 * @author feliperiffel
 */
public class Ticket {
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
    
    public IParquimetro getParquimetro(){
        return parquimetro;
    }
    
    public int getSerial(){
        return serial;
    }
    
    public LocalDateTime getEmissao(){
        return emissao;
    }
    
    public LocalDateTime getValidade(){
        return validade;
    }
    
    public ICartao getCartao(){
        return cartao;
    }
}
