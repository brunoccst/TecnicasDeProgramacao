/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.graficos;

import negocio.entidades.Parquimetro;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Bruno
 */
public interface IGrafico {
    void setDados(ArrayList<Parquimetro> dados);
    void setTitulo(String umTitulo);
    JPanel getPanel();
}
