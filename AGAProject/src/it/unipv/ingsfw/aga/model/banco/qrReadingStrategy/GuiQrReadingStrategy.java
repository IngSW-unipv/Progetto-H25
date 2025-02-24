package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;

import it.unipv.ingsfw.aga.model.banco.QrCode;

/**
 * Implementazione della strategia di lettura del QR code in un ambiente grafico (GUI).
 */
public class GuiQrReadingStrategy implements IQrReadingStrategy {

    /**
     * Esegue la lettura del codice QR in modalità GUI.
     * Al momento, viene segnalato che la strategia non è corretta e viene restituito un QrCode generico.
     *
     * @return un oggetto QrCode generato
     */
    @Override
    public QrCode readQR() {
        System.out.println("Strategia di lettura QrCode errata");
        return new QrCode();
    }

    /**
     * Esegue la lettura del codice QR in modalità GUI utilizzando il codice fornito.
     *
     * @param code il codice da utilizzare per creare l'oggetto QrCode
     * @return un oggetto QrCode creato con il codice specificato
     */
    @Override
    public QrCode readQR(String code) {
        return new QrCode(code);
    }
}
