package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import java.util.Scanner;

/**
 * Implementazione della strategia di lettura del QR code tramite tastiera.
 * Utilizza lo standard input per acquisire il valore del codice QR.
 */
public class KeyboardQrReadingStrategy implements IQrReadingStrategy {

    /**
     * Legge un codice QR dalla tastiera.
     *
     * @return un oggetto QrCode contenente il valore letto dalla tastiera
     */
    @Override
    public QrCode readQR() {
        QrCode qr = new QrCode();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Inserisci il valore per la query:");
            String input = scanner.nextLine();
            qr.setId(input);
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }
        return qr;
    }

    /**
     * Metodo di lettura del QR code con parametro non supportato per questa strategia.
     *
     * @param code il codice da utilizzare (non viene utilizzato in questa strategia)
     * @return un oggetto QrCode creato con il valore fornito
     */
    @Override
    public QrCode readQR(String code) {
        System.out.println("Strategia di lettura QrCode errata");
        return new QrCode(code);
    }
}