package connection;

import java.util.Date;

public class Evento {
	private Date data;
	private String luogo;
	private int capacita;
	
	public Evento(Date data, String luogo, int capacita) {
		this.data=data;
		this.luogo=luogo;
		this.capacita=capacita;
	}
	
	public Evento(Evento evento) {
		this.data=evento.getData();
		this.luogo=evento.getLuogo();
		this.capacita=evento.getCapacita();
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data=data;
	}
	
	public String getLuogo() {
		return luogo;
	}
	
	public void setLuogo(String luogo) {
		this.luogo=luogo;
	}
	
	public int getCapacita() {
		return capacita;
	}
	
	public void setCapacita(int capacita) {
		this.capacita=capacita;
	}
	
	public String toString(){
		return "Data: "+data+", luogo: "+luogo+", capacita: "+capacita;
	}
	
	
	
}
