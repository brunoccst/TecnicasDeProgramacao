package apresentacao;

import ModuloGerencial.IParquimetro;
import ModuloGerencial.Ticket;
import ModuloGerencial.TicketFacade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import persistencia.ParquimetroDAO;
import persistencia.TicketDAO;

/**
 *
 * @author Bruno
 */
public class Controller implements ActionListener {
    private View view;
    private Seletor_Log seletorLog;
    private ArrayList<File> logs;
    private ArrayList<IParquimetro> parquimetros;
    
    public Controller()
    {
        logs = new ArrayList<File>();
        parquimetros = new ArrayList<IParquimetro>();
    }
    
    public void associaView(View v)
    {
        view = v;
    }
    
    public void associaSeletorLog(Seletor_Log sel)
    {
        seletorLog = sel;
    }
    
    public boolean validaLog(File log) throws FileNotFoundException
    {
        if (!log.getPath().endsWith(".log")) return false;
        return true;
    }
    
    public void importaDados(File log) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(log.getPath()));
        try {
            String line = br.readLine();
            int id = Integer.parseInt(line);
            IParquimetro parquimetro = ParquimetroDAO.getParquimetro(id);
            line = br.readLine();
            while (line.equals(";"))
            {
                  line = br.readLine();
                  if (line == ";" || line == null) break;
                  int serial = Integer.parseInt(line);

                  DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                  line = br.readLine();
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
                  if (!line.equals(";")) 
                  {
                    TicketDAO.creteNewTicket(id, serial, emissao, validade, line);
                    line = br.readLine();
                  }
                  else
                  {
                    TicketDAO.creteNewTicket(id, serial, emissao, validade, null);
                  }
            }
            view.dadosImportados(logs.size());
        }
        finally 
        {
            br.close();
        }
    }
    
    public void manageAddLog(ActionEvent e)
    {
        JFileChooser fileChooser = (JFileChooser) e.getSource();
            if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())) 
            {
                File log = fileChooser.getSelectedFile();
                try {
                    if (validaLog(log))
                    {
                        logs.add(log);
                        view.adicionaLog(log.getPath());   
                    }
                    else
                    {
                        view.arquivoInvalido(); 
                    }
                }
                catch (FileNotFoundException err)
                {
                    view.arquivoNaoEncontrado();
                }
            } 
            seletorLog.setVisible(false);
    }
    
    public void manageImportaDados(ActionEvent e)
    {
         for (File log : logs)
         {
                try {
                    importaDados(log);
                } catch (IOException ex) {
                    view.erroDeES();
                }
         }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seletorLog.getSeletor())
        {
            manageAddLog(e);
        }
        else if (e.getSource() == view.getImportarLogging())
        {
            seletorLog.mostra();
        }
        else if (e.getSource()== view.getInserirDados())
        {
            manageImportaDados(e);
        }
        else if (e.getSource()== view.getGerarRelatorio())
        {
            int parquimetroId = view.getParquimetroSelecionado();
            IParquimetro parquimetro = ParquimetroDAO.getParquimetro(parquimetroId);
            LocalDateTime data = view.getDataRelatorio();
            ArrayList<Ticket> tickets = TicketDAO.getTickets(parquimetro, data, data.plusYears(1));
            view.setRelatorio(tickets);
            
        }
    }
}