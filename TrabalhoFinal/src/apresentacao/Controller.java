package apresentacao;

import ModuloGerencial.IParquimetro;
import ModuloGerencial.Parquimetro;
import ModuloGerencial.ParquimetroFacade;
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
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        view.setParquimetros(ParquimetroDAO.getParquimetro());
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
            String data = view.getDataRelatorio();
            LocalDateTime dataSelecionada = LocalDateTime.now();
            if (!data.equals("")) 
            {
                String[] dt = data.split("/");
                int dia = Integer.parseInt(dt[0]);
                int mes = Integer.parseInt(dt[1]);
                int ano = Integer.parseInt(dt[2]);
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataSelecionada = LocalDateTime.of(ano, Month.of(mes), dia, 0, 0);
            }
            
            int parquimetroId = view.getParquimetroSelecionado();
            if (parquimetroId == 0)
            {
                //Gerar relatório com todos os dados do log dos parquímetros filtrados por dia específico ou mês;
                ArrayList<Parquimetro> parq = ParquimetroFacade.getParquimetros();
                ArrayList<Ticket> tickets = new ArrayList<>();
                for (Parquimetro p : parq)
                {
                    ArrayList<Ticket> ticketsP = p.getTickets();
                    for (Ticket t : ticketsP)
                    {
                        if ((t.getEmissao().getMonth().compareTo(dataSelecionada.getMonth()) == 0)
                                && t.getEmissao().getDayOfMonth() == dataSelecionada.getDayOfMonth())
                        {
                            tickets.add(t);
                        }
                    }
                }
                
            //Sorting
            Collections.sort(tickets, new Comparator<Ticket>() {

                    @Override
                    public int compare(Ticket o1, Ticket o2) {
                        return Math.min(o1.getParquimetro().getId(), o2.getParquimetro().getId());
                    }

            });
            view.setRelatorioGeral(tickets);
            }
            else 
            {
                //Gerar relatório de total de valor arrecadado, total de valor isento, filtrados por número do parquímetro e
                //agrupados por mês ou ano;
                Parquimetro parquimetro = ParquimetroFacade.getParquimetro(parquimetroId);
                ArrayList<Double> mes = new ArrayList(12);
                for (Ticket t : parquimetro.getTickets()){
                    int ind = t.getEmissao().getMonthValue() - 1;
                    mes.set(ind, mes.get(ind) + t.getValor());
                }
                view.setRelatorioValores(mes, parquimetro);
            }
        }
    }
}