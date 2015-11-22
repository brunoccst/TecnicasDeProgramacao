package apresentacao;

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
public class GeradorDeGrafico {
   private int[] serie;
   private int comeco;
   private int fim;
   
   private DefaultCategoryDataset dados;
   private JFreeChart grafico;
   
   public GeradorDeGrafico(int[] serie, int comeco, int fim) {
     this.serie = serie;
     this.comeco = comeco;
     this.fim = fim;
     this.dados = new DefaultCategoryDataset();
     plotaMediaMovelSimples();
     this.grafico = ChartFactory.createLineChart("Indicadores", 
                           "Dias", "Valores", 
                             dados, 
                          PlotOrientation.VERTICAL, 
                           true, true, false);
   }
   
   public void plotaMediaMovelSimples() {
     for (int i = comeco; i <= fim; i++) {
       dados.addValue(serie[i], "Dado " + i, Integer.valueOf(i));
     }
   }
   
   public JPanel getPanel() {
      return new ChartPanel(grafico);
   }
}