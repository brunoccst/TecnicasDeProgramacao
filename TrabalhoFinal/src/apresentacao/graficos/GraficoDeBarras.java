package apresentacao.graficos;

import negocio.entidades.Parquimetro;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Bruno
 */
public class GraficoDeBarras implements IGrafico {
   private ArrayList<Parquimetro> parquimetros;
   private DefaultCategoryDataset dataset;
   private JFreeChart grafico;
   private String titulo, yAxis, xAxis;
   
   public GraficoDeBarras() {
     this("Grafico de Barras", "X", "Y");
   }
   
   public GraficoDeBarras(String umTitulo, String umXAxis, String umYAxis) {
     this.titulo = umTitulo;
     this.xAxis = umXAxis;
     this.yAxis = umYAxis;
     this.parquimetros = new ArrayList();
     this.dataset = new DefaultCategoryDataset();
     this.grafico = ChartFactory.createStackedBarChart(titulo, xAxis, yAxis, dataset,
             PlotOrientation.VERTICAL, true, true, false);
   }
   
   @Override
   public JPanel getPanel() {
      return new ChartPanel(grafico);
   }

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
    
    public void geraDataset(int ano)
    {
        for (Parquimetro p : parquimetros)
        {
            dataset.addValue(p.getValorTotal(ano), "" + p.getId(), "" + p.getId());
        }
    }
}