/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.controllers;

import negocio.entidades.Ticket;
import negocio.LeitorDeLog;
import apresentacao.views.SeletorDeLog;
import apresentacao.views.ViewPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import persistencia.dao.TicketDAO;

/**
 *
 * @author Bruno
 */
public class ImportarInserirController implements ActionListener {
    private ViewPrincipal viewPrincipal;
    private SeletorDeLog seletorDeLog;
    private ArrayList<File> logsEmMemoria;
    
    public ImportarInserirController() 
    { 
        logsEmMemoria = new ArrayList<File>();
    }
    
    public void associaViewPrincipal(ViewPrincipal view)
    {
        viewPrincipal = view;
    }
    
    public void associaSeletorDeLog(SeletorDeLog sel)
    {
        seletorDeLog = sel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewPrincipal.getImportarLogging())
        {
            seletorDeLog.mostra();
        }
        else if (e.getSource() == seletorDeLog.getSeletor())
        {
            if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand()))
            {
                JFileChooser fileChooser = (JFileChooser) e.getSource();
                if (LeitorDeLog.valida(fileChooser)) 
                {
                    logsEmMemoria.add(fileChooser.getSelectedFile());
                    viewPrincipal.escreveNoConsole(viewPrincipal.getTextoDoConsole() 
                            + "\n"
                            + "Arquivo '"
                            + fileChooser.getSelectedFile().getName()
                            + "' adicionado com sucesso.");
                }
                else viewPrincipal.escreveNoConsole("Erro ao carregar o arquivo. Verifique se o formato é '.log'.");
            }
            seletorDeLog.esconde();
        }
        else if (e.getSource() == viewPrincipal.getInserir())
        {
            viewPrincipal.escreveNoConsole("");
            for (File log : logsEmMemoria)
            {
                viewPrincipal.escreveNoConsole(viewPrincipal.getTextoDoConsole()
                            + "\n"
                            + "Inserindo dados do arquivo '"
                            + log.getName()
                            + "'");
                try
                {
                    ArrayList<Ticket> ticketsLidos = LeitorDeLog.leLog(log);
                    for (Ticket tic : ticketsLidos)
                    {
                        String cartaoID = tic.getCartao() != null ? tic.getCartao().getID() : null;
                        try 
                        {
                            TicketDAO.creteNewTicket(tic.getParquimetro().getId(), tic.getSerial(), tic.getEmissao(), tic.getValidade(), cartaoID);
                        }
                        catch (SQLIntegrityConstraintViolationException ex)
                        {
                            viewPrincipal.escreveNoConsole(viewPrincipal.getTextoDoConsole()
                            + "\n"
                            + "Ticket " + tic.getSerial()
                            + " já existente no banco de dados.");
                        }
                    }
                    viewPrincipal.escreveNoConsole(viewPrincipal.getTextoDoConsole() + "\n");
                }
                catch (FileNotFoundException ex)
                {
                     viewPrincipal.escreveNoConsole(viewPrincipal.getTextoDoConsole()
                            + "\n"
                            + "Erro ao adicionar '"
                            + log.getName()
                            + "'. O arquivo não existe.");
                }
                catch (IOException ex)
                {
                    viewPrincipal.escreveNoConsole(viewPrincipal.getTextoDoConsole()
                            + "\n"
                            + "Erro ao adicionar '"
                            + log.getName()
                            + "'. O log está num formato errado.");
                }

            }
        }
    }
    
}
