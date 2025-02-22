package it.unipv.ingsfw.aga.model.banco;

import java.util.UUID;

public class QrCode {
    private String id;

    public QrCode() {
        this.id = UUID.randomUUID().toString();
    }
    public QrCode(String id) {
        this.id = id;
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
