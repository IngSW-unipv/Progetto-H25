package it.unipv.ingsfw.aga.model.banco;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;


public class BancoGuardaroba extends Banco {
    private int maxGrucce;
    private int grucceAssegnate;

    public BancoGuardaroba(int numeroBanco, Evento evento) {
        super(numeroBanco, evento);
        this.maxGrucce = 1000;
        this.grucceAssegnate = 0;
    }

    public BancoGuardaroba(int numeroBanco, int maxGrucce, Evento evento) {
        super(numeroBanco, evento);
        this.maxGrucce = maxGrucce;
        this.grucceAssegnate = 0;
    }
    
    public BancoGuardaroba(int numeroBanco) {
        super(numeroBanco, null);
        this.maxGrucce = maxGrucce;
        //this.grucceAssegnate = 0;??
    }

    public int getMaxGrucce() {
        return maxGrucce;
    }

    public void setMaxGrucce(int maxGrucce) {
        this.maxGrucce = maxGrucce;
    }

    public int getGrucceAssegnate() {
        return grucceAssegnate;
    }

    public void setGrucceAssegnate(int grucceAssegnate) {
        this.grucceAssegnate = grucceAssegnate;
    }

    public boolean consegnaCapo() {
        QrCode qr = readQR();
        this. grucceAssegnate += 1;
        if (this.grucceAssegnate < this.maxGrucce) {
           return assegnaGruccia(qr, this.grucceAssegnate);
        }else {
            System.out.println("Grucce terminate");
            return false;
        }
    }

    public String consegnaCapo(String code) {
        QrCode qr = readQR(code);
        if (this.grucceAssegnate < this.maxGrucce) {
            if(restituzioneCapo(code) == -1)//TODO: non so se giusto come metodo per verificare che una gruccia non sia già staa assegbata)
                {
            this.grucceAssegnate += 1;
            if (assegnaGruccia(qr, this.grucceAssegnate)){
            return "Oggetto inserito sulla gruccia: " + this.grucceAssegnate ;}
            else {
                return "Errore nell'assegnazione della gruccia";
            }
        }else{
                System.out.println("Gruccia già assegnata");
                return "Gruccia già assegnata";
            }
        }else {
            System.out.println("Grucce terminate");
            return "Grucce terminate";
        }
    }

    public int restituzioneCapo(){
        QrCode qr = readQR();
         if(validateQr(qr)) {
             return PersistenceFacade.getInstance().getGruccia(qr.getId());
         } else {
            return -1;
         }
    }

    public int restituzioneCapo(String code){
        QrCode qr = readQR(code);
        if(validateQr(qr)) {
            return PersistenceFacade.getInstance().getGruccia(qr.getId());
        } else {
            return -1;
        }
    }

    

    public boolean assegnaGruccia(QrCode qr, int gruccia){
        return PersistenceFacade.getInstance().setGruccia(qr.getId(),gruccia);
    }
    

    public boolean validateQr(QrCode qr){
            if (PersistenceFacade.getInstance().getStatoBiglietto(qr.getId()) == 1){
                System.out.println("Biglietto valido");
                return true;}
            else {
                return qrCodeinvalido();
            }
        }
    
    
    @Override
    public String toString() {
    	return "[Banco]\n" +
                "Tipo: Guardaroba\n" +
                "Numero banco: " + getNumeroBanco() +"\n" +
                "Evento: " + getDataEvento() + "\n";
    }
    
    
    
 }
