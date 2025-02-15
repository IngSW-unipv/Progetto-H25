package connection;

public class BancoGuardaroba extends Banco {
	private int maxGrucce;
    private int grucceAssegnate;
    
    public BancoGuardaroba(int numeroBanco,Evento evento, int maxGrucce, int grucceAssegnate) {
		super(numeroBanco,evento);
		this.maxGrucce=maxGrucce;
		this.grucceAssegnate=grucceAssegnate;
	}
    
    public int getMaxGrucce() {
    	return maxGrucce;
    }
    
    public int getGrucceAssegnate() {
    	return grucceAssegnate;
    }
    
    public String toString() {
		return "Numero banco: "+this.getNumeroBanco()+", max grucce: "+this.getMaxGrucce()+", grucce assegnate: "+
				this.getGrucceAssegnate()+", evento: "+this.printEvento();
	}
}
