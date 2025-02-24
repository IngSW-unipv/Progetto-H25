package it.unipv.ingsfw.aga.model.banco;

/**
 * Enum che definisce i tipi di strategie per la lettura dei codici QR.
 */
public enum Type {
    /**
     * Strategia per leggere il QR da tastiera.
     */
    KEYBOARD,

    /**
     * Strategia per leggere il QR utilizzando una fotocamera.
     */
    CAMERA,

    /**
     * Strategia per leggere il QR in un ambiente grafico (GUI).
     */
    GUI;
}