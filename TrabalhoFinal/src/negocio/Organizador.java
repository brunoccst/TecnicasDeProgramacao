package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import negocio.entidades.Ticket;
import negocio.interfaces.ITicket;

/**
 *
 * @author Bruno
 */
public class Organizador {
    public static void OrganizaPorParquimetro(ArrayList<ITicket> tickets)
    {
            Collections.sort(tickets, new Comparator<ITicket>() {

                    @Override
                    public int compare(ITicket o1, ITicket o2) {
                        if(o1.getParquimetro().getId() > o2.getParquimetro().getId()){
                            return 1;
                        }else if(o1.getParquimetro().getId() < o2.getParquimetro().getId()){
                            return -1;
                        }else {
                            return 0;
                        }
                    }

            });
    }
}
