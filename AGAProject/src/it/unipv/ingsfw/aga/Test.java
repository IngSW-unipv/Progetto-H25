package it.unipv.ingsfw.aga;

import it.unipv.ingsfw.aga.database.InvitatoDAO;
import it.unipv.ingsfw.aga.model.persone.Invitato;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        Invitato nuovoInvitato = new Invitato("Mario", "Rossi", "test@email.com");
        InvitatoDAO.aggiungiInvitato(nuovoInvitato);
    }
}
