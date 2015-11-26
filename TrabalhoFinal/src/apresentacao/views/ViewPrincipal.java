/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.views;

import apresentacao.controllers.ImportarInserirController;
import apresentacao.controllers.GraficosController;
import apresentacao.controllers.RelatoriosController;
import apresentacao.graficos.GraficoDeBarras;
import apresentacao.graficos.GraficoDePizza;
import apresentacao.graficos.IGrafico;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import negocio.entidades.Parquimetro;
import negocio.entidades.Ticket;

/**
 *
 * @author Bruno
 */
public class ViewPrincipal extends javax.swing.JFrame {
    private IGrafico graficoDeBarras;
    private IGrafico graficoDePizza;
 
    public ViewPrincipal() {
        initComponents();
    }
    
    public void mostra()
    {
        setVisible(true);
    }
    
    public void associaImportarInserir(ImportarInserirController c)
    {
        btn_importar.addActionListener(c);
        btn_inserir.addActionListener(c);
    }
    
    public void associaRelatorios(RelatoriosController c)
    {
        btn_gerarRelatorioGeral.addActionListener(c);
        btn_gerarRelatorioIndividual.addActionListener(c);
        btn_atualizarLista.addActionListener(c);
    }
    
    public void associaGraficos(GraficosController c)
    {
        btn_gerarBarras.addActionListener(c);
        btn_gerarPizza.addActionListener(c);
    }

    
    ////////////////////TAB: Importar/Inserir
    public JButton getImportar()
    {
        return btn_importar;
    }
    
    public JButton getInserir()
    {
        return btn_inserir;
    }
    
    public String getTextoDoConsole()
    {
        return console.getText();
    }
    
    public void escreveNoConsole(String texto)
    {
        console.setText(texto);
    }
    ////////////////////
    
    ////////////////////TAB: Relatorios
    ////////Subtab: Geral
    public void escreveNoRelatorioGeral(String mensagem)
    {
        textArea_relatorioGeral.setText(mensagem);
    }
    
    public String getDataRelatorioGeral()
    {
        return box_dataGeral.getText();
    }
    
    public JButton getBotaoGerarRelatorioGeral()
    {
        return btn_gerarRelatorioGeral;
    }
    ////////
    
    ////////Subtab: Individual
    public void escreveNoRelatorioIndividual(String mensagem)
    {
        textArea_relatorioIndividual.setText(mensagem);
    }
        
    public void setListaDeParquimetros(List<String> ids)
    {
        dropdown_parquimetro.removeAllItems();
        for (String id : ids)
        {
            dropdown_parquimetro.addItem(id);
        }
    }
        
    public int getParquimetroSelecionadoRelatorio()
    {
        return Integer.parseInt((String) dropdown_parquimetro.getSelectedItem());
    }
    
    public JButton getBotaoGerarRelatorioIndividual()
    {
        return btn_gerarRelatorioIndividual;
    }
    
    public JButton getBotaoAtualizarLista()
    {
        return btn_atualizarLista;
    }
    ////////
    ////////////////////
    
    ////////////////////TAB: Graficos
    
    //////Subtab: Barras
    public void setGraficoDeBarras(GraficoDeBarras g)
    {
        graficoDeBarras = g;
        panel_barras.removeAll();
        JPanel grafico = graficoDeBarras.getPanel();
        panel_barras.add(grafico, BorderLayout.CENTER);
        panel_barras.validate();
    }
    
    public int getAnoBarras()
    {
        String dt = box_ano.getText();
        if (dt.equals("")) return LocalDateTime.now().getYear();
        return Integer.parseInt(dt);
    }
    
    public JButton getGerarBarras()
    {
        return btn_gerarBarras;
    }
    //////
    
    //////Subtab: Pizza
    public void setGraficoDePizza(GraficoDePizza p)
    {
        graficoDePizza = p;
        panel_pizza.removeAll();
        JPanel grafico = graficoDePizza.getPanel();
        panel_pizza.add(grafico, BorderLayout.CENTER);
        panel_pizza.validate();
    }
    
    public String getDataInicioPizza()
    {
        return box_mesAnoInicio.getText();
    }
    
    public String getDataFimPizza()
    {
        return box_mesAnoFim.getText();
    }
    
    public JButton getGerarPizza()
    {
        return btn_gerarPizza;
    }
    //////
    ////////////////////
    

    
    public void adicionaLog(String path)
    {
        console.setText(console.getText()
        + "\n"
        + "Adicionado arquivo: '"
        + path
        + "'");
    }
    
