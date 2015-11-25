package apresentacao;

import ModuloGerencial.Parquimetro;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Bruno
 */
public class GeradorDePizza {
   private ArrayList<Parquimetro> parquimetros;
   private LocalDateTime inicio, fim;
   private PieDataset dados;
   private JFreeChart grafico;
   
   public GeradorDePizza(ArrayList<Parquimetro> parq, LocalDateTime umInicio, LocalDateTime umFim) {
     this.parquimetros = parq;
     inicio = umInicio;
     fim = umFim;
     this.dados = new PieDataset();
     setDados();
     String legenda = "Percentual de total arrecadados de " 
             + inicio.getMonth() + "/" + inicio.getYear() 
             + " ate " 
             + fim.getMonth() + "/" + fim.getYear();
     this.grafico = ChartFactory.createPieChart(legenda, dados);
   }
   
   public void setDados() {
     for (int i = 0; i < parquimetros.size(); i++) {
       dados.addValue(parquimetros.get(i).getValorTotal(ano), "" + parquimetros.get(i).getId(),Integer.valueOf(i+1));
     }
   }
   
    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("One", new Double(43.2));
        dataset.setValue("Two", new Double(10.0));
        dataset.setValue("Three", new Double(27.5));
        dataset.setValue("Four", new Double(17.5));
        dataset.setValue("Five", new Double(11.0));
        dataset.setValue("Six", new Double(19.4));
        return dataset;        
    }
   public JPanel getPanel() {
      return new ChartPanel(grafico);
   }
}