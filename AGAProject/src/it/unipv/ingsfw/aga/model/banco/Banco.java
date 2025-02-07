package it.unipv.ingsfw.aga.model.banco;
import java.util.Scanner;

public abstract class Banco {
    private int numeroBanco;

    public Banco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }
    //Implementare con Evento il progressivo di banco

    // Getter for numeroBanco
    public int getNumeroBanco() {
        return numeroBanco;
    }

    public void setNumeroBanco(int numeroBanco) {
        this.numeroBanco = numeroBanco;
    }

    public abstract boolean validateQr(QrCode qr);

    //  method to read QR code
    public QrCode readQR() {
        // Implementation for reading QR codes at the entrance
        QrCode qr = new QrCode();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Inserisci il valore per la query:");
            String input = scanner.nextLine();
            qr.setId(input);
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Reading QR code at entrance banco: " + getNumeroBanco());
        // Logic for validating and processing the QR code goes here
        return qr;
    }
}