    public void dadosImportados(int qtdArquivos)
    {
        console.setText(qtdArquivos + " arquivos importados."); 
    }
    
    public void arquivoNaoEncontrado()
    {
        console.setText(console.getText()
        + "\n"
        + "Arquivo não encontrado"
        );
    }
   
    
    public void erroDeES()
    {
        console.setText(console.getText()
        + "\n"
        + "Erro de E/S"
        );
    }
    
    public void arquivoInvalido()
    {
        console.setText(console.getText()
        + "\n"
        + "Arquivo inválido"
        );
    }
    
    public void setRelatorioGeral(ArrayList<Ticket> tickets)
    {
        StringBuilder sb = new StringBuilder();
        LocalDateTime dataAtual = tickets.get(0).getEmissao();
        int parquimetroAtual = tickets.get(0).getParquimetro().getId();
        
        sb.append(dataAtual.getDayOfMonth() + "/" + dataAtual.getMonth() + "/" + dataAtual.getYear() + "\n");
        sb.append("[" + parquimetroAtual + "]\n\n");
        
        for (Ticket t : tickets)
        {
            if (t.getParquimetro().getId() > parquimetroAtual) 
            {
                parquimetroAtual = t.getParquimetro().getId();
                sb.append("\n[" + parquimetroAtual + "]\n");
            }
            sb.append(t.getSerial() + " || " + t.getEmissao() + " - " + t.getValidade() + "\n");
        }
        textArea_relatorioGeral.setText(sb.toString());
    }
    
