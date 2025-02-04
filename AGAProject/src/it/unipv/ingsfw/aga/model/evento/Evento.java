package it.unipv.ingsfw.aga.model.evento;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.persone.Invitato;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;

import java.time.LocalDate;
import java.util.HashMap;

public class Evento {
    private Organizzatore organizzatore;
    private LocalDate data;
    private String location;
    private int maxPartecipanti;
    private boolean venditeAperte;
    private HashMap<String, Invitato> invitati = new HashMap<>();

    public Evento(Organizzatore organizzatore, LocalDate data, String location, int maxPartecipanti) throws MaxExeededException {
        this.organizzatore = organizzatore;
        this.data = data;
        this.location = location;
        if(maxPartecipanti < 0){
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        }else if(maxPartecipanti > 1500){
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        }else{
            this.maxPartecipanti = maxPartecipanti;
        }
        this.venditeAperte = false;
    }

    public Organizzatore getOrganizzatore() {
        return organizzatore;
    }

    public LocalDate getData() {
        return data;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxPartecipanti() {
        return maxPartecipanti;
    }

    public boolean isVenditeAperte() {
        return venditeAperte;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void chiudiVendite() {
        venditeAperte = false;
    }

    public void setMaxPartecipanti(int maxPartecipanti) throws MaxExeededException {
        if(maxPartecipanti < 0){
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        }else if(maxPartecipanti > 1500){
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        }else{
            this.maxPartecipanti = maxPartecipanti;
        }
    }

    public boolean aggiungiInvitato(Invitato invitato) {
        if (invitati.containsKey(invitato.getEmail())) {
            System.out.println("Email già registrata: " + invitato.getEmail());
            return false;
        }
        invitati.put(invitato.getEmail(), invitato);
        System.out.println("Invitante aggiunto: " + invitato);
        return true;
    }


}
