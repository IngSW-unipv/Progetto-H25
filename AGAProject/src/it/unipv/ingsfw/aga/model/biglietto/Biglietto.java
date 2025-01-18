package it.unipv.ingsfw.aga.model.biglietto;

public class Biglietto implements IBiglietto {
    private final String id;

    public Biglietto(String id) {
        this.id = id;
    }
    public String getid(){
        return id;
    }
    public String stampaBiglietto(){
        return "Biglietto: " + id;
        //TOD0: completare logica di stampa e diversi override
    }

}