    public void setRelatorioValores(double[] valorPorMes, Parquimetro p){
        String[] mstr = {"JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"};
        StringBuilder sb = new StringBuilder();
        sb.append("Parquimetro " + p.getId() + "\n");
        sb.append("--------------------------\n\n");
        for(int i = 0; i < 12; i++){
            sb.append("Mes: " + mstr[i] + "______ Total: " + valorPorMes[i] + "\n");
        }
        textArea_relatorioIndividual.setText(sb.toString());
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        moduloGerencial = new javax.swing.JTabbedPane();
        tab_importarInserir = new javax.swing.JPanel();
        btn_importar = new javax.swing.JButton();
        btn_inserir = new javax.swing.JButton();
        panel_importarInserir = new javax.swing.JScrollPane();
        console = new javax.swing.JTextPane();
        tab_relatorios = new javax.swing.JPanel();
        tab_relatorios_content = new javax.swing.JTabbedPane();
        tab_relatorioGeral = new javax.swing.JPanel();
        lbl_data = new javax.swing.JLabel();
        box_dataGeral = new javax.swing.JFormattedTextField();
        btn_gerarRelatorioGeral = new javax.swing.JButton();
        panel_relatorioGeral = new javax.swing.JScrollPane();
        textArea_relatorioGeral = new javax.swing.JTextArea();
        tab_relatorioIndividual = new javax.swing.JPanel();
        lbl_parquimetro = new javax.swing.JLabel();
        dropdown_parquimetro = new javax.swing.JComboBox<>();
        btn_gerarRelatorioIndividual = new javax.swing.JButton();
        btn_atualizarLista = new javax.swing.JButton();
        panel_relatorioIndividual = new javax.swing.JScrollPane();
        textArea_relatorioIndividual = new javax.swing.JTextArea();
        tab_graficos = new javax.swing.JPanel();
        tab_graficos_content = new javax.swing.JTabbedPane();
        tab_barras = new javax.swing.JPanel();
        lbl_ano = new javax.swing.JLabel();
        box_ano = new javax.swing.JFormattedTextField();
        panel_barras = new java.awt.Panel();
        btn_gerarBarras = new javax.swing.JButton();
        tab_pizza = new javax.swing.JPanel();
        lbl_mesAnoIncio = new javax.swing.JLabel();
        box_mesAnoInicio = new javax.swing.JFormattedTextField();
        lbl_mesAnoFim = new javax.swing.JLabel();
        box_mesAnoFim = new javax.swing.JFormattedTextField();
        btn_gerarPizza = new javax.swing.JButton();
        panel_pizza = new java.awt.Panel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_importar.setText("Importar arquivos de logging");

        btn_inserir.setText("Inserir dados importados");

        console.setEditable(false);
        panel_importarInserir.setViewportView(console);

        javax.swing.GroupLayout tab_importarInserirLayout = new javax.swing.GroupLayout(tab_importarInserir);
        tab_importarInserir.setLayout(tab_importarInserirLayout);
        tab_importarInserirLayout.setHorizontalGroup(
            tab_importarInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_importarInserirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_importarInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_importarInserir)
                    .addGroup(tab_importarInserirLayout.createSequentialGroup()
                        .addComponent(btn_importar, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        tab_importarInserirLayout.setVerticalGroup(
            tab_importarInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_importarInserirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_importarInserirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_importar)
                    .addComponent(btn_inserir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_importarInserir, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );

        moduloGerencial.addTab("Importar/Inserir", tab_importarInserir);

        lbl_data.setText("Data");

        box_dataGeral.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        btn_gerarRelatorioGeral.setText("Gerar");

        textArea_relatorioGeral.setEditable(false);
        textArea_relatorioGeral.setColumns(20);
        textArea_relatorioGeral.setRows(5);
        panel_relatorioGeral.setViewportView(textArea_relatorioGeral);

        javax.swing.GroupLayout tab_relatorioGeralLayout = new javax.swing.GroupLayout(tab_relatorioGeral);
        tab_relatorioGeral.setLayout(tab_relatorioGeralLayout);
        tab_relatorioGeralLayout.setHorizontalGroup(
            tab_relatorioGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatorioGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_relatorioGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_relatorioGeralLayout.createSequentialGroup()
                        .addComponent(lbl_data)
                        .addGap(18, 18, 18)
                        .addComponent(box_dataGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarRelatorioGeral)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panel_relatorioGeral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab_relatorioGeralLayout.setVerticalGroup(
            tab_relatorioGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatorioGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_relatorioGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_data)
                    .addComponent(box_dataGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarRelatorioGeral))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_relatorioGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab_relatorios_content.addTab("Geral", tab_relatorioGeral);

        lbl_parquimetro.setText("Parquímetro");

        dropdown_parquimetro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_gerarRelatorioIndividual.setText("Gerar");

        btn_atualizarLista.setText("Atualizar lista");

        textArea_relatorioIndividual.setEditable(false);
        textArea_relatorioIndividual.setColumns(20);
        textArea_relatorioIndividual.setRows(5);
        panel_relatorioIndividual.setViewportView(textArea_relatorioIndividual);

        javax.swing.GroupLayout tab_relatorioIndividualLayout = new javax.swing.GroupLayout(tab_relatorioIndividual);
        tab_relatorioIndividual.setLayout(tab_relatorioIndividualLayout);
        tab_relatorioIndividualLayout.setHorizontalGroup(
            tab_relatorioIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatorioIndividualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_relatorioIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_relatorioIndividualLayout.createSequentialGroup()
                        .addComponent(lbl_parquimetro)
                        .addGap(18, 18, 18)
                        .addComponent(dropdown_parquimetro, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarRelatorioIndividual)
                        .addGap(18, 18, 18)
                        .addComponent(btn_atualizarLista)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addComponent(panel_relatorioIndividual))
                .addContainerGap())
        );
        tab_relatorioIndividualLayout.setVerticalGroup(
            tab_relatorioIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatorioIndividualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_relatorioIndividualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_parquimetro)
                    .addComponent(dropdown_parquimetro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarRelatorioIndividual)
                    .addComponent(btn_atualizarLista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_relatorioIndividual, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_relatorios_content.addTab("Individual", tab_relatorioIndividual);

        javax.swing.GroupLayout tab_relatoriosLayout = new javax.swing.GroupLayout(tab_relatorios);
        tab_relatorios.setLayout(tab_relatoriosLayout);
        tab_relatoriosLayout.setHorizontalGroup(
            tab_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatoriosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tab_relatorios_content, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tab_relatoriosLayout.setVerticalGroup(
            tab_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatoriosLayout.createSequentialGroup()
                .addComponent(tab_relatorios_content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        moduloGerencial.addTab("Relatórios", tab_relatorios);

        lbl_ano.setText("Ano");

        box_ano.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy"))));

        panel_barras.setLayout(new java.awt.BorderLayout());

        btn_gerarBarras.setText("Gerar");

        javax.swing.GroupLayout tab_barrasLayout = new javax.swing.GroupLayout(tab_barras);
        tab_barras.setLayout(tab_barrasLayout);
        tab_barrasLayout.setHorizontalGroup(
            tab_barrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_barrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_barrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_barras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab_barrasLayout.createSequentialGroup()
                        .addComponent(lbl_ano)
                        .addGap(18, 18, 18)
                        .addComponent(box_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarBarras)
                        .addGap(0, 343, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tab_barrasLayout.setVerticalGroup(
            tab_barrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_barrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_barrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ano)
                    .addComponent(box_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarBarras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_barras, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_graficos_content.addTab("Barras", tab_barras);

        lbl_mesAnoIncio.setText("Mês/Ano Inicio");

        box_mesAnoInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/yyyy"))));

        lbl_mesAnoFim.setText("Mês/Ano Fim");

        box_mesAnoFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/yyyy"))));

        btn_gerarPizza.setText("Gerar");

        panel_pizza.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout tab_pizzaLayout = new javax.swing.GroupLayout(tab_pizza);
        tab_pizza.setLayout(tab_pizzaLayout);
        tab_pizzaLayout.setHorizontalGroup(
            tab_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_pizzaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab_pizzaLayout.createSequentialGroup()
                        .addComponent(lbl_mesAnoIncio)
                        .addGap(18, 18, 18)
                        .addComponent(box_mesAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_mesAnoFim)
                        .addGap(18, 18, 18)
                        .addComponent(box_mesAnoFim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarPizza)
                        .addGap(0, 134, Short.MAX_VALUE))
                    .addComponent(panel_pizza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab_pizzaLayout.setVerticalGroup(
            tab_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_pizzaLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(tab_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_mesAnoInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_mesAnoIncio)
                    .addComponent(lbl_mesAnoFim)
                    .addComponent(box_mesAnoFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarPizza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_pizza, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab_graficos_content.addTab("Pizza", tab_pizza);

        javax.swing.GroupLayout tab_graficosLayout = new javax.swing.GroupLayout(tab_graficos);
        tab_graficos.setLayout(tab_graficosLayout);
        tab_graficosLayout.setHorizontalGroup(
            tab_graficosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_graficos_content)
        );
        tab_graficosLayout.setVerticalGroup(
            tab_graficosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab_graficos_content)
        );

        moduloGerencial.addTab("Gráficos", tab_graficos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(moduloGerencial)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moduloGerencial)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField box_ano;
    private javax.swing.JFormattedTextField box_dataGeral;
    private javax.swing.JFormattedTextField box_mesAnoFim;
    private javax.swing.JFormattedTextField box_mesAnoInicio;
    private javax.swing.JButton btn_atualizarLista;
    private javax.swing.JButton btn_gerarBarras;
    private javax.swing.JButton btn_gerarPizza;
    private javax.swing.JButton btn_gerarRelatorioGeral;
    private javax.swing.JButton btn_gerarRelatorioIndividual;
    private javax.swing.JButton btn_importar;
    private javax.swing.JButton btn_inserir;
    private javax.swing.JTextPane console;
    private javax.swing.JComboBox<String> dropdown_parquimetro;
    private javax.swing.JLabel lbl_ano;
    private javax.swing.JLabel lbl_data;
    private javax.swing.JLabel lbl_mesAnoFim;
    private javax.swing.JLabel lbl_mesAnoIncio;
    private javax.swing.JLabel lbl_parquimetro;
    private javax.swing.JTabbedPane moduloGerencial;
    private java.awt.Panel panel_barras;
    private javax.swing.JScrollPane panel_importarInserir;
    private java.awt.Panel panel_pizza;
    private javax.swing.JScrollPane panel_relatorioGeral;
    private javax.swing.JScrollPane panel_relatorioIndividual;
    private javax.swing.JPanel tab_barras;
    private javax.swing.JPanel tab_graficos;
    private javax.swing.JTabbedPane tab_graficos_content;
    private javax.swing.JPanel tab_importarInserir;
    private javax.swing.JPanel tab_pizza;
    private javax.swing.JPanel tab_relatorioGeral;
    private javax.swing.JPanel tab_relatorioIndividual;
    private javax.swing.JPanel tab_relatorios;
    private javax.swing.JTabbedPane tab_relatorios_content;
    private javax.swing.JTextArea textArea_relatorioGeral;
    private javax.swing.JTextArea textArea_relatorioIndividual;
    // End of variables declaration//GEN-END:variables
 
}
