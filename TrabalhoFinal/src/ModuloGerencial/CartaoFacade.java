/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;

import java.util.ArrayList;
import persistencia.CartaoDAO;

/**
 *
 * @author feliperiffel
 */
public class CartaoFacade {
    public static Cartao createCartao(boolean residente){
        return CartaoDAO.creteNewCartao(residente);
    }
    public static void updateCartao(Cartao cartao){
        
    }
    public static Cartao getCartao(String id){
        return CartaoDAO.getCartao(id);
    }
    public ArrayList<Cartao> getCartao(){
        return CartaoDAO.getCartao();
    }
}
