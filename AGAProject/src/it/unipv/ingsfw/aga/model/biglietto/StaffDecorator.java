package it.unipv.ingsfw.aga.model.biglietto;

public class StaffDecorator extends BigliettoDecorator {
    public StaffDecorator(Stampabile biglietto) {
        super(biglietto);
    }
    @Override
    protected String stringaDecorator() {
        return "Staff";
    }
}