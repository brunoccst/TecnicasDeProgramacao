/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parquimetro_;

import ModuloGerencial.Parquimetro;
import ModuloGerencial.ParquimetroFacade;
import ModuloGerencial.Configuracoes;
import ModuloGerencial.ConfiguracoesFacade;
import ModuloGerencial.Ticket;
import ModuloGerencial.Cartao;
import ModuloGerencial.CartaoFacade;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author feliperiffel
*/

public class OParquimetro {
    
    Parquimetro parquimetro;
    Configuracoes config;
    int serialTicket = 0;
    
    ArrayList<Ticket> tickets;
    
    private int moeda5 = 0, moeda10 = 0, moeda25 = 0, moeda50 = 0, moeda100 = 0;
    private int _moeda5 = 0, _moeda10 = 0, _moeda25 = 0, _moeda50 = 0, _moeda100 = 0;
    private int validade;
    
    public OParquimetro (int code){
        parquimetro = ParquimetroFacade.getParquimetro(code);
        serialTicket = parquimetro.getLastSerial() + 1;
        config = ConfiguracoesFacade.getConfiguracoes();
        tickets = new ArrayList();
    }
    
    public void addMoeda(Moeda moeda){
        if (moeda == Moeda.Moeda5)
            moeda5++;
        else if (moeda == Moeda.Moeda10)
            moeda10++;
        else if (moeda == Moeda.Moeda25)
            moeda25++;
        else if (moeda == Moeda.Moeda50)
            moeda50++;
        else if (moeda == Moeda.Moeda100)
            moeda100++;
    }
    
    public void novoTicket(){
        moeda5 += _moeda5;
        moeda10 += _moeda10;
        moeda25 += _moeda25;
        moeda50 += _moeda50;
        moeda100 += _moeda100;
        
        _moeda5 = 0;
        _moeda10 = 0;
        _moeda25 = 0;
        _moeda50 = 0;
        _moeda100 = 0;
        
        validade = 0;
    }
    
    public int addValidade(){
        if (validade < config.getTempoMinimo())
            validade += config.getIncremento();
        
        if (validade > config.getTempoMaximo()) 
            validade = config.getTempoMaximo();
        
        return validade;
    }
    
    public int dcrValidade(){
        if (validade > config.getTempoMinimo())
            validade -= config.getTempoMinimo();
        
        if (validade < config.getTempoMinimo())
            validade = config.getTempoMinimo();
        
        return validade;
    }
    
    private double custo(){
        int v = validade;
        int inc = config.getIncremento();
        
        double vinc = config.getTarifaIncremento();
        double valor = config.getValorInicial();
        
        v -= config.getTempoMinimo();
        while (v > 0){
            v -= inc;
            valor += vinc;
        }
        return valor;
    }
    
    public int compraTicket(){
        
        double valor = custo();
        
        if (((_moeda5 * 0.5) + (_moeda10 * 0.1) + (_moeda25 * 0.25) + (_moeda50 * 0.5) + (_moeda100 * 1)) >= valor){
            
            ImprimeTicket(null);
            return 0;
        }
        else 
            return 1;
    }
    
    public int compraTicket(String idCartao){
        Cartao c = CartaoFacade.getCartao(idCartao);
        if (c == null) return 99;
        
        if (c.isResidente()){
            ImprimeTicket(c);
        }
        else{
            if (c.getSaldo() > custo()){
                ImprimeTicket(c);
            }
            else
                return 1;
        }
        return 0;
    }
    
    public double valorIncerido(){
        return ((_moeda5 * 0.5) + (_moeda10 * 0.1) + (_moeda25 * 0.25) + (_moeda50 * 0.5) + (_moeda100 * 1));
    }
    
    private void ImprimeTicket(Cartao cartao)
    {    
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime val = now.plusMinutes(validade);
        if (cartao == null)
            tickets.add(new Ticket(parquimetro, serialTicket, now, val));
        else
            tickets.add(new Ticket(parquimetro, serialTicket, now, val, cartao)); 
        
        System.out.println(tickets.get(tickets.size() - 1));
        
        novoTicket();
    }
    
    public ArrayList<Integer> troco(){
        return new ArrayList();
    }
    
}
