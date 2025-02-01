package it.unipv.ingsfw.aga.model.biglietto;

public class AbstractDecorator implements Stampabile {
    private Stampabile biglietto;

    public AbstractDecorator(Stampabile biglietto) {
        this.biglietto = biglietto;
    }

    public String stampaBiglietto() {
        return biglietto.stampaBiglietto();
    }

}
