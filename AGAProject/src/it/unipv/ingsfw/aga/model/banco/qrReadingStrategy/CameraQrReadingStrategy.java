package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;

import it.unipv.ingsfw.aga.model.banco.QrCode;

public class CameraQrReadingStrategy implements IQrReadingStrategy {
    @Override
    public QrCode readQR() {
        // Placeholder per l'implementazione della lettura tramite fotocamera
        System.out.println("Lettura QR tramite fotocamera non ancora implementata.");
        QrCode qr = new QrCode();
        return qr;
    }

    public QrCode readQR(String code) {
        System.out.println("Lettura QR tramite fotocamera non ancora implementata.");
        System.out.println("Strategia di lettura QrCode errata");
       return new QrCode(code);
    }
}