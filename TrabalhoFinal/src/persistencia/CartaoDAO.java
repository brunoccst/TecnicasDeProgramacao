/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;
import ModuloGerencial.Cartao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 *
 * @author feliperiffel
 */
public class CartaoDAO {
    
    public static Cartao creteNewCartao(boolean residente){
        
        Cartao novoCartao = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            
            novoCartao = new Cartao(RandomString.nextString(128), 0, residente);
            
            //Inserir dados na tabela
            String sql2 = "insert into CartoesRecarregaveis(id, saldo, residente) values(?, ?, ?)";
            try (PreparedStatement comando = conexao.prepareStatement(sql2)) {
                comando.setString(1, novoCartao.getID());
                comando.setDouble(2, 0);
                comando.setInt(3, residente ? 1 : 0);
                if (comando.executeUpdate() > 0) {
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
    
}
