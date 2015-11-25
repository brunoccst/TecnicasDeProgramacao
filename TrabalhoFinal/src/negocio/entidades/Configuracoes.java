/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidades;

import java.time.LocalTime;

/**
 *
 * @author feliperiffel
 */
public class Configuracoes {
    private LocalTime inicioTarifacao;
    private LocalTime fimTarifacao;
    private double valorInicial;
    private int tempoMinimo;
    private int tempoMaximo;
    private int incremento;
    private double tarifaIncremento;
    private boolean moeda5, moeda10, moeda25, moeda50, moeda100;
    private boolean cartaoRecarregavel, cartaoResidente;
    
    public Configuracoes(LocalTime inicioTarifacao, LocalTime fimTarifacao, double valorInicial, int tempoMinimo, int tempoMaximo, int incremento, double tarifaIncremento, boolean moeda5, boolean moeda10, boolean moeda25, boolean moeda50, boolean moeda100, boolean cartaoRecarregavel, boolean cartaoResidente){
        this.inicioTarifacao = inicioTarifacao;
        this.fimTarifacao = fimTarifacao;
        this.valorInicial = valorInicial;
        this.tempoMinimo = tempoMinimo;
        this.tempoMaximo = tempoMaximo;
        this.incremento = incremento;
        this.tarifaIncremento = tarifaIncremento;
        this.moeda5 = moeda5;
        this.moeda10 = moeda10;
        this.moeda25 = moeda25;
        this.moeda50 = moeda50;
        this.moeda100 = moeda100;
        this.cartaoRecarregavel = cartaoRecarregavel;
        this.cartaoResidente = cartaoResidente;
    }
    
    public LocalTime getInicioTarifacao(){
        return inicioTarifacao;
    }
    
    public LocalTime getFimTarifacao(){
        return fimTarifacao;
    }
    
    public double getValorInicial(){
        return valorInicial;
    }
    
    public int getTempoMinimo(){
        return tempoMinimo;
    }
    
    public int getTempoMaximo(){
        return tempoMaximo;
    }
    
    public int getIncremento(){
        return incremento;
    }
    
    public double getTarifaIncremento(){
        return tarifaIncremento;
    }
    
    public boolean getMoeda5(){
        return moeda5;
    }
    
    public boolean getMoeda10(){
        return moeda10;
    }
    
    public boolean getMoeda25(){
        return moeda25;
    }
    
    public boolean getMoeda50(){
        return moeda50;
    }
    
    public boolean getMoeda100(){
        return moeda100;
    }
    
    public boolean getCartaoRecarregavel(){
        return cartaoRecarregavel;
    }
    
    public boolean getCartaoResidente(){
        return cartaoResidente;
    }
    
    public void setInicioTarifacao(LocalTime novoInicioTarifacao){
        inicioTarifacao = novoInicioTarifacao;
    }
    
    public void setFimTarifacao(LocalTime novoFimTarifacao){
        fimTarifacao = novoFimTarifacao;
    }
    
    public void setValorInicial(double novoValorInicial){
        valorInicial = novoValorInicial;
    }
    
    public void setTempoMinimo(int novoTempoMinimo){
        tempoMinimo = novoTempoMinimo;
    }
    
    public void setTempoMaximo(int novoTempoMaximo){
        tempoMaximo = novoTempoMaximo;
    }
            
    public void setIncremento(int novoIncremento){
        incremento = novoIncremento;
    }
    
    public void setTarifaIncremento(double novaTarifaIncremento){
        tarifaIncremento = novaTarifaIncremento;
    }
    
    public void setMoeda5(boolean aceita){
        moeda5 = aceita;
    }
    
    public void setMoeda10(boolean aceita){
        moeda10 = aceita;
    }
    
    public void setMoeda25(boolean aceita){
        moeda25 = aceita;
    }
    
    public void setMoeda50(boolean aceita){
        moeda50 = aceita;
    }
    
    public void setMoeda100(boolean aceita){
        moeda100 = aceita;
    }
    
    public void setCartaoRecarregavel(boolean aceita){
        cartaoRecarregavel = aceita;
    }
    public void setCartaoResidente(boolean aceita){
        cartaoResidente = aceita;
    }
}
