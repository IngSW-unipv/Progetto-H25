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
	
}
