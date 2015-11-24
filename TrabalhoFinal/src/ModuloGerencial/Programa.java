package ModuloGerencial;

import apresentacao.Controller;
import apresentacao.GeradorDeGrafico;
import apresentacao.Seletor_Log;
import apresentacao.View;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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
        
//        Parquimetro p = ParquimetroFacade.criaParquimetro("asd");
//        System.out.println(">"+p.getId()+" "+p.getEndereco());
//        ArrayList<Parquimetro> ps = ParquimetroFacade.getParquimetros();
//        ps.stream().forEach((p1) -> {
//            System.out.println(">>" + p1.getId() + " " + p1.getEndereco());
//        });
//        Parquimetro p = ParquimetroFacade.getParquimetro(1);
//        System.out.println(">"+p);
        
//        p.addTicket(new Ticket((IParquimetro)p, 1, LocalDateTime.now(), LocalDateTime.now()));
//        p.addTicket(new Ticket((IParquimetro)p, 2, LocalDateTime.now(), LocalDateTime.now()));
//        p.addTicket(new Ticket((IParquimetro)p, 3, LocalDateTime.now(), LocalDateTime.now()));
//        p.addTicket(new Ticket((IParquimetro)p, 4, LocalDateTime.of(1015, Month.APRIL, 1, 11, 11), LocalDateTime.of(1015, Month.APRIL, 1, 11, 40)));
//        ArrayList<Ticket> ts = p.getTickets();
//        ts.stream().forEach((t1) -> {
//            System.out.println(">>" + t1.getSerial() + " E: " + t1.getEmissao().toString() + " V: " + t1.getValidade().toString());
//        });
//        Cartao car = CartaoFacade.createCartao(true);
//        System.out.println(car.getID() + " " + car.isResidente());
    }
    
    public static void main(String[] args)
    {
        criarGui();
    }
    
}