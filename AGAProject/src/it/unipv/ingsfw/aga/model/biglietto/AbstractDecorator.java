package it.unipv.ingsfw.aga.model.biglietto;

public class AbstractDecorator implements IBiglietto {
    private IBiglietto biglietto;

    public AbstractDecorator(IBiglietto biglietto) {
        this.biglietto = biglietto;
    }

    public String stampaBiglietto() {
        return biglietto.stampaBiglietto();
    }

}
