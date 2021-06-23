package SharedMobility2;

import java.util.Random;

public class Bicicletta extends Veicoli implements GeneraID
{
    private boolean cascobici;

    public Bicicletta(String marca, String modello, double prezzoalminuto, Coordinate coordinateveicolo) {
        super(marca, modello, prezzoalminuto, coordinateveicolo);
        setCascobici(true);
        super.setIdveicolo(GeneraIdentificativo());
    }

    public boolean isCascobici() {
        return cascobici;
    }

    public void setCascobici(boolean cascobici) {
        this.cascobici = cascobici;
    }
    public void CascobiciSmarrito()
    {
        setCascobici(false);
    }

    @Override
    public String GeneraIdentificativo() {
        Random rnd = new Random();
        String s = new String();
        s=s.concat(getMarca().substring(0,2));
        s=s.concat(getModello().substring(0,2));
        s=s.concat("TU");
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.concat(String.valueOf(rnd.nextInt(9)));
        s=s.toUpperCase();
        return s;
    }
    @Override
    public void Aggiungikm(double km)
    {
        setKmpercorsi(getKmpercorsi()+km);
    }
}
