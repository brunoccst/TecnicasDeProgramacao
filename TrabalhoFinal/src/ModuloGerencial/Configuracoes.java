/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;

import java.time.LocalTime;

/**
 *
 * @author feliperiffel
 */
public class Configuracoes {
    private LocalTime inicioTarifacao;
    private LocalTime fimTarifacao;
    private int tempoMinimo;
    private int tempoMaximo;
    private int incremento;
    private double tarifaIncremento;
    private boolean moeda5, moeda10, moeda25, moeda50, moeda100;
    private boolean cartaoRecarregavel, cartaoResidente;
    
    public Configuracoes(LocalTime inicioTarifacao, LocalTime fimTarifacao, int tempoMinimo, int tempoMaximo, int incremento, double tarifaIncremento, boolean moeda5, boolean moeda10, boolean moeda25, boolean moeda50, boolean moeda100, boolean cartaoRecarregavel, boolean cartaoResidente){
        this.inicioTarifacao = inicioTarifacao;
        this.fimTarifacao = fimTarifacao;
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

    public Configuracoes(LocalTime of, LocalTime of0, int i, int i0, int i1, int i2, int i3, boolean b, boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public LocalTime getInicioTarifacao(){
        return inicioTarifacao;
    }
    
    public LocalTime getFimTarifacao(){
        return fimTarifacao;
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
