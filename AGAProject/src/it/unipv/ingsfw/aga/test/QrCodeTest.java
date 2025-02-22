package it.unipv.ingsfw.aga.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.unipv.ingsfw.aga.model.banco.QrCode;


public class QrCodeTest {
	
    @Test
    public void constructorIdAutomatico() {
        QrCode qrCode = new QrCode();
        assertNotNull(qrCode.getId());
        assertFalse(qrCode.getId().isEmpty());
    }
    
    @Test
    public void constructorIdManuale() {
        String id = "test-id";
        QrCode qrCode = new QrCode(id);
        assertEquals(id, qrCode.getId());
    }
    
    @Test
    public void ModificaId() {
        QrCode qrCode = new QrCode();
        String newId = "new-id";
        qrCode.setId(newId);
        assertEquals(newId, qrCode.getId());
    }
    
    @Test
    public void ProduceIdUnico() {
        QrCode qrCode1 = new QrCode();
        QrCode qrCode2 = new QrCode();
        assertNotEquals(qrCode1.getId(), qrCode2.getId());
    }
}