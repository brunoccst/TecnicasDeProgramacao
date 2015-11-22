package ModuloGerencial;

import persistencia.CartaoDAO;

/**
 *
 * @author Bruno
 */
public class Programa {

    public static void main(String[] args)
    {
        Cartao c = CartaoDAO.creteNewCartao(true);
        System.out.println(c.getID());
    }
    
}