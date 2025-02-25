package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.exceptions.PermissionDeniedException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.evento.Evento;

import java.util.Date;

/**
 * Classe astratta che rappresenta una persona.
 */
public class Persona {
    private final String codiceFiscale;
    private String nome;
    private String cognome;
    private String email;
    /**
     * Costruttore con parametri.
     * @param codiceFiscale
     * @param nome
     * @param cognome
     * @param email
     */
    public Persona(String codiceFiscale, String nome, String cognome, String email) {
        this.codiceFiscale = codiceFiscale;
        this.nome=  nome;
        this.cognome = cognome;
        this.email = email;
    }
    /**
     * Costruttore di default.
     */
    public Persona() {
    	this.nome = null;
    	this.codiceFiscale = "nullo";
    	this.cognome = null;
    	this.email = null;
    }
    /**
     * Costruttore di copia.
     * @param p
     */
    public Persona(Persona p) {
        this.codiceFiscale = p.getCodiceFiscale();
        this.nome=  p.getNome();
        this.cognome = p.getCognome();
        this.email = p.getEmail();
    }
    /**
     * Costruttore con parametri.
     * @param codiceFiscale
     * @param email
     */
    public Persona(String codiceFiscale, String email) {
        this.codiceFiscale = codiceFiscale;
        this.nome = null;
        this.cognome = null;
        this.email = email;
    }
    public String getPassword() {
    	return null;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getEmail() {
        return email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Metodo per la creazione di eventi per le Persone
     * @see it.unipv.ingsfw.aga.model.evento.EventoFactory
     * @param tipoEvento garantisce la possibilità di creare eventi di diverso tipo per future implementazioni
     * @param organizzatoreCF codice fiscale dell'organizzatore
     * @param data data dell'evento identifica in modo unico l'evento
     * @param location luogo dell'evento
     * @param maxPartecipanti numero massimo di partecipanti all'evento
     * @return l'evento creato
     * @throws MaxExeededException se il numero massimo di partecipanti è superato
     * @throws PermissionDeniedException se l'utente non ha i permessi per creare un evento
     */
    public Evento creaEvento(String tipoEvento,String organizzatoreCF, Date data, String location, int maxPartecipanti) throws MaxExeededException {
        throw new PermissionDeniedException();
    }
    /**
     * Metodo per la verifica dell'ingresso
     * @param type modalità di lettura scelta
     * @param qrCode codice qr da leggere
     * @throws PermissionDeniedException se l'utente non ha i permessi per verificare l'ingresso
     */
    public void checkIngresso(Type type, QrCode qrCode){
        throw new PermissionDeniedException();
    }
    /**
     * Metodo per la verifica accesso al guardaroba
     * @param type modalità di lettura scelta
     * @param qrCode codice qr da leggere
     * @throws PermissionDeniedException se l'utente non ha i permessi per verificare l'ingresso
     */
    public void checkGuardaroba(Type type, QrCode qrCode){
        throw new PermissionDeniedException();
    }
    public String toString() {
        return "[Persona]\n" +
                "Tipo: Nessuno\n" +
        		"Codice Fiscale: "+codiceFiscale+"\n"+
                "Nome: " + nome+" Cognome: " + cognome + "\n" +
                "Email: " + email + "\n";
    }
}