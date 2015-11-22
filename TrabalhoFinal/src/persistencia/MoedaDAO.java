package persistencia;

import ModuloGerencial.Moeda;
import ModuloGerencial.Moeda;
import java.util.ArrayList;

public interface MoedaDAO {
    ArrayList<Moeda> buscarTodas();
    Moeda buscarPorCodigo(int id);
    void inserir(Moeda novaMoeda);
    void alterar(Moeda moeda);
}
