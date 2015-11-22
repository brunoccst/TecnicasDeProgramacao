/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;

/**
 *
 * @author feliperiffel
 */
public class Cartao implements ICartao {
    private String id;
    private double saldo;
    private boolean residente;
    
    public Cartao(String id, double saldo){
        this(id, saldo, false);
    }
    
    public Cartao (String id, double saldo, boolean residente){
        this.id = id;
        this.saldo = saldo;
        this.residente = residente;
    }
    
    @Override
    public String getID(){
        return id;
    }
    
    @Override
    public void addSaldo(double valor){
        this.saldo += valor;
    }
    
    @Override
    public double getSaldo(){
        return saldo;
    }
    
    @Override
    public boolean isResidente(){
        return residente;
    }
    
    @Override
    public double descontaValor(double valor){
        saldo -= valor;
        return saldo;
    }
}
