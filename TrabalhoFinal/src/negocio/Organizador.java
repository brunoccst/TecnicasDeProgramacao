package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import negocio.entidades.Ticket;

/**
 *
 * @author Bruno
 */
public class Organizador {
    public static void OrganizaPorParquimetro(ArrayList<Ticket> tickets)
    {
            Collections.sort(tickets, new Comparator<Ticket>() {

                    @Override
                    public int compare(Ticket o1, Ticket o2) {
                        return Math.min(o1.getParquimetro().getId(), o2.getParquimetro().getId());
                    }

            });
    }
}
