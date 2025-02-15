package connection;

public class BancoIngresso extends Banco{

	public BancoIngresso(int numeroBanco,Evento evento) {
		super(numeroBanco,evento);
	}
	
	public String toString() {
		return "Numero banco: "+this.getNumeroBanco()+", evento: "+this.printEvento();
	}
}
