package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.persistence.PersistenceFacade;
import it.unipv.ingsfw.aga.model.evento.Evento;

/**
 * Classe che rappresenta il banco per l'ingresso agli eventi.
 * Estende la classe astratta Banco e implementa la validazione dei codici QR.
 */
public class BancoIngresso extends Banco {

    /**
     * Costruttore che inizializza il banco ingresso con il numero e l'evento associato.
     *
     * @param numeroBanco il numero identificativo del banco
     * @param evento      l'evento associato al banco
     */
    public BancoIngresso(int numeroBanco, Evento evento) {
        super(numeroBanco, evento);
    }

    /**
     * Costruttore che inizializza il banco ingresso con il numero, senza associare un evento.
     *
     * @param numeroBanco il numero identificativo del banco
     */
    public BancoIngresso(int numeroBanco) {
        super(numeroBanco);
    }

    /**
     * Valida il codice QR in base allo stato e alla data dell'evento.
     *
     * @param qr il codice QR da validare
     * @return true se il biglietto è valido e il codice viene invalidato, false altrimenti
     */
    @Override
    public boolean validateQr(QrCode qr) {
        if (PersistenceFacade.getInstance().getStatoBiglietto(qr.getId()) == 0 &&
            evento.getData().equals(PersistenceFacade.getInstance().getDataByBiglietto(qr.getId()))) {
            System.out.println("Biglietto valido");
            return invalidateQr(qr);
        } else {
            return qrCodeinvalido();
        }
    }

    /**
     * Invalida il codice QR aggiornando lo stato del biglietto tramite il PersistenceFacade.
     *
     * @param qr il codice QR da invalidare
     * @return true se l'aggiornamento dello stato ha avuto successo, false altrimenti
     */
    public boolean invalidateQr(QrCode qr) {
        return PersistenceFacade.getInstance().setStatoBiglietto(qr.getId(), true);
    }

    /**
     * Effettua l'accesso leggendo il codice QR tramite la strategia impostata.
     *
     * @return true se il codice QR è valido, false altrimenti
     */
    public boolean accesso() {
        QrCode qr = readQR();
        return validateQr(qr);
    }

    /**
     * Effettua l'accesso leggendo il codice QR tramite la strategia impostata, utilizzando il codice fornito.
     *
     * @param code il codice da utilizzare per leggere il QR
     * @return true se il codice QR è valido, false altrimenti
     */
    public boolean accesso(String code) {
        QrCode qr = readQR(code);
        return validateQr(qr);
    }

    /**
     * Restituisce una rappresentazione testuale del banco ingresso.
     *
     * @return la stringa che rappresenta il banco ingresso
     */
    @Override
    public String toString() {
        return "[Banco]\n" +
               "Tipo: Ingresso\n" +
               "Numero banco: " + getNumeroBanco() + "\n" +
               "Evento: " + evento.getData() + "\n";
    }
}