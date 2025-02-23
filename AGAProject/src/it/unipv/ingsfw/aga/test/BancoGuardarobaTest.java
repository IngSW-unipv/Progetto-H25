package it.unipv.ingsfw.aga.test;

import it.unipv.ingsfw.aga.model.banco.BancoGuardaroba;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BancoGuardarobaTest {

    private BancoGuardaroba bancoGuardaroba;

    @Before
    public void setUp() {
        bancoGuardaroba = new BancoGuardaroba(1);
    }

    @Test
    public void getMaxGrucceReturnsCorrectValue() {
        assertEquals(1000, bancoGuardaroba.getMaxGrucce());
    }

    @Test
    public void setMaxGrucceUpdatesValue() {
        bancoGuardaroba.setMaxGrucce(500);
        assertEquals(500, bancoGuardaroba.getMaxGrucce());
    }

    @Test
    public void getGrucceAssegnateReturnsCorrectValue() {
        assertEquals(0, bancoGuardaroba.getGrucceAssegnate());
    }

    @Test
    public void setGrucceAssegnateUpdatesValue() {
        bancoGuardaroba.setGrucceAssegnate(300);
        assertEquals(300, bancoGuardaroba.getGrucceAssegnate());
    }
}