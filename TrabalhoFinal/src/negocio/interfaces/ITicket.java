package negocio.interfaces;

import java.time.LocalDateTime;

/**
 *
 * @author Bruno
 */
public interface ITicket {
    int getSerial();
    LocalDateTime getEmissao();
    LocalDateTime getValidade();
    IParquimetro getParquimetro();
    double getValor();
}
