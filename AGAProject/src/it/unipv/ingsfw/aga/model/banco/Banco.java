package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.QrReadingStrategy;

public abstract class Banco {
    private int numeroBanco;
    private QrReadingStrategy qrStrategy; // Aggiunta strategia

    public Banco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    // Getter per numeroBanco
    public int getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    // Setter per impostare la strategia di lettura QR
    public void setQrStrategy(QrReadingStrategy qrStrategy) {
        this.qrStrategy = qrStrategy;
    }

    public abstract boolean validateQr(QrCode qr);

    // Metodo aggiornato per leggere il QR tramite la strategia impostata
    public QrCode readQR() {
        if (qrStrategy == null) {
            throw new IllegalStateException("QR Strategy non impostata.");
        }
        QrCode qr = qrStrategy.readQR();
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        // Ulteriori logiche di elaborazione del QR possono essere aggiunte qui
        return qr;
    }
}