package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.QrCode;

import java.util.Scanner;



public class KeyboardQrReadingStrategy implements IQrReadingStrategy {

    @Override
    public QrCode readQR()  {
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
    @Override
    public QrCode readQR(String code){
        System.out.println("Strategia di lettura QrCode errata");
        return new QrCode(code);
    }
}