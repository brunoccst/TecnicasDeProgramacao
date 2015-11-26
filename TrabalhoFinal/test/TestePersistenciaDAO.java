/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import persistencia.dao.*;
import negocio.entidades.*;
/**
 *
 * @author 14104931
 */
public class TestePersistenciaDAO {
    
    @Test
    public void criaCartao(){
        Cartao novoCartao = CartaoDAO.createNewCartao(true);
        Assert.assertTrue(novoCartao != null);
    }
    
    @Test
    public void pegaCartoPorId(){
        String id = "bGOTT10M00H1vibvmlQWCGA50nTddmrTcb8pMqOtApdseKiOININVtHzFBGx67LuIr0NNAJRLxu1csZZPZXk8VqooDvMBAMC5j2IT5BKoXTfBzcsNEsQ9QxipuWoIAuV";
        Cartao novoCartao = CartaoDAO.getCartao(id);
        Assert.assertTrue(novoCartao.getID().equals(id));
    }
}
