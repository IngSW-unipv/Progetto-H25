package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

/**
 * Classe che rappresenta il banco per il guardaroba.
 * Gestisce l'assegnazione e la restituzione delle grucce per i capi consegnati.
 */
public class BancoGuardaroba extends Banco {
    private int maxGrucce;
    private int grucceAssegnate;

    /**
     * Costruttore che inizializza il banco guardaroba con il numero e l'evento associato.
     *
     * @param numeroBanco il numero identificativo del banco
     * @param evento      l'evento associato al banco
     */
    public BancoGuardaroba(int numeroBanco, Evento evento) {
        super(numeroBanco, evento);
        this.maxGrucce = 1000;
        this.grucceAssegnate = 0;
    }

    /**
     * Costruttore che inizializza il banco guardaroba con il numero, il massimo delle grucce e l'evento associato.
     *
     * @param numeroBanco il numero identificativo del banco
     * @param maxGrucce   il numero massimo di grucce disponibili
     * @param evento      l'evento associato al banco
     */
    public BancoGuardaroba(int numeroBanco, int maxGrucce, Evento evento) {
        super(numeroBanco, evento);
        this.maxGrucce = maxGrucce;
        this.grucceAssegnate = 0;
    }

    /**
     * Costruttore che inizializza il banco guardaroba con il numero, senza associare un evento.
     *
     * @param numeroBanco il numero identificativo del banco
     */
    public BancoGuardaroba(int numeroBanco) {
        super(numeroBanco, null);
        this.maxGrucce = 1000;
        this.grucceAssegnate = 0;
    }

    /**
     * Restituisce il numero massimo di grucce disponibili.
     *
     * @return il numero massimo di grucce
     */
    public int getMaxGrucce() {
        return maxGrucce;
    }

    /**
     * Imposta il numero massimo di grucce disponibili.
     *
     * @param maxGrucce il nuovo numero massimo di grucce
     */
    public void setMaxGrucce(int maxGrucce) {
        this.maxGrucce = maxGrucce;
    }

    /**
     * Aggiorna il numero massimo di grucce in base all'evento corrente tramite il PersistenceFacade.
     *
     * @param evento l'evento da cui ottenere il numero massimo di grucce
     */
    public void updateMaxGrucce(Evento evento) {
        setMaxGrucce(PersistenceFacade.getInstance().getMaxGrucce(evento));
    }

    /**
     * Restituisce il numero di grucce attualmente assegnate.
     *
     * @return il numero di grucce assegnate
     */
    public int getGrucceAssegnate() {
        return grucceAssegnate;
    }

    /**
     * Imposta il numero di grucce assegnate.
     *
     * @param grucceAssegnate il nuovo numero di grucce assegnate
     */
    public void setGrucceAssegnate(int grucceAssegnate) {
        this.grucceAssegnate = grucceAssegnate;
    }

    /**
     * Aggiorna il numero di grucce assegnate in base all'evento corrente tramite il PersistenceFacade.
     *
     * @param evento l'evento da cui ottenere il numero di grucce assegnate
     */
    public void updateGrucceAssegnate(Evento evento) {
        setGrucceAssegnate(PersistenceFacade.getInstance().getNumeroGrucceAssegnate(evento));
    }

    /**
     * Gestisce la consegna di un capo leggendo il QR tramite la strategia impostata.
     * Se il numero di grucce assegnate è inferiore al massimo, valida la restituzione e assegna la gruccia.
     *
     * @return un messaggio che indica l'esito dell'operazione
     */
    public String consegnaCapo() {
        QrCode qr = readQR();
        if (this.grucceAssegnate < this.maxGrucce) {
            if (restituzioneCapo(qr.getId()) == 0) {
                this.grucceAssegnate += 1;
                if (assegnaGruccia(qr, this.grucceAssegnate)) {
                    return "Oggetto inserito sulla gruccia: " + this.grucceAssegnate;
                } else {
                    return "Errore nell'assegnazione della gruccia";
                }
            } else {
                System.out.println("Gruccia già assegnata");
                return "Gruccia già assegnata";
            }
        } else {
            System.out.println("Grucce terminate");
            return "Grucce terminate";
        }
    }

    /**
     * Gestisce la consegna di un capo utilizzando un codice fornito.
     *
     * @param code il codice da utilizzare per leggere il QR
     * @return un messaggio che indica l'esito dell'operazione
     */
    public String consegnaCapo(String code) {
        QrCode qr = readQR(code);
        if (this.grucceAssegnate < this.maxGrucce) {
            if (restituzioneCapo(code) == 0) {
                this.grucceAssegnate += 1;
                if (assegnaGruccia(qr, this.grucceAssegnate)) {
                    return "Oggetto inserito sulla gruccia: " + this.grucceAssegnate;
                } else {
                    return "Errore nell'assegnazione della gruccia";
                }
            } else {
                System.out.println("Gruccia già assegnata");
                return "Gruccia già assegnata";
            }
        } else {
            System.out.println("Grucce terminate");
            return "Grucce terminate";
        }
    }

    /**
     * Restituisce il numero della gruccia associata a un capo, leggendo il QR tramite la strategia impostata.
     *
     * @return il numero della gruccia se il QR è valido, -1 altrimenti
     */
    public int restituzioneCapo() {
        QrCode qr = readQR();
        if (validateQr(qr)) {
            return PersistenceFacade.getInstance().getGruccia(qr.getId());
        } else {
            return -1;
        }
    }

    /**
     * Restituisce il numero della gruccia associata a un capo utilizzando il codice fornito.
     *
     * @param code il codice da utilizzare per leggere il QR
     * @return il numero della gruccia se il QR è valido, -1 altrimenti
     */
    public int restituzioneCapo(String code) {
        QrCode qr = readQR(code);
        if (validateQr(qr)) {
            return PersistenceFacade.getInstance().getGruccia(qr.getId());
        } else {
            return -1;
        }
    }

    /**
     * Assegna una gruccia a un capo in base al codice QR e al numero della gruccia.
     *
     * @param qr       il codice QR del capo
     * @param gruccia  il numero della gruccia da assegnare
     * @return true se l'assegnazione ha avuto successo, false altrimenti
     */
    public boolean assegnaGruccia(QrCode qr, int gruccia) {
        return PersistenceFacade.getInstance().setGruccia(qr.getId(), gruccia);
    }

    /**
     * Valida il codice QR per il banco guardaroba.
     * Il biglietto è considerato valido se lo stato è 1 e la data dell'evento corrisponde.
     *
     * @param qr il codice QR da validare
     * @return true se il biglietto è valido, false altrimenti
     */
    
    public boolean validateQr(QrCode qr) {
    	String data="" + evento.getData();
    	String dataBiglietto=""+PersistenceFacade.getInstance().getDataByBiglietto(qr.getId());
        if (PersistenceFacade.getInstance().getStatoBiglietto(qr.getId()) == 1 &&
            (data).equals(dataBiglietto)) {
            System.out.println("Biglietto valido");
            return true;
        } else {
            return qrCodeinvalido();
        }
    }

    /**
     * Restituisce una rappresentazione testuale del banco guardaroba.
     *
     * @return una stringa che rappresenta il banco guardaroba
     */
    @Override
    public String toString() {
        return "[Banco]\n" +
               "Tipo: Guardaroba\n" +
               "Numero banco: " + getNumeroBanco() + "\n" +
               "Evento: " + evento.getData() + "\n";
    }
}
