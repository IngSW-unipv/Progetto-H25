package it.unipv.ingsfw.aga.model.biglietto;

public abstract class BigliettoDecorator implements Stampabile {
    protected Stampabile biglietto;
    public BigliettoDecorator(Stampabile biglietto) {
        this.biglietto = biglietto;
    }
    @Override
    public String stampaBiglietto() {
        String original = biglietto.stampaBiglietto();
        return original.replace("Tipo: Standard", "Tipo: " + stringaDecorator());
    }
    protected abstract String stringaDecorator();
}