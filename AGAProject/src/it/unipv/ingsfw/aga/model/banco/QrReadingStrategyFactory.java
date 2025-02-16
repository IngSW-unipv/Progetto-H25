package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.KeyboardQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.CameraQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.GuiQrReadingStrategy;

public class QrReadingStrategyFactory {

    public static IQrReadingStrategy getQrReadingStrategy(Type type) {
         if (type.equals(Type.KEYBOARD)) {
            return  new KeyboardQrReadingStrategy();
        } else if (type.equals(Type.CAMERA)) {
            return  new CameraQrReadingStrategy();
        }
         else if (type.equals(Type.GUI)) {
             return new GuiQrReadingStrategy();
         }else {
            throw new IllegalArgumentException("Tipo di strategia QR non supportata: " + type);
        }
    }
}

