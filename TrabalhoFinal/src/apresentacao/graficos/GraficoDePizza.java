package apresentacao.graficos;

import negocio.entidades.Parquimetro;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author Bruno
 */
public class GraficoDePizza implements IGrafico {
   private double valorGeral, valorIsento;
   private DefaultPieDataset dataset;
   private JFreeChart grafico;
   private String titulo;
   
   public GraficoDePizza() {
     this("Grafico de Pizza");
   }
   
   public GraficoDePizza(String umTitulo) {
     this.titulo = umTitulo;
     this.valorGeral = 0.0;
     this.valorIsento = 0.0;
     this.dataset = new DefaultPieDataset();
     Locale locale = Locale.US;
     this.grafico = ChartFactory.createPieChart(titulo, dataset, true, true, locale);
   }
   
   @Override
   public JPanel getPanel() {
      return new ChartPanel(grafico);
   }


    public void setValorGeral(double valor) {
        this.valorGeral = valor;
    }
    
    public void setValorIsento(double valor) {
        this.valorIsento = valor;
    }

    @Override
    public void setTitulo(String umTitulo) {
        this.titulo = umTitulo;
    }
    
    public void geraDataset(LocalDateTime inicio, LocalDateTime fim)
    {
        dataset.setValue("Valor Geral", valorGeral);
        dataset.setValue("Valor Isento", valorIsento);
    }
}