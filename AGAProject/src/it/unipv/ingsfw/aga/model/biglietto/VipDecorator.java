package it.unipv.ingsfw.aga.model.biglietto;

public class VipDecorator extends BigliettoDecorator {
    public VipDecorator(Stampabile biglietto) {
        super(biglietto);
    }

    @Override
    protected String stringaDecorator() {
        return "VIP";
    }
}