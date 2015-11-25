/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;
import negocio.entidades.Cartao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import persistencia.DBConnection;
import negocio.RandomString;

/**
 *
 * @author feliperiffel
 */
public class CartaoDAO {
    
    public static Cartao creteNewCartao(boolean residente){
        
        Cartao novoCartao = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            
            String cID = RandomString.nextString(128);
            
            String sql = "insert into CartoesRecarregaveis(id, saldo, residente) values(?, ?, ?)";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, cID);
                comando.setDouble(2, 0);
                comando.setInt(3, residente ? 1 : 0);
                if (comando.executeUpdate() > 0) {
                    novoCartao = new Cartao(cID, 0, residente);
                    System.out.println("Inserção efetuada com sucesso");
                } else {
                    System.out.println("Falha na inserção");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return novoCartao;
    }
    
    public static Cartao getCartao(String id){
        Cartao cartao = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            //Consultar dados da tabela
            String sql = "select * from CartoesRecarregaveis where id = ?";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, id);
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        cartao = new Cartao(resultados.getString("id"), resultados.getDouble("saldo"), resultados.getInt("residente") == 1 ? true : false);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cartao;
    }
    
    public static ArrayList<Cartao> getCartao(){
        ArrayList<Cartao> cartoes = new ArrayList();
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            //Consultar dados da tabela
            String sql = "select * from CartoesRecarregaveis";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        cartoes.add(new Cartao(resultados.getString("id"), resultados.getDouble("saldo"), resultados.getInt("residente") == 1 ? true : false));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cartoes;
    }
}
