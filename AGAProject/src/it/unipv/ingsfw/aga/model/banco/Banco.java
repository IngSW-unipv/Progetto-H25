package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;

public abstract class Banco {
    private int numeroBanco;
    private IQrReadingStrategy qrStrategy; // Aggiunta strategia

    public Banco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
        this.qrStrategy = QrReadingStrategyFactory.getQrReadingStrategy(Type.KEYBOARD);
    }

    // Getter per numeroBanco
    public int getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    // Setter per impostare la strategia di lettura QR
    public void setQrStrategy(IQrReadingStrategy qrStrategy) {
        this.qrStrategy = qrStrategy;
    }

    public abstract boolean validateQr(QrCode qr); 
    
    public QrCode readQR() {
        if (qrStrategy == null) {
            throw new IllegalStateException("QR Strategy non impostata.");
        }
        QrCode qr = qrStrategy.readQR();
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        return qr;
    }

}