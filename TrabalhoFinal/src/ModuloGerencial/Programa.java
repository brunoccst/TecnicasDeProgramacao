package ModuloGerencial;

import apresentacao.Controller;
import apresentacao.GeradorDeGrafico;
import apresentacao.Seletor_Log;
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

    private static void criarGui() {
        View v = new View();
        Seletor_Log sel = new Seletor_Log();
        Controller c = new Controller();
        c.associaView(v);
        c.associaSeletorLog(sel);
        v.associaController(c);
        sel.associaController(c);
        v.mostra();
    }
    
    public static void main(String[] args)
    {
        criarGui();
    }
    
}