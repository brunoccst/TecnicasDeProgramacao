package ModuloGerencial;

import persistencia.CartaoDAO;

/**
 *
 * @author Bruno
 */
public class Programa {

    public static void main(String[] args)
    {
        CartaoDAO cDao = new CartaoDAO();
        Cartao c = cDao.creteNewCartao(true);
        System.out.println(c.getID());
    }
    
}