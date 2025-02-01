package it.unipv.ingsfw.aga.model.evento;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;

import java.time.LocalDate;
import java.util.List;

public class Evento {
    private Organizzatore organizzatore;
    private String nome;
    private LocalDate data;
    private String location;
    private int maxPartecipanti;
    private boolean venditeAperte;
    private List<Biglietto> invitati;


    public Evento(String nome, LocalDate data, String location, int maxPartecipanti, List<String> servizi) throws MaxExeededException {
        this.nome = nome;
        this.data = data;
        this.location = location;
        if (maxPartecipanti < 0) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        } else if (maxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        } else {
            this.maxPartecipanti = maxPartecipanti;
        }
        this.venditeAperte = false;
    }
    public Organizzatore getOrganizzatore() {
        return organizzatore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxPartecipanti() {
        return maxPartecipanti;
    }

    public void setMaxPartecipanti(int newMaxPartecipanti) throws MaxExeededException {
        if(newMaxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        }else{
                this.maxPartecipanti = newMaxPartecipanti;
            }
    }

    public void AperturaVendite(){
        venditeAperte = true;
    }

    public void ChiusuraVendite(){
        venditeAperte = false;
    }

    // TODO: implemetare logica di creazione evento e gestione partecipanti

}

