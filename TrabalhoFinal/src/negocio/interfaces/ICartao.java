/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.interfaces;

/**
 *
 * @author feliperiffel
 */
public interface ICartao {
    String getID();
    void addSaldo(double valor);
    double getSaldo();
    boolean isResidente();
    double descontaValor(double valor);    
}
