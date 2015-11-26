/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.dao;

import negocio.entidades.Ticket;
import negocio.interfaces.IParquimetro;
import negocio.interfaces.ICartao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import negocio.interfaces.ITicket;
import persistencia.DBConnection;

/**
 *
 * @author feliperiffel
 */
public class TicketDAO {
    
    public static void createNewTicket(int prquimetroID, int serial, LocalDateTime emissao, LocalDateTime validade, String cartaoID) throws SQLIntegrityConstraintViolationException{
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {
            String sql;
            //Inserir dados na tabela
            if (cartaoID != null)
                sql = "insert into Tickets(parquimetro_id, serial, data_emissao, hora_emissao, data_validade, hora_validade, cartao_id) values(?,?,?,?,?,?,?)";
            else
                sql = "insert into Tickets(parquimetro_id, serial, data_emissao, hora_emissao, data_validade, hora_validade) values(?,?,?,?,?,?)";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, prquimetroID);
                comando.setInt(2, serial);
                comando.setDate(3, Date.valueOf(emissao.toLocalDate()));
                comando.setTime(4, Time.valueOf(emissao.toLocalTime()));
                comando.setDate(5, Date.valueOf(validade.toLocalDate()));
                comando.setTime(6, Time.valueOf(validade.toLocalTime()));
                if (cartaoID != null) comando.setString(7, cartaoID);
                if (comando.executeUpdate() > 0) {
                    System.out.println("Inserção efetuada com sucesso");
                } else {
                    System.out.println("Falha na inserção");
                }
            }
        } 
        catch (SQLIntegrityConstraintViolationException ex)
        {
            throw ex;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static int lastSerial(IParquimetro parquimetro){
        int serialTicket = 0;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {            
            String sql = "select * from Tickets where parquimetro_id = ? order by 1 desc fetch first row only";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, parquimetro.getId());
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        serialTicket = resultados.getInt("serial");
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return serialTicket;
    }
    
    public static ArrayList<ITicket> getTickets(IParquimetro parquimetro, LocalDateTime doDia, LocalDateTime ateDia){
        ArrayList tickets = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {            
            String sql = "select * from Tickets where parquimetro_id = ?";
            
            if (doDia != null && ateDia == null)
                sql = sql + " and data_emissao = ? ";
            else if(doDia != null && ateDia != null)
                sql = sql + " and data_emissao between ? and ? and hora_emissao between ? and ?";
            
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, parquimetro.getId());
                
                if (doDia != null && ateDia == null)
                    comando.setDate(2, Date.valueOf(doDia.toLocalDate()));
                else if(doDia != null && ateDia != null){
                    comando.setDate(2, Date.valueOf(doDia.toLocalDate()));
                    comando.setDate(3, Date.valueOf(ateDia.toLocalDate()));
                    comando.setTime(4, Time.valueOf(doDia.toLocalTime()));
                    comando.setTime(5, Time.valueOf(ateDia.toLocalTime()));
                }
                
                try (ResultSet resultados = comando.executeQuery()) {
                    tickets = new ArrayList();
                    while (resultados.next()) {
                        tickets.add(new Ticket(parquimetro, resultados.getInt("serial"), LocalDateTime.of(resultados.getDate("data_emissao").toLocalDate(), resultados.getTime("hora_emissao").toLocalTime()), LocalDateTime.of(resultados.getDate("data_validade").toLocalDate(), resultados.getTime("hora_validade").toLocalTime()), (ICartao)CartaoDAO.getCartao(resultados.getString("cartao_id"))));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tickets;
    }
}
