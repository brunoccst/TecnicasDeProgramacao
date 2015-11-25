package negocio;

import apresentacao.controllers.GraficosController;
import apresentacao.controllers.ImportarInserirController;
import apresentacao.controllers.RelatoriosController;
import apresentacao.views.SeletorDeLog;
import apresentacao.views.ViewPrincipal;

/**
 *
 * @author Bruno
 */
public class Programa {

    private static void criarGui() {
        //Controllers
        ImportarInserirController impController = new ImportarInserirController();
        RelatoriosController relController = new RelatoriosController();
        GraficosController graController = new GraficosController();

        //Views
        ViewPrincipal view = new ViewPrincipal();
        SeletorDeLog seletor = new SeletorDeLog();
        
        //Associa os listeners
        view.associaImportarInserir(impController);
        seletor.associaController(impController);
        impController.associaViewPrincipal(view);
        impController.associaSeletorDeLog(seletor);
        
        view.associaRelatorios(relController);
        relController.associaViewPrincipal(view);
        
        view.associaGraficos(graController);
        graController.associaViewPrincipal(view);
        
        //Inicia
        view.mostra();
    }
    
    public static void main(String[] args)
    {
        criarGui();
    }
    
}