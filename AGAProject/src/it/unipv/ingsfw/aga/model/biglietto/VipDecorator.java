package it.unipv.ingsfw.aga.model.biglietto;

public class VipDecorator extends AbstractDecorator {
    public VipDecorator(IBiglietto biglietto) {
        super(biglietto);
    }

    public String stampaBiglietto() {
        return super.stampaBiglietto() + " (VIP)";
    }
}
