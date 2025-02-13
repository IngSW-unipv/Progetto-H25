package connection;

public class Persona {
	private String CF;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private boolean staff;
	private boolean organizzatore;
	
	
	public Persona(String CF, String nome, String cognome, String email, String password, boolean staff, boolean organizzatore) {
		super();
		this.CF=CF;
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.password=password;
		this.staff=staff;
		this.organizzatore=organizzatore;
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
    
    public String toString() {
        return "CF: "+CF+", Nome: "+ nome+", Cognome: "+cognome;
    }
    
	
}
