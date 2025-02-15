package connection;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.QrReadingStrategyFactory;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;

public class Banco {
    private int numeroBanco;
    private Evento evento;
    private IQrReadingStrategy qrStrategy; // Aggiunta strategia

    public Banco(int numeroBanco,Evento evento) {
        this.numeroBanco = numeroBanco;
        this.evento=new Evento(evento);
        this.qrStrategy = QrReadingStrategyFactory.getQrReadingStrategy(Type.KEYBOARD);
    }

    // Getter per numeroBanco
    public int getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }
    public IQrReadingStrategy getQrStrategy() {
        return qrStrategy;
    }
    
    public String printEvento() {
    	return evento.toString();
    }
   /* // Setter per impostare la strategia di lettura QR
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
    }*/

}