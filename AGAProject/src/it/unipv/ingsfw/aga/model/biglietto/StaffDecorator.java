package it.unipv.ingsfw.aga.model.biglietto;

public class StaffDecorator extends AbstractDecorator {
    public StaffDecorator(Stampabile biglietto) {
        super(biglietto);
    }

    public String stampaBiglietto() {
        return super.stampaBiglietto() + " (Staff)";
    }

}
