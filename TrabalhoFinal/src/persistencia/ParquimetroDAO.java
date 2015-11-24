/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import ModuloGerencial.Parquimetro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author feliperiffel
 */
public class ParquimetroDAO {
    public static Parquimetro creteNewParquimetro(String endereco){
        
        Parquimetro novoParquimetro = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            //Inserir dados na tabela
            String sql = "insert into Parquimetros(endereco) values(?)";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, endereco);
                if (comando.executeUpdate() > 0) {
                    
                    //Pega o ID do novo registro
                    String sql2 = "select * from Parquimetros order by 1 desc fetch first row only";
                    try (PreparedStatement comando2 = conexao.prepareStatement(sql)) {
                        try (ResultSet resultados = comando.executeQuery()) {
                            while (resultados.next()) {
                                novoParquimetro = new Parquimetro(resultados.getInt("id"), resultados.getString("endereco"));
                            }
                        }
                    }
                    
                    System.out.println("Inserção efetuada com sucesso");
                } else {
                    System.out.println("Falha na inserção");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return novoParquimetro;
    }
    
    public static Parquimetro getParquimetro(int id){
        Parquimetro parquimetro = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            //Consultar dados da tabela
            String sql = "select * from Parquimetros where id = ?";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, id);
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        parquimetro = new Parquimetro(resultados.getInt("id"), resultados.getString("endereco"));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return parquimetro;
    }
    
    public static ArrayList<Parquimetro> getParquimetro(){
        ArrayList<Parquimetro> parquimetros = new ArrayList();
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            //Consultar dados da tabela
            String sql = "select * from Parquimetros";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        parquimetros.add(new Parquimetro(resultados.getInt("id"), resultados.getString("endereco")));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return parquimetros;
    }
}
