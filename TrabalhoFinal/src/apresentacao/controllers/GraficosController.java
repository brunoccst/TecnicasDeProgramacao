package apresentacao.controllers;

import apresentacao.graficos.GraficoDeBarras;
import apresentacao.views.ViewPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import negocio.entidades.Parquimetro;
import negocio.facades.ParquimetroFacade;

/**
 *
 * @author Bruno
 */
public class GraficosController implements ActionListener {
    private ViewPrincipal viewPrincipal;
    
    public void associaViewPrincipal(ViewPrincipal view)
    {
        viewPrincipal = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewPrincipal.getGerarBarras())
        {
            int ano = viewPrincipal.getDataBarras();
            ArrayList<Parquimetro> parquimetros = ParquimetroFacade.getParquimetros();
            String titulo = "Grafico de valor arrecadado (" + ano + ")";
            GraficoDeBarras ger = new GraficoDeBarras(titulo, "Parquimetro", "Valor");
            ger.setDados(parquimetros);
            ger.geraDataset(ano);
            viewPrincipal.setGraficoDeBarras(ger);
        }
    }
}
