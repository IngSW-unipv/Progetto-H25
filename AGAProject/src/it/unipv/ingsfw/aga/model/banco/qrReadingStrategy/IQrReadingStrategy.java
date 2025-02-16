package it.unipv.ingsfw.aga.model.banco.qrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.QrCode;

public interface IQrReadingStrategy {
    QrCode readQR();
    QrCode readQR(String code);
}


