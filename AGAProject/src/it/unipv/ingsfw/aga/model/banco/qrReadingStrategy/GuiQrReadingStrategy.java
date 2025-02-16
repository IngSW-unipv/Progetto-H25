package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;

import it.unipv.ingsfw.aga.model.banco.QrCode;

public class GuiQrReadingStrategy implements IQrReadingStrategy {

    @Override
    public QrCode readQR(){
        System.out.println("Strategia di lettura QrCode errata");
        return new QrCode();
    }

    @Override
    public QrCode readQR(String code){
        return new QrCode(code);
    }
}
