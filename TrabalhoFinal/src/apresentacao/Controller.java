package apresentacao;

import ModuloGerencial.IParquimetro;
import ModuloGerencial.Parquimetro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;

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
            while (!line.contains("<id>")) line = br.readLine();
            String aux = line.substring(line.indexOf('>') + 1, line.lastIndexOf('<'));
            int id = Integer.parseInt(aux);
            
            while (!line.contains("<endereco>")) line = br.readLine();
            aux = line.substring(line.indexOf('>') + 1, line.lastIndexOf('<'));
            String endereco = aux;
            
            IParquimetro parquimetro = new Parquimetro(id, endereco);
            System.out.println(parquimetro.toString());
            
        } finally {
            br.close();
        }
        view.dadosImportados(logs.size());
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
    }
}