/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;
import persistencia.ParquimetroDAO;

/**
 *
 * @author feliperiffel
 */
public class ParquimetroFactory {
    public static Parquimetro criaParquimetro(String endereco){
        return ParquimetroDAO.creteNewParquimetro(endereco);
    }
    public static void updateParquimetro(Parquimetro parquimetro){
        
    }
}
