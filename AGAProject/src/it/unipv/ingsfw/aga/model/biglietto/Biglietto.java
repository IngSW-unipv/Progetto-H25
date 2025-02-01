package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.exceptions.AlreadyUsedException;
import it.unipv.ingsfw.aga.model.persone.Persona;

public class Biglietto implements Stampabile {
    private String id;
    private Evento evento;
    private Persona invitato;
    private double prezzo;
    private boolean pagato;
    private boolean staff;
    private boolean ingressoEffettuato;
    private boolean guardarobaUsato;

    public Biglietto(Evento evento, Persona invitato, double prezzo) {
        this.id = "QRcode Unico"; //TODO: Implementare un generatore di QRcode unico
        this.evento = evento;
        this.invitato = invitato;
        this.prezzo = prezzo;
        this.staff = false;
        this.pagato = false;
        this.ingressoEffettuato = false;
        this.guardarobaUsato = false;
    }

    public String getId() {
        return id;
    }

    public Evento getEvento() {
        return evento;
    }

    public Persona getInvitato() {
        return invitato;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isPagato() {
        return pagato;
    }

    public void setPagato(){
        this.pagato = true;
    }

    public boolean isStaff(){
        return staff;
    }

    public void setIngressoEffettuato() throws AlreadyUsedException {
        if(ingressoEffettuato) {
            throw new AlreadyUsedException("Il biglietto con ID " + id + " è già stato utilizzato.");
        }
        this.ingressoEffettuato = true;
    }

    public void setGuardarobaUsato() throws AlreadyUsedException {
        if(guardarobaUsato){
            throw new AlreadyUsedException("E' già stato deposto un oggetto al guardaroba con ID " + id);
        }
        this.guardarobaUsato = true;
    }

    public String stampaBiglietto() {
        return "Biglietto\n" +
                "id ='" + id + '\'' +
                "\nEvento = " + evento.getData() +
                "\nPersona = " + invitato.getEmail() +
                "\nTipo: Standard";
    }
}