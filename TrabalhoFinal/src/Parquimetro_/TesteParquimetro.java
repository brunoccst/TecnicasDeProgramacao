/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parquimetro_;

/**
 *
 * @author feliperiffel
 */
public class TesteParquimetro {
    
    public void testaCompraCerta(){
        OParquimetro p = new OParquimetro(1);
        
        p.novoTicket();
        p.addMoeda(Moeda.Moeda50);
        p.addMoeda(Moeda.Moeda25);
       // if (p.compraTicket() == 0) return true;
       // return false;
    }
    
    public void testaValorErrado(){
        OParquimetro p = new OParquimetro(1);
        
        p.novoTicket();
        p.addMoeda(Moeda.Moeda5);
        //if(p.compraTicket() == 1)return true;
        //return false;
    }
    
    public boolean testaCartaoResidente(){
        OParquimetro p = new OParquimetro(1);
        
        p.novoTicket();
        //if (p.compraTicket("") == 0) return true;
        return false;
    }
}
