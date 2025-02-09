package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.KeyboardQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.CameraQrReadingStrategy;

public class QrReadingStrategyFactory {

    public static IQrReadingStrategy getQrReadingStrategy(Type type) {
         if (type.equals(Type.KEYBOARD)) {
            return  new KeyboardQrReadingStrategy();
        } else if (type.equals(Type.CAMERA)) {
            return  new CameraQrReadingStrategy();
        } else {
            throw new IllegalArgumentException("Tipo di strategia QR non supportata: " + type);
        }
    }
}

