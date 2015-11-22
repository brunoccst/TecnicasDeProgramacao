/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import ModuloGerencial.Ticket;
import ModuloGerencial.IParquimetro;
import ModuloGerencial.Cartao;
import ModuloGerencial.ICartao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author feliperiffel
 */
public class TicketDAO {
    
    public static void creteNewTicket(int prquimetroID, int serial, LocalDateTime emissao, LocalDateTime validade, String cartaoID){
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
                comando.setString(3, emissao.getYear() + "-" + emissao.getMonthValue() + "-" + emissao.getDayOfMonth());
                comando.setString(4, emissao.getHour() + ":" + emissao.getMinute() + ":" + emissao.getSecond());
                comando.setString(5, validade.getYear() + "-" + validade.getMonthValue() + "-" + validade.getDayOfMonth());
                comando.setString(6, validade.getHour() + ":" + validade.getMinute() + ":" + validade.getSecond());
                if (cartaoID != null) comando.setString(7, cartaoID);
                if (comando.executeUpdate() > 0) {
                    System.out.println("Inserção efetuada com sucesso");
                } else {
                    System.out.println("Falha na inserção");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static ArrayList<Ticket> getTickets(IParquimetro parquimetro, LocalDateTime doDia, LocalDateTime ateDia){
        ArrayList tickets = null;
        try (Connection conexao = DBConnection.getConexaoViaDriverManager()) {            
            String sql = "select * from Tickets where parquimetro_id = ?";
            
            if (doDia != null && ateDia == null)
                sql = sql + " and data_emissao = DATE(?) ";
            else if(doDia != null && ateDia != null)
                sql = sql + " and data_emissao between DATE(?) and DATE(?) and hora_emissao between TIME(?) and TIME(?)";
            
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setInt(1, parquimetro.getId());
                
                if (doDia != null && ateDia == null)
                    comando.setString(2, doDia.getYear() + "-" + doDia.getMonthValue() + "-" + doDia.getDayOfMonth());
                else if(doDia != null && ateDia != null){
                    comando.setString(2, doDia.getYear() + "-" + doDia.getMonthValue() + "-" + doDia.getDayOfMonth());
                    comando.setString(3, ateDia.getYear() + "-" + ateDia.getMonthValue() + "-" + ateDia.getDayOfMonth());
                    comando.setString(4, doDia.getHour() + ":" + doDia.getMinute() + ":" + doDia.getSecond());
                    comando.setString(5, ateDia.getHour() + ":" + ateDia.getMinute() + ":" + ateDia.getSecond());
                }
                
                try (ResultSet resultados = comando.executeQuery()) {
                    tickets = new ArrayList();
                    while (resultados.next()) {
                        tickets.add(new Ticket(parquimetro, resultados.getInt("serial"), LocalDateTime.now(), LocalDateTime.now(), (ICartao)CartaoDAO.getCartao(resultados.getString("cartao_id"))));
                        //NAO SEI PEGAR A MERDA DA DATA E HORA DEVOLTA.
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tickets;
    }
}
