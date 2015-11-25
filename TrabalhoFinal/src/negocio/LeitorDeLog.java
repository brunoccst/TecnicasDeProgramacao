/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import negocio.entidades.Cartao;
import negocio.interfaces.IParquimetro;
import negocio.entidades.Parquimetro;
import negocio.entidades.Ticket;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import persistencia.dao.CartaoDAO;
import persistencia.dao.ParquimetroDAO;
import persistencia.dao.TicketDAO;

/**
 *
 * @author Bruno
 */
public class LeitorDeLog {
    public static boolean valida(JFileChooser fileChooser)
    {
        return fileChooser.getSelectedFile().getName().endsWith(".log");
    }
    
    public static ArrayList<Ticket> leLog(File log) throws FileNotFoundException, IOException
    {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        BufferedReader br = new BufferedReader(new FileReader(log.getPath()));

        String line = br.readLine();
        
        int id = Integer.parseInt(line);
        IParquimetro parquimetro = ParquimetroDAO.getParquimetro(id);
        
        line = br.readLine();
        while (line.equals(";"))
        {
              line = br.readLine();
              if (line == null || line.equals(";")) break;
              
              int serial = Integer.parseInt(line);
              line = br.readLine();

              DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
              String dataEmissao = line;
              line = br.readLine();
              dataEmissao += " " + line;
              LocalDateTime emissao = LocalDateTime.parse(dataEmissao, formato);
              line = br.readLine();

              String dataValidade = line;
              line = br.readLine();
              dataValidade += " " + line;
              LocalDateTime validade = LocalDateTime.parse(dataValidade, formato);
              line = br.readLine();

              if (!line.equals(";")) //Possui cartão (a proxima linha é o cartão)
              {
                Cartao cartao = CartaoDAO.getCartao(line);
                Ticket novoTicket = new Ticket(parquimetro, serial, emissao, validade, cartao);
                tickets.add(novoTicket);
                line = br.readLine();
              }
              else //Não possui cartão
              {
                Ticket novoTicket = new Ticket(parquimetro, serial, emissao, validade, null);
                tickets.add(novoTicket);
              }
        }
        return tickets;
    }
}
