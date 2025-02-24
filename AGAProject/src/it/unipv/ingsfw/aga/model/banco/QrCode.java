package it.unipv.ingsfw.aga.model.banco;

import java.util.UUID;

/**
 * Rappresenta un codice QR, utilizzato per l'identificazione e la validazione dei biglietti.
 */
public class QrCode {
    private String id;

    /**
     * Crea un oggetto QrCode generando automaticamente un identificativo univoco.
     */
    public QrCode() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Crea un oggetto QrCode utilizzando l'identificativo specificato.
     *
     * @param id l'identificativo del codice QR
     */
    public QrCode(String id) {
        this.id = id;
    }

    /**
     * Restituisce l'identificativo del codice QR.
     *
     * @return l'identificativo
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta l'identificativo del codice QR.
     *
     * @param id il nuovo identificativo
     */
    public void setId(String id) {
        this.id = id;
    }
}
