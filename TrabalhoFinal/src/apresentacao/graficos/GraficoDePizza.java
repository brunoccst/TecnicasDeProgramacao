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
   private ArrayList<Parquimetro> parquimetros;
   private DefaultPieDataset dataset;
   private JFreeChart grafico;
   private String titulo;
   
   public GraficoDePizza() {
     this("Grafico de Pizza");
   }
   
   public GraficoDePizza(String umTitulo) {
     this.titulo = umTitulo;
     this.parquimetros = new ArrayList();
     this.dataset = new DefaultPieDataset();
     Locale locale = Locale.US;
     this.grafico = ChartFactory.createPieChart(titulo, dataset, false, false, locale);
   }
   
   @Override
   public JPanel getPanel() {
      return new ChartPanel(grafico);
   }

    @Override
    public void setDados(ArrayList<Parquimetro> dados) {
        this.parquimetros = dados;
    }

    @Override
    public void setTitulo(String umTitulo) {
        this.titulo = umTitulo;
    }
    
    public void geraDataset(LocalDateTime inicio, LocalDateTime fim)
    {
        for (Parquimetro p : parquimetros)
        {
            dataset.setValue("" + p.getId(), p.getValorTotal(inicio, fim));
        }
    }
}