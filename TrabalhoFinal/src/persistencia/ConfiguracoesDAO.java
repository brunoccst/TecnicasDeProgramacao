/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import ModuloGerencial.Configuracoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author feliperiffel
 */
public class ConfiguracoesDAO {
    
    public static Configuracoes getConfiguracoes(){
        return new Configuracoes(LocalTime.of(7, 30), LocalTime.of(8, 30), 30, 120, 10, 0.25, true, true, true, true, true, true, true);
    }
 
    public static void setConfiguracoes(Configuracoes conf){
    }
}
