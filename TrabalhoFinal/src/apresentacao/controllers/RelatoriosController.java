package apresentacao.controllers;

import apresentacao.views.ViewPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import negocio.entidades.Parquimetro;
import negocio.entidades.Ticket;
import negocio.facades.ParquimetroFacade;

/**
 *
 * @author Bruno
 */
public class RelatoriosController implements ActionListener {
    private ViewPrincipal viewPrincipal;
    
    public void associaViewPrincipal(ViewPrincipal view)
    {
        viewPrincipal = view;
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
        }
        if (e.getSource() == viewPrincipal.getGerarRelatorio())
        {
            String data = viewPrincipal.getDataRelatorio();
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
            
            int parquimetroId = viewPrincipal.getParquimetroSelecionado();
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
            viewPrincipal.setRelatorioGeral(tickets);
            }
            else 
            {
                //Gerar relatório de total de valor arrecadado, total de valor isento, filtrados por número do parquímetro e
                //agrupados por mês ou ano;
                Parquimetro parquimetro = ParquimetroFacade.getParquimetro(parquimetroId);
                ArrayList<Double> mes = new ArrayList();
                for (int i = 0; i < 12; i++)mes.add((Double)0.0);
                for (Ticket t : parquimetro.getTickets()){
                    int ind = t.getEmissao().getMonthValue() - 1;
                    mes.set(ind, mes.get(ind) + t.getValor());
                }
                viewPrincipal.setRelatorioValores(mes, parquimetro);
            }
        }
    }
    
}
