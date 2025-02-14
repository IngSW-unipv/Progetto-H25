package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

import java.sql.SQLException;

public interface IBigliettoDAO {
    void aggiungiBiglietto(Biglietto biglietto) throws SQLException;
}
