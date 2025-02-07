package it.unipv.ingsfw.aga.model.banco;

import java.util.UUID;

public class QrCode {
    private String id;

    public QrCode() {
        this.id = generateRandomId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}
