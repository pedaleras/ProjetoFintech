import model.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        TipoRenda tr = new TipoRenda("Sálario");
        Date agora = new Date();
        Usuario usuario = new Usuario("admin","123");

        Recebimentos recebimento = new Recebimentos(10000,agora,usuario,tr);

        System.out.println(recebimento.getMensagemTransacao());

        TipoGasto tg = new TipoGasto("Comida");
        Gastos gasto = new Gastos(50,agora,usuario,tg);

        System.out.println(gasto.getMensagemTransacao());
    }
}