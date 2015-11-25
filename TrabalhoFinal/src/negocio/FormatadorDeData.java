package negocio;

import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Bruno
 */
public class FormatadorDeData {
    public static LocalDateTime FormataDataDiaMesAno(String data)
    {
        LocalDateTime dt = LocalDateTime.now();
        if (data == null || data.equals("")) return dt;
        
        String[] dataEmPartes = data.split("/");
        int dia = Integer.parseInt(dataEmPartes[0]);
        int mes = Integer.parseInt(dataEmPartes[1]);
        int ano = Integer.parseInt(dataEmPartes[2]);
        dt = LocalDateTime.of(ano, Month.of(mes), dia, 0, 0);
        
        return dt;
    }
    
    public static LocalDateTime FormataDataMesAno(String data, boolean inicio)
    {
        LocalDateTime dt = LocalDateTime.now();
        if (data == null || data.equals("")) return dt;
        
        String[] dataEmPartes = data.split("/");
        int dia = (inicio) ? 1 : 30;
        int mes = Integer.parseInt(dataEmPartes[0]);
        int ano = Integer.parseInt(dataEmPartes[1]);
        dt = LocalDateTime.of(ano, Month.of(mes), dia, 0, 0);
        
        return dt;
    }
}
