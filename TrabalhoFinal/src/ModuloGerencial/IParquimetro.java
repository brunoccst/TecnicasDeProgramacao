/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloGerencial;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author feliperiffel
 */
public interface IParquimetro {
    int getId();
    String getEndereco();
    ArrayList getTickets();
}
