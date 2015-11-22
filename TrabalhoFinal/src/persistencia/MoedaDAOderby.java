package persistencia;

import ModuloGerencial.Moeda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

public class MoedaDAOderby implements MoedaDAO {

    public String DB_CONN_STRING_CREATE = "jdbc:derby:TrabalhoFinal;create=true";
    public String DB_NAME = "TrabalhoFinal";
    public String USER_NAME = "";
    public String PASSWORD = "";
    private DataSource dataSource;
    
    @Override
    public ArrayList<Moeda> buscarTodas() {
        ArrayList<Moeda> resultado = new ArrayList<Moeda>();
        try (Connection conexao = getConexaoViaDriverManager()) {
            String sql = "select * from MOEDAS";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                try (ResultSet resultados = comando.executeQuery()) {
                    while (resultados.next()) {
                        Moeda m = new Moeda(resultados.getString("nome"), resultados.getDouble("valor"));
                        resultado.add(m);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultado;
    }

    @Override
    public Moeda buscarPorCodigo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserir(Moeda novaMoeda) {
        try (Connection conexao = getConexaoViaDriverManager()) {
            //Inserir dados na tabela
            String sql = "insert into MOEDAS(nome, valor) "
                    + "values('" 
                    + novaMoeda.getNome()
                    + "', " 
                    + novaMoeda.getValor() 
                    + ")";
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.executeQuery();
                if (comando.executeUpdate() > 0) {
                    System.out.println("Inserção efetuada com sucesso");
                } else {
                    System.out.println("Falha na inserção");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void alterar(Moeda moeda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private Connection getConexaoViaDriverManager() throws Exception {
        return DriverManager.getConnection(DB_CONN_STRING_CREATE, USER_NAME, PASSWORD);
    }
    
    private Connection getConexaoViaDataSource() throws Exception {
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
