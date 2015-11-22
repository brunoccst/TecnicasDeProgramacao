package ModuloGerencial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 *
 * @author Bruno
 */
public class Programa {
    public static String DB_CONN_STRING_CREATE = "jdbc:derby:TrabalhoFinal;create=true";
    public static String DB_NAME = "TrabalhoFinal";
    public static String USER_NAME = "";
    public static String PASSWORD = "";
    private static DataSource dataSource;
    
    public static void main(String[] args)
    {
        try (Connection conexao = getConexaoViaDriverManager()) {
            //Consultar dados da tabela
            String sql3 = "select * from MOEDAS";
            try (PreparedStatement comando = conexao.prepareStatement(sql3)) {
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        System.out.println(String.format("Valor: %d ; Nome: %s", resultados.getDouble("valor"), resultados.getString("nome")));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    private static Connection getConexaoViaDriverManager() throws Exception {
        return DriverManager.getConnection(DB_CONN_STRING_CREATE, USER_NAME, PASSWORD);
    }
    
    private static Connection getConexaoViaDataSource() throws Exception {
        if (dataSource == null) {
            EmbeddedDataSource ds = new EmbeddedDataSource();
            ds.setDatabaseName(DB_NAME);
            ds.setCreateDatabase("create");
            ds.setUser(USER_NAME);
            ds.setPassword(PASSWORD);
            dataSource = ds;
        }
        return dataSource.getConnection();
    }
}
