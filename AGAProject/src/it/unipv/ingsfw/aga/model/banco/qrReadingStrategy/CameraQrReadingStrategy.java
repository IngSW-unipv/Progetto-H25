package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;

import it.unipv.ingsfw.aga.model.banco.QrCode;

/**
 * Implementazione della strategia di lettura del QR code tramite fotocamera.
 * Attualmente, questa implementazione è un placeholder e non esegue una lettura reale.
 */
public class CameraQrReadingStrategy implements IQrReadingStrategy {
    /**
     * Esegue la lettura del QR code tramite fotocamera.
     * Al momento, viene segnalato che la funzionalità non è implementata e
     * viene restituito un oggetto QrCode generico.
     *
     * @return un oggetto QrCode generato
     */
    @Override
    public QrCode readQR() {
        System.out.println("Lettura QR tramite fotocamera non ancora implementata.");
        QrCode qr = new QrCode();
        return qr;
    }

    /**
     * Esegue la lettura del QR code tramite fotocamera utilizzando un codice specificato.
     * Al momento, viene segnalato che la funzionalità non è implementata e viene restituito
     * un oggetto QrCode creato con il codice fornito.
     *
     * @param code il codice da utilizzare per creare l'oggetto QrCode
     * @return un oggetto QrCode creato con il codice specificato
     */
    @Override
    public QrCode readQR(String code) {
        System.out.println("Lettura QR tramite fotocamera non ancora implementata.");
        System.out.println("Strategia di lettura QrCode errata");
        return new QrCode(code);
    }
}