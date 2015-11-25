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
    
    public void associaImportarInserir(ImportarInserirController c)
    {
        btn_importar.addActionListener(c);
        btn_inserir.addActionListener(c);
    }
    
    public void associaRelatorios(RelatoriosController c)
    {
        btn_gerarRelatorio.addActionListener(c);
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
    public String getDataRelatorio()
    {
        return box_data.getText();
    }
    
    public String getParquimetroSelecionadoRelatorio()
    {
        String parq = (String) dropdown_parquimetro.getSelectedItem();
        return parq;
    }
    
    public JButton getBotaoGerarRelatorio()
    {
        return btn_gerarRelatorio;
    }
    
    public JButton getBotaoAtualizarLista()
    {
        return btn_atualizarLista;
    }
    ////////////////////
    
    ////////////////////TAB: Graficos
    
    //////Subtab: Barras
    public String getAnoBarras()
    {
        return box_ano.getText();
    }
    
    public JButton getGerarBarras()
    {
        return btn_gerarBarras;
    }
    //////
    
    //////Subtab: Pizza
    public String getDataInicioPizza()
    {
        return box_dataInicio.getText();
    }
    
    public String getDataFimPizza()
    {
        return box_dataFim.getText();
    }
    
    public JButton getGerarPizza()
    {
        return btn_gerarPizza;
    }
    //////
    
    ////////////////////
    
    //Graficos
    public void setGraficoDeBarras(GraficoDeBarras g)
    {
        graficoDeBarras = g;
        panel_barras.removeAll();
        JPanel grafico = graficoDeBarras.getPanel();
        panel_barras.add(grafico, BorderLayout.CENTER);
        panel_barras.validate();
    }
    
    public void setGeradorDePizza(GraficoDeBarras g)
    {
        graficoDePizza = g;
    }
    
    public void setListaDeParquimetros(List<String> ids)
    {
        dropdown_parquimetro.removeAllItems();
        dropdown_parquimetro.addItem("Todos");
        for (String id : ids)
        {
            dropdown_parquimetro.addItem(id);
        }
    }
    
    public JButton getImportarLogging()
    {
        return btn_importar;
    }
       
    public int getDataBarras()
    {
        String dt = box_ano.getText();
        System.out.println(dt);
        if (dt.equals("")) return LocalDateTime.now().getYear();
        return Integer.parseInt(dt);
    }
    
    public int getParquimetroSelecionado()
    {
        String selected = (String) dropdown_parquimetro.getSelectedItem();
        if (selected.equals("Todos")) return 0;
        return Integer.parseInt(selected);
    }
    
    public JButton getGerarRelatorio()
    {
        return btn_gerarRelatorio;
    }
    
    public void mostra()
    {
        setVisible(true);
    }
    
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
        relatorio.setText(sb.toString());
    }
    
    public void setRelatorioValores(ArrayList<Double> mes, Parquimetro p){
        String[] mstr = {"JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ"};
        StringBuilder sb = new StringBuilder();
        sb.append("Parquimetro " + p.getId() + "\n");
        for(int i = 0; i < 12; i++){
            sb.append("Mes: " + mstr[i] + " Valor: " + mes.get(i) + "\n");
        }
        relatorio.setText(sb.toString());
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
        lbl_data = new javax.swing.JLabel();
        box_data = new javax.swing.JFormattedTextField();
        lbl_parquimetro = new javax.swing.JLabel();
        dropdown_parquimetro = new javax.swing.JComboBox<>();
        btn_gerarRelatorio = new javax.swing.JButton();
        btn_atualizarLista = new javax.swing.JButton();
        panel_relatorio = new javax.swing.JScrollPane();
        relatorio = new javax.swing.JTextArea();
        tab_graficos = new javax.swing.JPanel();
        tab_graficos_content = new javax.swing.JTabbedPane();
        tab_barras = new javax.swing.JPanel();
        lbl_ano = new javax.swing.JLabel();
        box_ano = new javax.swing.JFormattedTextField();
        panel_barras = new java.awt.Panel();
        btn_gerarBarras = new javax.swing.JButton();
        tab_pizza = new javax.swing.JPanel();
        lbl_dataInicio = new javax.swing.JLabel();
        box_dataInicio = new javax.swing.JFormattedTextField();
        lbl_fim = new javax.swing.JLabel();
        box_dataFim = new javax.swing.JFormattedTextField();
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
                .addComponent(panel_importarInserir, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        );

        moduloGerencial.addTab("Importar/Inserir", tab_importarInserir);

        lbl_data.setText("Data");

        box_data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        lbl_parquimetro.setText("Parquímetro");

        dropdown_parquimetro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_gerarRelatorio.setText("Gerar");

        btn_atualizarLista.setText("Atualizar lista");

        relatorio.setEditable(false);
        relatorio.setColumns(20);
        relatorio.setRows(5);
        panel_relatorio.setViewportView(relatorio);

        javax.swing.GroupLayout tab_relatoriosLayout = new javax.swing.GroupLayout(tab_relatorios);
        tab_relatorios.setLayout(tab_relatoriosLayout);
        tab_relatoriosLayout.setHorizontalGroup(
            tab_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_relatorio)
                    .addGroup(tab_relatoriosLayout.createSequentialGroup()
                        .addComponent(lbl_data)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(box_data, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_parquimetro)
                        .addGap(18, 18, 18)
                        .addComponent(dropdown_parquimetro, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarRelatorio)
                        .addGap(18, 18, 18)
                        .addComponent(btn_atualizarLista)
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tab_relatoriosLayout.setVerticalGroup(
            tab_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_relatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab_relatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_data)
                    .addComponent(box_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_parquimetro)
                    .addComponent(dropdown_parquimetro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarRelatorio)
                    .addComponent(btn_atualizarLista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_relatorio, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(panel_barras, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
        );

        tab_graficos_content.addTab("Barras", tab_barras);

        lbl_dataInicio.setText("Data inicio");

        box_dataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        lbl_fim.setText("Data fim");

        box_dataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

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
                        .addComponent(lbl_dataInicio)
                        .addGap(18, 18, 18)
                        .addComponent(box_dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_fim)
                        .addGap(18, 18, 18)
                        .addComponent(box_dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_gerarPizza)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addComponent(panel_pizza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        tab_pizzaLayout.setVerticalGroup(
            tab_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab_pizzaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tab_pizzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(box_dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dataInicio)
                    .addComponent(lbl_fim)
                    .addComponent(box_dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gerarPizza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_pizza, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(moduloGerencial)
                .addContainerGap())
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
    private javax.swing.JFormattedTextField box_data;
    private javax.swing.JFormattedTextField box_dataFim;
    private javax.swing.JFormattedTextField box_dataInicio;
    private javax.swing.JButton btn_atualizarLista;
    private javax.swing.JButton btn_gerarBarras;
    private javax.swing.JButton btn_gerarPizza;
    private javax.swing.JButton btn_gerarRelatorio;
    private javax.swing.JButton btn_importar;
    private javax.swing.JButton btn_inserir;
    private javax.swing.JTextPane console;
    private javax.swing.JComboBox<String> dropdown_parquimetro;
    private javax.swing.JLabel lbl_ano;
    private javax.swing.JLabel lbl_data;
    private javax.swing.JLabel lbl_dataInicio;
    private javax.swing.JLabel lbl_fim;
    private javax.swing.JLabel lbl_parquimetro;
    private javax.swing.JTabbedPane moduloGerencial;
    private java.awt.Panel panel_barras;
    private javax.swing.JScrollPane panel_importarInserir;
    private java.awt.Panel panel_pizza;
    private javax.swing.JScrollPane panel_relatorio;
    private javax.swing.JTextArea relatorio;
    private javax.swing.JPanel tab_barras;
    private javax.swing.JPanel tab_graficos;
    private javax.swing.JTabbedPane tab_graficos_content;
    private javax.swing.JPanel tab_importarInserir;
    private javax.swing.JPanel tab_pizza;
    private javax.swing.JPanel tab_relatorios;
    // End of variables declaration//GEN-END:variables
 
}
