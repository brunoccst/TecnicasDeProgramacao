package apresentacao.controllers;

import apresentacao.graficos.GraficoDeBarras;
import apresentacao.graficos.GraficoDePizza;
import apresentacao.views.ViewPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import negocio.FormatadorDeData;
import negocio.entidades.Parquimetro;
import negocio.facades.ParquimetroFacade;

/**
 *
 * @author Bruno
 */
public class GraficosController implements ActionListener {
    private ViewPrincipal viewPrincipal;
    
    public void associaViewPrincipal(ViewPrincipal view)
    {
        viewPrincipal = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewPrincipal.getGerarBarras())
        {
            int ano = viewPrincipal.getAnoBarras();
            ArrayList<Parquimetro> parquimetros = ParquimetroFacade.getParquimetros();
            String titulo = "Grafico de valor arrecadado (" + ano + ")";
            
            GraficoDeBarras graficoDeBarras = new GraficoDeBarras(titulo, "Parquimetro", "Valor");
            graficoDeBarras.setDados(parquimetros);
            graficoDeBarras.geraDataset(ano);
            
            viewPrincipal.setGraficoDeBarras(graficoDeBarras);
        }
        else if (e.getSource() == viewPrincipal.getGerarPizza())
        {
            LocalDateTime dtInicio = FormatadorDeData.FormataDataMesAno(viewPrincipal.getDataInicioPizza(), true);
            LocalDateTime dtFim = FormatadorDeData.FormataDataMesAno(viewPrincipal.getDataFimPizza(), false);
            dtInicio = LocalDateTime.of(dtInicio.getYear(), dtInicio.getMonth(), 1, 0, 0);
            dtFim = LocalDateTime.of(dtFim.getYear(), dtFim.getMonth(), dtFim.getMonth().maxLength(), 23, 59);
            
            String titulo = "Grafico de total arrecadado x total isento (" 
                    + dtInicio.getMonth().getValue() + "/" + dtInicio.getYear() 
                    + " - "
                    + dtFim.getMonth().getValue() + "/" + dtFim.getYear()
                    + ")";
            
            ArrayList<Parquimetro> parquimetros = ParquimetroFacade.getParquimetros();
            GraficoDePizza graficoDePizza = new GraficoDePizza(titulo);
            graficoDePizza.setValorGeral(ParquimetroFacade.getValorTotalGeral(parquimetros));
            graficoDePizza.setValorIsento(ParquimetroFacade.getValorTotalIsento(parquimetros));
            graficoDePizza.geraDataset(dtInicio, dtFim);
            
            viewPrincipal.setGraficoDePizza(graficoDePizza);
        }
    }
}
