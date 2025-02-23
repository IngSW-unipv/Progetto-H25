package it.unipv.ingsfw.aga.model.banco;


import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

import it.unipv.ingsfw.aga.model.evento.Evento;


public class BancoIngresso extends Banco {
	
    public BancoIngresso(int numeroBanco, Evento evento) {
        super(numeroBanco, evento);
    }
    
    public BancoIngresso(int numeroBanco) {
        super(numeroBanco);
    }


    @Override
	public boolean validateQr(QrCode qr){
	       if (PersistenceFacade.getInstance().getStatoBiglietto(qr.getId()) == 0 && evento.getData().equals(PersistenceFacade.getInstance().getDataByBiglietto(qr.getId()))){
               System.out.println("Biglietto valido");
               return invalidateQr(qr);
}
           else {
               return qrCodeinvalido();
           }
	}

    public boolean invalidateQr(QrCode qr){
        return PersistenceFacade.getInstance().setStatoBiglietto(qr.getId(),true);
    }



    public boolean accesso(){
        QrCode qr = readQR();
        return validateQr(qr);
    }

    public boolean accesso(String code){
        QrCode qr = readQR(code);
        return validateQr(qr);
    }
    
    @Override
    public String toString() {
    	return "[Banco]\n" +
                "Tipo: Ingresso\n" +
                "Numero banco: " + getNumeroBanco() +"\n" +
                "Evento: " + evento.getData() + "\n";
    }

}