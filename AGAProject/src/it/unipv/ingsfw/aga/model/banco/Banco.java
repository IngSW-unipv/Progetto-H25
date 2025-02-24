package it.unipv.ingsfw.aga.model.banco;//ALBERTO

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.evento.Evento;
import java.util.Date;

/**
 * Classe astratta che rappresenta un banco di controllo per un evento.
 * Gestisce la strategia di lettura dei codici QR e altre operazioni inerenti al banco.
 */
public abstract class Banco {
    private int numeroBanco;
    protected Evento evento;
    private IQrReadingStrategy qrStrategy;

    /**
     * Costruttore che inizializza il banco con il numero e l'evento associato.
     *
     * @param numeroBanco il numero identificativo del banco
     * @param evento l'evento associato al banco
     */
    public Banco(int numeroBanco, Evento evento) {
        this.numeroBanco = numeroBanco;
        this.evento = evento;
        this.qrStrategy = QrReadingStrategyFactory.getQrReadingStrategy(Type.GUI);
    }
    
    /**
     * Costruttore che inizializza il banco con il numero, senza associare un evento.
     *
     * @param numeroBanco il numero identificativo del banco
     */
    public Banco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
        this.evento = null;
        this.qrStrategy = QrReadingStrategyFactory.getQrReadingStrategy(Type.GUI);
    }

    /**
     * Restituisce il numero del banco.
     *
     * @return il numero del banco
     */
    public int getNumeroBanco() {
        return numeroBanco;
    }

    /**
     * Imposta il numero del banco.
     *
     * @param numeroBanco il nuovo numero del banco
     */
    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    /**
     * Restituisce la strategia di lettura QR attualmente impostata.
     *
     * @return la strategia di lettura QR
     */
    public IQrReadingStrategy getQrStrategy() {
        return qrStrategy;
    }

    /**
     * Imposta la strategia di lettura QR.
     *
     * @param qrStrategy la strategia da utilizzare per la lettura dei codici QR
     */
    public void setQrStrategy(IQrReadingStrategy qrStrategy) {
        this.qrStrategy = qrStrategy;
    }

    /**
     * Valida il codice QR.
     *
     * @param qr il codice QR da validare
     * @return true se il codice QR è valido, false altrimenti
     */
    public abstract boolean validateQr(QrCode qr);

    /**
     * Legge un codice QR utilizzando la strategia impostata.
     *
     * @return il codice QR letto
     * @throws IllegalStateException se la strategia di lettura non è impostata
     */
    public QrCode readQR() {
        if (qrStrategy == null) {
            throw new IllegalStateException("QR Strategy non impostata.");
        }
        QrCode qr = qrStrategy.readQR();
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        return qr;
    }

    /**
     * Legge un codice QR utilizzando la strategia impostata e un codice dato.
     *
     * @param code il codice da utilizzare per la lettura del QR
     * @return il codice QR letto
     * @throws IllegalStateException se la strategia di lettura non è impostata
     */
    public QrCode readQR(String code) {
        if (qrStrategy == null) {
            throw new IllegalStateException("QR Strategy non impostata.");
        }
        QrCode qr = qrStrategy.readQR(code);
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        return qr;
    }

    /**
     * Metodo per gestire la lettura di un codice QR non valido.
     *
     * @return false
     */
    public boolean qrCodeinvalido(){
        System.out.println("Biglietto già convalidato");
        return false;
    }
    
    /**
     * Restituisce una rappresentazione testuale dell'evento associato.
     *
     * @return la stringa rappresentante l'evento
     */
    public String printEvento() {
        return evento.toString();
    }
    
    /**
     * Restituisce una rappresentazione stringa del banco.
     *
     * @return la stringa che rappresenta il banco
     */
    public String toString() {
        return "[Banco]\n" +
                "Numero banco " + getNumeroBanco() + "\n" +
                "Evento: " + evento.getData() + "\n";
    }

}