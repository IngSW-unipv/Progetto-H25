package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;

import it.unipv.ingsfw.aga.model.banco.QrCode;

public class CameraQrReadingStrategy implements QrReadingStrategy {
    @Override
    public QrCode readQR() {
        QrCode qr = new QrCode();
        // Placeholder per l'implementazione della lettura tramite fotocamera
        System.out.println("Lettura QR tramite fotocamera non ancora implementata.");
        return qr;
    }
}