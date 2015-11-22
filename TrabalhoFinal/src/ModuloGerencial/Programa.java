package ModuloGerencial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import persistencia.MoedaDAO;
import persistencia.MoedaDAOderby;

/**
 *
 * @author Bruno
 */
public class Programa {

    public static void main(String[] args)
    {
        MoedaDAO moedas = new MoedaDAOderby();
        Moeda novaMoeda = new Moeda("Um real", 1.0); //TODO: Verificar por que o ID é requisitado
        moedas.inserir(novaMoeda);
        ArrayList<Moeda> moedasDisponiveis = moedas.buscarTodas();
        for (Moeda m : moedasDisponiveis)
        {
            System.out.println(m.toString());
        }
    }
    
}