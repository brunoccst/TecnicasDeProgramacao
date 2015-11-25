/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.facades;

import negocio.entidades.Configuracoes;
import persistencia.dao.ConfiguracoesDAO;
/**
 *
 * @author feliperiffel
 */
public class ConfiguracoesFacade {
    public static Configuracoes getConfiguracoes(){
        return ConfiguracoesDAO.getConfiguracoes();
    }
    public static void updateConfiguracoes(Configuracoes configuracoes){
        
    }
}
