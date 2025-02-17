package it.unipv.ingsfw.aga.model.banco;//ALBERTO

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.evento.Evento;
import java.util.Date;

public class Banco {
    private int numeroBanco;
    private Evento evento;
    private IQrReadingStrategy qrStrategy; // Aggiunta strategia

    public Banco(int numeroBanco, Evento evento) {
        this.numeroBanco = numeroBanco;
        this.evento=evento;
        this.qrStrategy = QrReadingStrategyFactory.getQrReadingStrategy(Type.GUI);
    }
    
    public Banco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
        this.evento=null;
        this.qrStrategy = QrReadingStrategyFactory.getQrReadingStrategy(Type.GUI);
    }

    // Getter per numeroBanco
    public int getNumeroBanco() {
        return numeroBanco;
    }
    
    public Date getDataEvento() {
    	return evento.getData();
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }
    public IQrReadingStrategy getQrStrategy() {
        return qrStrategy;
    }
    // Setter per impostare la strategia di lettura QR
    public void setQrStrategy(IQrReadingStrategy qrStrategy) {
        this.qrStrategy = qrStrategy;
    }

    public boolean validateQr(QrCode qr){return false;};
    
    public QrCode readQR() {
        if (qrStrategy == null) {
            throw new IllegalStateException("QR Strategy non impostata.");
        }
        QrCode qr = qrStrategy.readQR();
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        return qr;
    }

    public QrCode readQR(String code) {
        if (qrStrategy == null) {
            throw new IllegalStateException("QR Strategy non impostata.");
        }
        QrCode qr = qrStrategy.readQR(code);
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        return qr;
    }
    
    public String printEvento() {
    	return evento.toString();
    }
    
    public String toString() {
    	return "[Banco]\n" +
                "Numero banco " + getNumeroBanco() +"\n" +
                "Evento: " + getDataEvento() + "\n";
    }

}