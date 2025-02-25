package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.KeyboardQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.CameraQrReadingStrategy;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.GuiQrReadingStrategy;

public class QrReadingStrategyFactory {

    public static IQrReadingStrategy getQrReadingStrategy(Type type) {
        switch (type) {
            case KEYBOARD:
                return new KeyboardQrReadingStrategy();
            case CAMERA:
                return new CameraQrReadingStrategy();
            case GUI:
                return new GuiQrReadingStrategy();
            default:
                throw new IllegalArgumentException("Tipo di strategia QR non supportata: " + type);
        }
    }
    }

