package apresentacao.graficos;

import apresentacao.graficos.IGrafico;
import negocio.entidades.Parquimetro;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;


/**
 *
 * @author Bruno
 */
public class GraficoDePizza implements IGrafico {
   private ArrayList<Parquimetro> parquimetros;
   private DefaultPieDataset dataset;
   private JFreeChart grafico;
   private String titulo, yAxis, xAxis;
   
   public GraficoDePizza() {
     this("Grafico de Barras", "X", "Y");
   }
   
   public GraficoDePizza(String umTitulo, String umXAxis, String umYAxis) {
     this.titulo = umTitulo;
     this.xAxis = umXAxis;
     this.yAxis = umYAxis;
     this.parquimetros = new ArrayList();
     this.dataset = new DefaultPieDataset();
     this.grafico = ChartFactory.createPieChart(titulo, dataset, false, false, null);
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
    
    public void setXAxis(String umX)
    {
        this.xAxis = umX;
    }
    
    public void setYAxis(String umY)
    {
        this.yAxis = umY;
    }
    
    public void geraDataset(LocalDateTime inicio, LocalDateTime fim)
    {
        for (Parquimetro p : parquimetros)
        {
            dataset.setValue("" + p.getId(), p.getValorTotal(inicio, fim));
        }
    }
}