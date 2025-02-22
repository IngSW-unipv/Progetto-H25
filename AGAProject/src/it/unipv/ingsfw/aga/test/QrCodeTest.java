package it.unipv.ingsfw.aga.test;

import static org.junit.jupiter.api.Assertions.*;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import org.junit.jupiter.api.Test;

public class QrCodeTest {
    @Test
    void constructorIdAutomatico() {
        QrCode qrCode = new QrCode();
        assertNotNull(qrCode.getId());
        assertFalse(qrCode.getId().isEmpty());
    }
    @Test
    void constructorIdManuale() {
        String id = "test-id";
        QrCode qrCode = new QrCode(id);
        assertEquals(id, qrCode.getId());
    }
    @Test
    void ModificaId() {
        QrCode qrCode = new QrCode();
        String newId = "new-id";
        qrCode.setId(newId);
        assertEquals(newId, qrCode.getId());
    }
    @Test
    void ProduceIdUnico() {
        QrCode qrCode1 = new QrCode();
        QrCode qrCode2 = new QrCode();
        assertNotEquals(qrCode1.getId(), qrCode2.getId());
    }
}