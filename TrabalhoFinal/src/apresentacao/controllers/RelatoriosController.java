package apresentacao.controllers;

import apresentacao.views.ViewPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import negocio.FormatadorDeData;
import negocio.Organizador;
import negocio.entidades.Parquimetro;
import negocio.entidades.Ticket;
import negocio.facades.ParquimetroFacade;

/**
 *
 * @author Bruno
 */
public class RelatoriosController implements ActionListener {
    private ViewPrincipal viewPrincipal;
    private double[] valorPorMes; 
    public void associaViewPrincipal(ViewPrincipal view)
    {
        viewPrincipal = view;
        valorPorMes = new double[12];
        atualizaParquimetrosDaView();
    }
    
    private void atualizaParquimetrosDaView()
    {
        List<String> ids = new ArrayList<>();
        List<Parquimetro> parquimetros = ParquimetroFacade.getParquimetros();
        for (Parquimetro p : parquimetros) ids.add("" + p.getId());
        viewPrincipal.setListaDeParquimetros(ids);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewPrincipal.getBotaoAtualizarLista())
        {
            atualizaParquimetrosDaView();
            viewPrincipal.escreveNoRelatorioIndividual("Lista de parquimetros atualizada.");

        }
        else if (e.getSource() == viewPrincipal.getBotaoGerarRelatorioGeral())
        {
            //Gerar relatório com todos os dados do log dos parquímetros 
            //filtrados por dia específico ou mês;

            LocalDateTime dataSelecionada = FormatadorDeData.FormataDataDiaMesAno(viewPrincipal.getDataRelatorioGeral());
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
            if (tickets.isEmpty())
            {
                viewPrincipal.escreveNoRelatorioGeral("Não foram encontrados registros para esta data.");
                return;
            }
            Organizador.OrganizaPorParquimetro(tickets);
            viewPrincipal.setRelatorioGeral(tickets);
            
        }
        else if (e.getSource() == viewPrincipal.getBotaoGerarRelatorioIndividual())
        {
            //Gerar relatório de total de valor arrecadado, total de valor isento, filtrados por número do parquímetro e
            //agrupados por mês ou ano;
            
            int parquimetroId = viewPrincipal.getParquimetroSelecionadoRelatorio();
            Parquimetro parquimetro = ParquimetroFacade.getParquimetro(parquimetroId);


            for (Ticket t : parquimetro.getTickets()){
                int mes = t.getEmissao().getMonthValue() - 1;
                valorPorMes[mes] += t.getValor();
            }
            viewPrincipal.setRelatorioValores(valorPorMes, parquimetro);
        }
    }
    
}
