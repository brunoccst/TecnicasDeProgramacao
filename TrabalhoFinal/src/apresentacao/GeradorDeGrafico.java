package apresentacao;

import ModuloGerencial.Parquimetro;
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
public class GeradorDeGrafico {
   private ArrayList<Parquimetro> parquimetros;
   private int ano;
   private DefaultCategoryDataset dados;
   private JFreeChart grafico;
   
   public GeradorDeGrafico(ArrayList<Parquimetro> parq, int umAno) {
     this.parquimetros = parq;
     this.ano = umAno;
     this.dados = new DefaultCategoryDataset();
     setDados();
     this.grafico = ChartFactory.createStackedBarChart("Total arrecadado por parquimetro (" + ano +")", 
                           "Parquimetro", "Valor", 
                             dados, 
                          PlotOrientation.VERTICAL, 
                           true, true, false);
   }
   
   public void setDados() {
     for (int i = 0; i < parquimetros.size(); i++) {
       dados.addValue(parquimetros.get(i).getValorTotal(ano), "" + parquimetros.get(i).getId(),Integer.valueOf(i+1));
     }
   }
   
   public JPanel getPanel() {
      return new ChartPanel(grafico);
   }
}