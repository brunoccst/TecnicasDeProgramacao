/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import negocio.entidades.*;
import negocio.LeitorDeLog;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import negocio.Organizador;
import negocio.interfaces.*;
/**
 *
 * @author 14104931
 */
public class TesteNegocio {
    
    @Test
    public void leArquivoECriaArray() throws IOException{
        ArrayList<Ticket> tickets;
        LeitorDeLog leitor = new LeitorDeLog();
        File log = new File("H:/Tecnicas/TecnicasDeProgramacao/TrabalhoFinal/Logs/Parquimetro_1.log");
        BufferedReader br = new BufferedReader(new FileReader(log.getPath()));
        tickets = leitor.leLog(log);
        Assert.assertTrue(!tickets.isEmpty());
    }
    
    @Test
    public void leArquivoSemTickets(){
        ArrayList<Ticket> tickets;
        boolean leu = false;
        LeitorDeLog leitor = new LeitorDeLog();
        try{
            File log = new File("H:/Tecnicas/TecnicasDeProgramacao/TrabalhoFinal/test/ParquimetroSemTickets.log");
            BufferedReader br = new BufferedReader(new FileReader(log.getPath()));
            tickets = leitor.leLog(log);
            leu = false;
        }catch(IOException e){
            leu = true;
        }
        Assert.assertTrue(leu);
    }
    
    @Test
    public void organizaArray(){
        boolean organizado = false;
        ArrayList<ITicket> tickets = new ArrayList<>();
        LocalDateTime dt = LocalDateTime.now();
        for(int i = 4; i >= 1; i--) tickets.add(new Ticket(new Parquimetro(i, "Rua A"), i, dt, dt));
        Organizador.OrganizaPorParquimetro(tickets);
        Assert.assertEquals(1, tickets.get(0).getSerial());
        Assert.assertEquals(2, tickets.get(1).getSerial());
        Assert.assertEquals(3, tickets.get(2).getSerial());
        Assert.assertEquals(4, tickets.get(3).getSerial());
    }
}