package ModuloGerencial;

import apresentacao.GeradorDeGrafico;
import apresentacao.View;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import persistencia.CartaoDAO;

/**
 *
 * @author Bruno
 */
public class Programa {

    public static void main(String[] args)
    {
        CartaoDAO cDao = new CartaoDAO();
        Cartao c = cDao.creteNewCartao(true);
        System.out.println(c.getID());
        
//        View view = new View();
//        view.setVisible(true);
    }
    
}